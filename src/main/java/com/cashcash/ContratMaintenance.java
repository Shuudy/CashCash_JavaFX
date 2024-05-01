package com.cashcash;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

/**
 * Représente un contrat de maintenance.
 * Cette classe stocke les informations relatives à un contrat de maintenance, telles que son numéro,
 * sa date de signature, sa date d'échéance et les matériels assurés.
 */
public class ContratMaintenance {
    private int numContrat;
    private LocalDate dateSignature, dateEcheance;
    private ArrayList<Materiel> lesMaterielsAssures;

    /**
     * Constructeur de la classe ContratMaintenance.
     *
     * @param numContrat     Le numéro du contrat de maintenance.
     * @param dateSignature  La date de signature du contrat de maintenance.
     * @param dateEcheance   La date d'échéance du contrat de maintenance.
     */
    public ContratMaintenance(int numContrat, Date dateSignature, Date dateEcheance) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature.toLocalDate();
        this.dateEcheance = dateEcheance.toLocalDate();
        this.lesMaterielsAssures = new ArrayList<Materiel>();
    }

    /**
     * Obtient le numéro du contrat de maintenance.
     *
     * @return Le numéro du contrat de maintenance.
     */
    public int getNumContrat() {
        return numContrat;
    }

    /**
     * Calcule le nombre de jours restants avant l'échéance du contrat de maintenance.
     *
     * @return Le nombre de jours restants avant l'échéance.
     */
    public int getJoursRestants() {
        LocalDate now = LocalDate.now();
        long joursRestants = dateEcheance.until(now, ChronoUnit.DAYS);
        return Math.toIntExact(joursRestants);
    }

    /**
     * Vérifie si le contrat de maintenance est valide à la date actuelle.
     *
     * @return True si le contrat est valide, sinon False.
     */
    public boolean estValide() {
        LocalDate now = LocalDate.now();
        return now.isAfter(dateSignature) && now.isBefore(dateEcheance);
    }

    /**
     * Ajoute un matériel assuré au contrat de maintenance.
     * Le matériel est ajouté au contrat seulement s'il a été installé après la date de signature du contrat.
     *
     * @param unMateriel Le matériel à ajouter au contrat de maintenance.
     */
    public void ajouteMateriel(Materiel unMateriel) {
        if(dateSignature.isBefore(unMateriel.getDateInstallation())) {
            lesMaterielsAssures.add(unMateriel);
        }
    }
}
