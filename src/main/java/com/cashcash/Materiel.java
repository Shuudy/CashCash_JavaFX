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
        String chaineMat = "<materiel numSerie=\"" + numSerie +"\">\n";

        chaineMat += "<type refInterne=\"" + leType.getReferenceInterne() + "\" libelle=\"" + leType.getLibelleTypeMateriel() + "\" />\n";
        chaineMat += "<famille codeFamille=\"SE\" libelle=\"serveur\" />\n";
        chaineMat += "<date_installation>" + dateInstallation.toString() + "</date_installation>\n";
        chaineMat += "<prix_vente>" + prixVente + "</prix_vente>\n";
        chaineMat += "<emplacement>" + emplacement + "</emplacement>\n";
        chaineMat += "<nbJourAvantEcheance>94</nbJourAvantEcheance>\n";

        chaineMat += "</materiel>";

        return chaineMat;
    }
}
