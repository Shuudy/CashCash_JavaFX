package com.cashcash;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class ContratMaintenance {
    private int numContrat;
    private LocalDate dateSignature, dateEcheance;
    private ArrayList<Materiel> lesMaterielsAssures;

    public ContratMaintenance(int numContrat, Date dateSignature, Date dateEcheance) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature.toLocalDate();
        this.dateEcheance = dateEcheance.toLocalDate();
        this.lesMaterielsAssures = new ArrayList<Materiel>();
    }

    public int getNumContrat() {
        return numContrat;
    }

    public int getJoursRestants() {
        LocalDate now = LocalDate.now();
        long joursRestants = dateEcheance.until(now, ChronoUnit.DAYS);
        return Math.toIntExact(joursRestants);
    }

    public boolean estValide() {
        LocalDate now = LocalDate.now();
        return now.isAfter(dateSignature) && now.isBefore(dateEcheance);
    }

    public void ajouteMateriel(Materiel unMateriel) {
        if(dateSignature.isBefore(unMateriel.getDateInstallation())) {
            lesMaterielsAssures.add(unMateriel);
        }
    }
}
