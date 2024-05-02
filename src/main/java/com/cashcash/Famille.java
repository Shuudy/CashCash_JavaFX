package com.cashcash;

/**
 * Représente une famille pour un type de matériel
 * Cette classe stocke les informations relatives à une famille, telles que son code
 * et son libellé.
 */
public class Famille {
    private String codeFamille, libelleFamille;

    /**
     * Constructeur de la classe Famille.
     *
     * @param codeFamille     Le code de la famille.
     * @param libelleFamille  Le libellé de la famille.
     */
    public Famille(String codeFamille, String libelleFamille) {
        this.codeFamille = codeFamille;
        this.libelleFamille = libelleFamille;
    }
}
