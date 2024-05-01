package com.cashcash;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContratMaintenanceTest {    
    
    private ContratMaintenance contratMaintenance;

    @BeforeEach
    public void setUp() {
        // Initialisation des données de test
        LocalDate dateSignature = LocalDate.of(2023, 1, 1);
        LocalDate dateEcheance = LocalDate.of(2024, 1, 1);

        // Création d'un contrat de maintenance pour chaque test
        contratMaintenance = new ContratMaintenance(1, Date.valueOf(dateSignature), Date.valueOf(dateEcheance));
    }

    @Test
    public void testGetJoursRestants() {
        LocalDate now = LocalDate.now();
        long joursRestants = ChronoUnit.DAYS.between(contratMaintenance.getDateEcheance(), now);
        assertEquals(joursRestants, contratMaintenance.getJoursRestants());
    }
}