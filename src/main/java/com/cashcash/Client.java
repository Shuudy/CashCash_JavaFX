package com.cashcash;

import java.util.ArrayList;

public class Client {
    private String raisonSociale, siren, codeApe, adresse, telClient, email;
    private int numClient, dureeDeplacement, distanceKm;

    private ArrayList<Materiel> lesMateriels;
    private ContratMaintenance leContrat;

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

    public ArrayList<Materiel> getMateriels() {
        return lesMateriels;
    }

    public void setContratMaintenance(ContratMaintenance leContrat) {
        if (!aUnContratMaintenance()) {
            this.leContrat = leContrat;
        }
    }

    public ContratMaintenance getContratMaintenance() {
        return leContrat;
    }

    public boolean aUnContratMaintenance() {
        return leContrat != null;
    }

    public int getId() {
        return numClient;
    }

    public String getEmail() {
        return email;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }
}