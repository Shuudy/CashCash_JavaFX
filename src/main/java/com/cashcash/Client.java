package com.cashcash;

import java.util.ArrayList;

public class Client {
    private String raisonSociale, siren, codeApe, adresse, telClient, email;
    private int numClient, dureeDeplacement, distanceKm;

    private ArrayList<Materiel> lesMateriels;

    public Client(int numClient, String raisonSociale, String siren, String codeApe, String adresse, String telClient, String email, int dureeDeplacement, int distanceKm) {
        this.numClient = numClient;
        this.raisonSociale = raisonSociale;
        this.siren = siren;
        this.codeApe = codeApe;
        this.adresse = adresse;
        this.telClient = telClient;
        this.email = email;

        this.dureeDeplacement = dureeDeplacement;
        this.distanceKm = distanceKm;
    }

    public ArrayList<Materiel> getMateriels() {
        return lesMateriels;
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