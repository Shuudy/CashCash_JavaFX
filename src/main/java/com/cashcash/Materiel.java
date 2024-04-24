package com.cashcash;

import java.sql.Date;
import java.time.LocalDate;

public class Materiel {
    private int numSerie;
    private LocalDate dateVente;
    private double prixVente;
    private String emplacement;

    private TypeMateriel leType;

    public Materiel(int numSerie, String dateVente, double prixVente, String emplacement, TypeMateriel leType){
        this.numSerie = numSerie;

        Date sqlDate = Date.valueOf(dateVente);
        this.dateVente = sqlDate.toLocalDate();

        this.prixVente = prixVente;
        this.emplacement = emplacement;

        this.leType = leType;
    }

    public String xmlMateriel(){
        String chaineMat = "<materiel numSerie=\"" + numSerie +"\">\n";

        chaineMat += "<type refInterne=\"" + leType.getReferenceInterne() + "\" libelle=\"" + leType.getLibelleTypeMateriel() + "\" />\n";
        chaineMat += "<famille codeFamille=\"SE\" libelle=\"serveur\" />\n";
        chaineMat += "<date_installation>" + dateVente.toString() + "</date_installation>\n";
        chaineMat += "<prix_vente>" + prixVente + "</prix_vente>\n";
        chaineMat += "<emplacement>" + emplacement + "</emplacement>\n";
        chaineMat += "<nbJourAvantEcheance>94</nbJourAvantEcheance>\n";

        chaineMat += "</materiel>";

        return chaineMat;
    }
}
