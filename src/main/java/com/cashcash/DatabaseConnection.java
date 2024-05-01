package com.cashcash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Gère la connexion à la base de données et fournit des méthodes pour récupérer des données.
 */
public class DatabaseConnection {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cashcash";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
	
	private Connection databaseLink;
	
	/**
     * Établit une connexion à la base de données.
     *
     * @return La connexion à la base de données.
     */
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return databaseLink;
	}
	
	/**
     * Récupère les données des clients depuis la base de données.
     *
     * @return Une liste observable des clients.
     */
	public static ObservableList<Client> getDataClients() {
		DatabaseConnection conn = new DatabaseConnection();
		GestionMateriels gm = new GestionMateriels(conn);
		ObservableList<Client> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement ps = conn.getConnection().prepareStatement("SELECT * FROM clients");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(gm.getClient(rs.getInt("id")));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return list;
	}

	/**
     * Récupère les matériels associés à un client depuis la base de données.
     *
     * @param id       L'identifiant du client.
     * @param contrat  Indique si seuls les matériels avec contrat doivent être récupérés.
     * @return Une liste observable des matériels associés au client.
     */
	public static ObservableList<Materiel> getMaterielForClient(int id, boolean contrat) {
		DatabaseConnection conn = new DatabaseConnection();
		GestionMateriels gm = new GestionMateriels(conn);
		ObservableList<Materiel> list = FXCollections.observableArrayList();
		
		try {
			for (Materiel unMateriel : gm.getMateriels(id)) {
				if (contrat && unMateriel.getContractNum() != 0) {
					list.add(unMateriel);
				} else if (!contrat && unMateriel.getContractNum() == 0){
					list.add(unMateriel);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}