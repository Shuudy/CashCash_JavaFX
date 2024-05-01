package com.cashcash;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Gère les opérations liées aux matériels et aux contrats de maintenance.
 */
public class GestionMateriels {

    private DatabaseConnection dc;

    /**
     * Initialise un objet GestionMateriels avec une connexion à la base de données.
     *
     * @param dc La connexion à la base de données.
     */
    public GestionMateriels(DatabaseConnection dc) {
        this.dc = dc;
    }

    /**
     * Récupère les matériels associés à un client à partir de la base de données.
     *
     * @param idClient L'identifiant du client.
     * @return Une liste d'objets Materiel associés au client.
     */
    public ArrayList<Materiel> getMateriels(int idClient) {
        Connection conn = dc.getConnection();
        ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
        try {
            PreparedStatement ps1 = conn.prepareStatement(
                    "SELECT m.contractNum, m.id, m.saleDate, m.installationDate, m.salePrice, m.location, mt.internalRef, mt.label FROM materials m, materialstypes mt WHERE mt.internalRef=m.internalRef AND m.clientNum = ?");
            ps1.setInt(1, idClient);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                TypeMateriel tm = new TypeMateriel(rs1.getString("internalRef"), rs1.getString("label"));
                Materiel m = new Materiel(rs1.getInt("id"), rs1.getDate("saleDate"), rs1.getDate("installationDate"),
                        rs1.getDouble("salePrice"), rs1.getString("location"), tm, rs1.getInt("contractNum"));
                lesMateriels.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lesMateriels;
    }

    /**
     * Associe un matériel à un contrat de maintenance.
     *
     * @param materiel Le matériel à associer au contrat.
     * @param contrat  Le contrat de maintenance auquel associer le matériel.
     */
    public void setMaterielToContrat(Materiel materiel, ContratMaintenance contrat) {

        try {
            Connection conn = dc.getConnection();

            // On met à jour le numéro du contrat du matériel
            PreparedStatement ps = conn.prepareStatement("UPDATE materials SET contractNum = ? WHERE id = ?");
            ps.setInt(1, contrat.getNumContrat());
            ps.setInt(2, materiel.getNumSerie());
            ps.executeUpdate();

            // On affecte le numéro de contrat au matériel
            materiel.setContractNum(contrat.getNumContrat());

            // On ajoute le matériel au contrat
            contrat.ajouteMateriel(materiel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un nouveau contrat de maintenance pour un client s'il n'en a pas déjà un.
     *
     * @param client Le client pour lequel créer le contrat de maintenance.
     * @return Le nouveau contrat de maintenance créé, ou le contrat de maintenance déjà existant.
     * @throws SQLException En cas d'erreur lors de l'interaction avec la base de données.
     */
    public ContratMaintenance createContratMaintenance(Client client) throws SQLException {
        ContratMaintenance unContrat = null;
        if (!client.aUnContratMaintenance()) {
            Connection conn = dc.getConnection();

            Date signatureDate = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(signatureDate);
            calendar.add(Calendar.YEAR, 1);
            Date dueDate = calendar.getTime();

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO maintenancecontracts(signatureDate, dueDate, clientNum) VALUES(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(signatureDate.getTime()));
            ps.setDate(2, new java.sql.Date(dueDate.getTime()));
            ps.setInt(3, client.getId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int contratId = rs.getInt(1);

            unContrat = new ContratMaintenance(contratId, new java.sql.Date(signatureDate.getTime()),
                    new java.sql.Date(dueDate.getTime()));
            client.setContratMaintenance(unContrat);
        } else {
            unContrat = client.getContratMaintenance();
        }

        return unContrat;
    }

    /**
     * Récupère les informations d'un client à partir de la base de données.
     *
     * @param id L'identifiant du client.
     * @return Le client correspondant à l'identifiant spécifié.
     */
    public Client getClient(int id) {
        Connection conn = dc.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM clients WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int clientNum = rs.getInt("id");

                // On parcours les matériels du client que l'on parcours
                ArrayList<Materiel> lesMateriels = getMateriels(clientNum);

                // Contrat de maintenance
                PreparedStatement ps2 = conn.prepareStatement(
                        "SELECT id, signatureDate, dueDate FROM maintenancecontracts WHERE clientNum = ?");
                ps2.setInt(1, clientNum);
                ResultSet rs2 = ps2.executeQuery();

                ContratMaintenance cm = null;
                while (rs2.next()) {
                    cm = new ContratMaintenance(rs2.getInt("id"), rs2.getDate("signatureDate"), rs2.getDate("dueDate"));
                }

                Client unClient = new Client(
                        rs.getInt("id"),
                        rs.getString("socialReason"),
                        rs.getString("sirenNum"),
                        rs.getString("apeCode"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getString("mailAddress"),
                        rs.getInt("travelTime"),
                        rs.getInt("distanceKm"),
                        lesMateriels,
                        cm);

                return unClient;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Génère une représentation XML des matériels d'un client et enregistre le fichier XML.
     *
     * @param unClient Le client pour lequel générer la représentation XML des matériels.
     * @return La représentation XML des matériels du client.
     * @throws IOException En cas d'erreur lors de la lecture ou de l'écriture du fichier XML.
     */
    public String xmlClient(Client unClient) throws IOException {

        String xmlMatTotal = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n" + "<listeMateriel>" + "\n"
                + "<materiels idClient=\"" + unClient.getId() + "\">\n";

        // Sous contrat
        xmlMatTotal += "\t<souContrat>\n";
        ContratMaintenance ct = unClient.getContratMaintenance();
        if (ct != null) {
            int jourRestant = ct.getJoursRestants();
            for (Materiel materiel : dc.getMaterielForClient(unClient.getId(), true)) {
                xmlMatTotal += materiel.xmlMateriel(jourRestant) + "\n";
            }
        }

        xmlMatTotal += "\t</sousContrat>\n";

        // Hors contrat
        xmlMatTotal += "\t<horsContrat>\n";
        for (Materiel materiel : dc.getMaterielForClient(unClient.getId(), false)) {
            xmlMatTotal += materiel.xmlMateriel() + "\n";
        }
        xmlMatTotal += "\t</horsContrat>\n";

        xmlMatTotal += "</listeMateriel>";

        Fichier fichierDesMateriels = new Fichier();
        fichierDesMateriels.ouvrir("materielsClient" + unClient.getId() + ".xml", "w");
        fichierDesMateriels.ecrire(xmlMatTotal);
        fichierDesMateriels.fermer();

        return xmlMatTotal;
    }

     /**
     * Génère une représentation PDF et enregistre le fichier PDF.
     *
     * @param client Le client pour lequel générer la représentation PDF.
     * @return La représentation PDF du message de relance du client.
     */
    public void pdfClient(Client client) {
        String space = "\n\n\n\n\n\n\n";
        String sp = "\n\n";
        String text = "\tNous vous informons que votre contrat avec CashCash arrivera à expiration le " + client.getContratMaintenance().getDateEcheance() + ". \nVeuillez envisager de renouveler votre contrat pour continuer à profiter de nos services.\nPour toute question ou assistance, n'hésitez pas à nous contacter.\n\nCordialement,";
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("relancecli" + client.getId() + ".pdf"));
            document.open();
            document.add(new Paragraph(client.getRaisonSociale() + "\nID => " + client.getId() + "\nMail => " + client.getEmail()));
            document.add(new Paragraph(space + "Sujet: Relance contrat de maintenance" + sp + text));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
