package com.cashcash;

import java.sql.Date;
import java.time.LocalDate;

public class Materiel {
    private int numSerie;
    private LocalDate dateVente;
    private LocalDate dateInstallation;
    private double prixVente;
    private String emplacement;
    private int contractNum;

    private TypeMateriel leType;

    public Materiel(int numSerie, Date dateVente, Date dateInstallation, double prixVente, String emplacement, TypeMateriel leType, int contractNum){
        this.numSerie = numSerie;

        this.dateVente = dateVente.toLocalDate();
        this.dateInstallation = dateInstallation.toLocalDate();

        this.prixVente = prixVente;
        this.emplacement = emplacement;

        this.contractNum = contractNum;
        this.leType = leType;
    }

    public int getContractNum() {
        return contractNum;
    }

    public LocalDate getDateInstallation() {
        return dateInstallation;
    }

    public String getLibelleTypeMateriel() {
        return leType.getLibelleTypeMateriel();
    }

    public int getNumSerie() {
        return numSerie;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setContractNum(int contractNum) {
        this.contractNum = contractNum;
    }

    public String xmlMateriel(){
        String chaineMat = "\t<materiel numSerie=\"" + numSerie +"\">\n";

        chaineMat += "\t\t<type refInterne=\"" + leType.getReferenceInterne() + "\" libelle=\"" + leType.getLibelleTypeMateriel() + "\" />\n";
        chaineMat += "\t\t<famille codeFamille=\"SE\" libelle=\"serveur\" />\n";
        chaineMat += "\t\t<date_installation>" + dateInstallation.toString() + "</date_installation>\n";
        chaineMat += "\t\t<prix_vente>" + prixVente + "</prix_vente>\n";
        chaineMat += "\t\t<emplacement>" + emplacement + "</emplacement>\n";
        chaineMat += "\t\t<nbJourAvantEcheance>94</nbJourAvantEcheance>\n";

        chaineMat += "\t</materiel>";

        return chaineMat;
    }
}
