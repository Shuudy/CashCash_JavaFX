package com.cashcash;

public class TypeMateriel {
    private String referenceInterne, libelleTypeMateriel;

    public TypeMateriel(String referenceInterne, String libelleTypeMateriel){
        this.referenceInterne = referenceInterne;
        this.libelleTypeMateriel = libelleTypeMateriel;
    }

    public String getReferenceInterne() {
        return referenceInterne;
    }

    public String getLibelleTypeMateriel() {
        return libelleTypeMateriel;
    }
}
