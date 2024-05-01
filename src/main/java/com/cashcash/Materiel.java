package com.cashcash;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Représente un matériel vendu par l'entreprise.
 * Cette classe stocke les informations relatives à un matériel, telles que son numéro de série,
 * sa date de vente, sa date d'installation, son prix de vente, son emplacement, etc.
 * Elle permet également de générer des représentations XML de ce matériel.
 */
public class Materiel {
    private int numSerie;
    private LocalDate dateVente;
    private LocalDate dateInstallation;
    private double prixVente;
    private String emplacement;
    private int contractNum;

    private TypeMateriel leType;

    /**
     * Constructeur de la classe Materiel.
     *
     * @param numSerie          Le numéro de série du matériel.
     * @param dateVente         La date de vente du matériel.
     * @param dateInstallation  La date d'installation du matériel.
     * @param prixVente         Le prix de vente du matériel.
     * @param emplacement       L'emplacement du matériel.
     * @param leType            Le type de matériel.
     * @param contractNum       Le numéro de contrat associé au matériel.
     */
    public Materiel(int numSerie, Date dateVente, Date dateInstallation, double prixVente, String emplacement, TypeMateriel leType, int contractNum){
        this.numSerie = numSerie;

        this.dateVente = dateVente.toLocalDate();
        this.dateInstallation = dateInstallation.toLocalDate();

        this.prixVente = prixVente;
        this.emplacement = emplacement;

        this.contractNum = contractNum;
        this.leType = leType;
    }

    /**
     * Obtient le numéro de contrat associé au matériel.
     *
     * @return Le numéro de contrat associé au matériel, si aucun alors null.
     */
    public int getContractNum() {
        return contractNum;
    }

    /**
     * Obtient la date d'installation du matériel.
     *
     * @return La date d'installation du matériel.
     */
    public LocalDate getDateInstallation() {
        return dateInstallation;
    }
    
    /**
     * Obtient le libellé du type de matériel.
     *
     * @return Le libellé du type de matériel.
     */
    public String getLibelleTypeMateriel() {
        return leType.getLibelleTypeMateriel();
    }

    /**
     * Obtient le numéro de série du matériel.
     *
     * @return Le numéro de série du matériel.
     */
    public int getNumSerie() {
        return numSerie;
    }

    /**
     * Obtient l'emplacement du matériel.
     *
     * @return L'emplacement du matériel.
     */
    public String getEmplacement() {
        return emplacement;
    }

    /**
     * Définit le numéro de contrat associé au matériel.
     *
     * @param contractNum Le numéro de contrat à définir.
     */
    public void setContractNum(int contractNum) {
        this.contractNum = contractNum;
    }

    /**
     * Génère une représentation XML du matériel avec un nombre de jours avant l'échéance.
     *
     * @param nbJourAvantEcheance Le nombre de jours avant l'échéance.
     * @return La représentation XML du matériel en String.
     */
    public String xmlMateriel(int nbJourAvantEcheance){
        String chaineMat = "\t<materiel numSerie=\"" + numSerie +"\">\n";

        chaineMat += "\t\t<type refInterne=\"" + leType.getReferenceInterne() + "\" libelle=\"" + leType.getLibelleTypeMateriel() + "\" />\n";
        chaineMat += "\t\t<famille codeFamille=\"SE\" libelle=\"serveur\" />\n";
        chaineMat += "\t\t<date_installation>" + dateInstallation.toString() + "</date_installation>\n";
        chaineMat += "\t\t<prix_vente>" + prixVente + "</prix_vente>\n";
        chaineMat += "\t\t<emplacement>" + emplacement + "</emplacement>\n";
        chaineMat += "\t\t<nbJourAvantEcheance>" + nbJourAvantEcheance + "</nbJourAvantEcheance>\n";

        chaineMat += "\t</materiel>";

        return chaineMat;
    }

    /**
     * Génère une représentation XML du matériel sans spécifier le nombre de jours avant l'échéance.
     *
     * @return La représentation XML du matériel en String.
     */
    public String xmlMateriel(){
        String chaineMat = "\t<materiel numSerie=\"" + numSerie +"\">\n";

        chaineMat += "\t\t<type refInterne=\"" + leType.getReferenceInterne() + "\" libelle=\"" + leType.getLibelleTypeMateriel() + "\" />\n";
        chaineMat += "\t\t<famille codeFamille=\"SE\" libelle=\"serveur\" />\n";
        chaineMat += "\t\t<date_installation>" + dateInstallation.toString() + "</date_installation>\n";
        chaineMat += "\t\t<prix_vente>" + prixVente + "</prix_vente>\n";
        chaineMat += "\t\t<emplacement>" + emplacement + "</emplacement>\n";

        chaineMat += "\t</materiel>";

        return chaineMat;
    }
}
