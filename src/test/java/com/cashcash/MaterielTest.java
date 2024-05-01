package com.cashcash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MaterielTest {

    private Materiel materiel;

    @BeforeEach
    public void setUp() {
        materiel = createTestMateriel();
    }

    @Test
    public void testSetContractNum() {
        materiel.setContractNum(9999);
        assertEquals(9999, materiel.getContractNum());
    }

    @Test
    public void testXmlMaterielWithNbJourAvantEcheance() {
        String xmlResult = materiel.xmlMateriel(10);
        String expectedXml = "\t<materiel numSerie=\"1234\">\n" +
                "\t\t<type refInterne=\"refInterneTest\" libelle=\"libelleTest\" />\n" +
                "\t\t<famille codeFamille=\"SE\" libelle=\"serveur\" />\n" +
                "\t\t<date_installation>" + LocalDate.now() + "</date_installation>\n" +
                "\t\t<prix_vente>1000.0</prix_vente>\n" +
                "\t\t<emplacement>Emplacement test</emplacement>\n" +
                "\t\t<nbJourAvantEcheance>10</nbJourAvantEcheance>\n" +
                "\t</materiel>";
        assertEquals(expectedXml, xmlResult);
    }

    @Test
    public void testXmlMateriel() {
        String xmlResult = materiel.xmlMateriel();
        String expectedXml = "\t<materiel numSerie=\"1234\">\n" +
            "\t\t<type refInterne=\"refInterneTest\" libelle=\"libelleTest\" />\n" +
            "\t\t<famille codeFamille=\"SE\" libelle=\"serveur\" />\n" +
            "\t\t<date_installation>" + LocalDate.now() + "</date_installation>\n" +
            "\t\t<prix_vente>1000.0</prix_vente>\n" +
            "\t\t<emplacement>Emplacement test</emplacement>\n" +
            "\t</materiel>";
        assertEquals(expectedXml, xmlResult);
    }

    private Materiel createTestMateriel() {
        return new Materiel(
            1234, // Numéro de série
            Date.valueOf(LocalDate.now()), // Date de vente
            Date.valueOf(LocalDate.now()), // Date d'installation
            1000.0, // Prix de vente
            "Emplacement test", // Emplacement
            new TypeMateriel("refInterneTest", "libelleTest"), // Type de matériel
            9876 // Numéro de contrat
        );
    }
}
