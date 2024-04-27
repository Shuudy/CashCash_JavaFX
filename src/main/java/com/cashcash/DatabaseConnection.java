package com.cashcash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseConnection {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cashcash";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
	
	private Connection databaseLink;
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return databaseLink;
	}
	
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

	public static ObservableList<Materiel> getMaterielForClient(int id, boolean contrat) {
		DatabaseConnection conn = new DatabaseConnection();
		GestionMateriels gm = new GestionMateriels(conn);
		ObservableList<Materiel> list = FXCollections.observableArrayList();
		
		try {
			for (Materiel unMateriel : gm.getMateriels(id)) {
				if (contrat && unMateriel.getContractNum() == 0) {
					list.add(unMateriel);
				} else if (!contrat){
					list.add(unMateriel);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}