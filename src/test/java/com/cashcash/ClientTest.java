package com.cashcash;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTest {
    
    private Client client;
    private ArrayList<Materiel> materiels;
    private ContratMaintenance contratMaintenance;

    @BeforeEach
    public void setUp() {
        // Initialisation des données de test
        materiels = new ArrayList<>();
        LocalDate dateVente1 = LocalDate.of(2022, 1, 15);
        LocalDate dateVente2 = LocalDate.of(2022, 2, 20);
        LocalDate dateInstallation1 = LocalDate.of(2022, 1, 15);
        LocalDate dateInstallation2 = LocalDate.of(2022, 2, 20);
        Materiel materiel1 = new Materiel(1, Date.valueOf(dateVente1), Date.valueOf(dateInstallation1), 1000.0, "Emplacement1", null, 0);
        Materiel materiel2 = new Materiel(2, Date.valueOf(dateVente2), Date.valueOf(dateInstallation2), 2000.0, "Emplacement2", null, 0);
        materiels.add(materiel1);
        materiels.add(materiel2);
        
        contratMaintenance = new ContratMaintenance(1, Date.valueOf("2022-01-01"), Date.valueOf("2023-01-01"));

        // Création d'un client pour chaque test
        client = new Client(1, "RaisonSocialeTest", "SIRENTest", "APETest", "AdresseTest", "TelTest", "EmailTest", 2, 50, materiels, contratMaintenance);
    }

    @Test
    public void testGetMateriels() {
        assertEquals(materiels, client.getMateriels());
    }

    @Test
    public void testSetContratMaintenance() {
        ContratMaintenance newContratMaintenance = new ContratMaintenance(2, Date.valueOf("2023-01-01"), Date.valueOf("2024-01-01"));
        client.setContratMaintenance(newContratMaintenance);
        assertNotEquals(newContratMaintenance, client.getContratMaintenance());
    }

    @Test
    public void testGetContratMaintenance() {
        assertEquals(contratMaintenance, client.getContratMaintenance());
    }

    @Test
    public void testAUnContratMaintenance() {
        assertTrue(client.aUnContratMaintenance());
    } 
}
