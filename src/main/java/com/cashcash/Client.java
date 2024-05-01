package com.cashcash;

import java.util.ArrayList;

/**
 * Représente un client de l'entreprise.
 * Cette classe stocke les informations relatives à un client, telles que sa raison sociale,
 * son numéro SIREN, son code APE, son adresse, etc.
 * Elle permet également de gérer les contrats de maintenance associés au client.
 */
public class Client {
    private String raisonSociale, siren, codeApe, adresse, telClient, email;
    private int numClient, dureeDeplacement, distanceKm;

    private ArrayList<Materiel> lesMateriels;
    private ContratMaintenance leContrat;
    
    /**
     * Constructeur de la classe Client.
     *
     * @param numClient       Le numéro du client.
     * @param raisonSociale   La raison sociale du client.
     * @param siren           Le numéro SIREN du client.
     * @param codeApe         Le code APE du client.
     * @param adresse         L'adresse du client.
     * @param telClient       Le numéro de téléphone du client.
     * @param email           L'email du client.
     * @param dureeDeplacement   La durée de déplacement de l'agence au client.
     * @param distanceKm      La distance en kilomètres de l'agence au client.
     * @param lesMateriels    La liste des matériels du client.
     * @param leContrat       Le contrat de maintenance du client.
     */
    public Client(int numClient, String raisonSociale, String siren, String codeApe, String adresse, String telClient, String email, int dureeDeplacement, int distanceKm, ArrayList<Materiel> lesMateriels, ContratMaintenance leContrat) {
        this.numClient = numClient;
        this.raisonSociale = raisonSociale;
        this.siren = siren;
        this.codeApe = codeApe;
        this.adresse = adresse;
        this.telClient = telClient;
        this.email = email;

        this.dureeDeplacement = dureeDeplacement;
        this.distanceKm = distanceKm;
        this.lesMateriels = lesMateriels;
        this.leContrat = leContrat;
    }

    /**
     * Obtient la liste des matériels du client.
     *
     * @return La liste des matériels du client.
     */
    public ArrayList<Materiel> getMateriels() {
        return lesMateriels;
    }

    /**
     * Définit le contrat de maintenance du client.
     *
     * @param leContrat Le contrat de maintenance à définir.
     */
    public void setContratMaintenance(ContratMaintenance leContrat) {
        if (!aUnContratMaintenance()) {
            this.leContrat = leContrat;
        }
    }

    /**
     * Obtient le contrat de maintenance du client.
     *
     * @return Le contrat de maintenance du client.
     */
    public ContratMaintenance getContratMaintenance() {
        return leContrat;
    }

    /**
     * Vérifie si le client a un contrat de maintenance.
     *
     * @return True si le client a un contrat de maintenance, sinon False.
     */
    public boolean aUnContratMaintenance() {
        return leContrat != null;
    }

    /**
     * Obtient l'identifiant du client.
     *
     * @return L'identifiant du client.
     */
    public int getId() {
        return numClient;
    }

    /**
     * Obtient l'email du client.
     *
     * @return L'email du client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtient la raison sociale du client.
     *
     * @return La raison sociale du client.
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }
}