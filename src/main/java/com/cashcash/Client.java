package com.cashcash;

public class Client {
    String raisonSociale, siren, codeApe, adresse, telClient, email;
    int numClient, dureeDeplacement, distanceKm;

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

    public int getId() {
        return numClient;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }
}