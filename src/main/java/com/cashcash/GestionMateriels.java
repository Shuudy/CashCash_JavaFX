package com.cashcash;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class GestionMateriels {

    private DatabaseConnection dc;

    public GestionMateriels(DatabaseConnection dc) {
        this.dc = dc;
    }

    public ArrayList<Materiel> getMateriels(int idClient) {
        Connection conn = dc.getConnection();
        ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
        try {
            PreparedStatement ps1 = conn.prepareStatement("SELECT m.contractNum, m.id, m.saleDate, m.installationDate, m.salePrice, m.location, mt.internalRef, mt.label FROM materials m, materialstypes mt WHERE mt.internalRef=m.internalRef AND m.clientNum = ?");
            ps1.setInt(1, idClient);
            ResultSet rs1 = ps1.executeQuery();
            
            while (rs1.next()) {
                TypeMateriel tm = new TypeMateriel(rs1.getString("internalRef"), rs1.getString("label"));
                Materiel m = new Materiel(rs1.getInt("id"), rs1.getDate("saleDate"), rs1.getDate("installationDate"), rs1.getDouble("salePrice"), rs1.getString("location"), tm, rs1.getInt("contractNum"));
                lesMateriels.add(m);
            }
            
        } catch (Exception e) {
			e.printStackTrace();	
		}

        return lesMateriels;
    } 

    public Client getClient(int id) {
        Connection conn = dc.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM clients WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int clientNum = rs.getInt("id");

                // On parcours les matériels du client que l'on parcours
                ArrayList<Materiel> lesMateriels = getMateriels(clientNum);

                // Contrat de maintenance
                PreparedStatement ps2 = conn.prepareStatement("SELECT id, signatureDate, dueDate FROM maintenancecontracts WHERE clientNum = ?");
                ps2.setInt(1, clientNum);
                ResultSet rs2 = ps2.executeQuery();

                ContratMaintenance cm = null;
                while(rs2.next()) {
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
                    cm
                );

                return unClient;
            }            
        } catch (Exception e) {
			e.printStackTrace();	
		}
        return null;
    }

    public String xmlClient(Client unClient) throws IOException {
        
        String xmlMatTotal ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\n"+"<listeMateriel>"+"\n"+"<materiels idClient=\""+ unClient.getId()+"\">\n";

        for(Materiel materiel : unClient.getMateriels()) {
            xmlMatTotal+=materiel.xmlMateriel() + "\n";
        }   

        xmlMatTotal+= "</listeMateriel>\n";

        Fichier fichierDesMateriels = new Fichier();

        fichierDesMateriels.ouvrir("materielsClient" + unClient.getId() + ".xml", "w");

        fichierDesMateriels.ecrire(xmlMatTotal);

        fichierDesMateriels.fermer();

        return xmlMatTotal;
    }
}
