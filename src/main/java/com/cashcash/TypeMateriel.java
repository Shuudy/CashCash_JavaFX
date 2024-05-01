package com.cashcash;

/**
 * Représente un type de matériel.
 * Cette classe stocke les informations relatives à un type de matériel, telles que sa référence interne
 * et son libellé.
 */
public class TypeMateriel {
    private String referenceInterne, libelleTypeMateriel;

    /**
     * Constructeur de la classe TypeMateriel.
     *
     * @param referenceInterne     La référence interne du type de matériel.
     * @param libelleTypeMateriel  Le libellé du type de matériel.
     */
    public TypeMateriel(String referenceInterne, String libelleTypeMateriel){
        this.referenceInterne = referenceInterne;
        this.libelleTypeMateriel = libelleTypeMateriel;
    }

    /**
     * Obtient la référence interne du type de matériel.
     *
     * @return La référence interne du type de matériel.
     */
    public String getReferenceInterne() {
        return referenceInterne;
    }

    /**
     * Obtient le libellé du type de matériel.
     *
     * @return Le libellé du type de matériel.
     */
    public String getLibelleTypeMateriel() {
        return libelleTypeMateriel;
    }
}
