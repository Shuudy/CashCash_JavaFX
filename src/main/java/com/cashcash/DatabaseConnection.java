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
		Connection conn = new DatabaseConnection().getConnection();
		ObservableList<Client> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM clients");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				// On parcours les matériels du client que l'on parcours
				PreparedStatement ps1 = conn.prepareStatement("SELECT m.id, m.saleDate, m.installationDate, m.salePrice, m.location, mt.internalRef, mt.label FROM materials m, materialstypes mt WHERE mt.internalRef=m.internalRef AND m.clientNum = ?");
				ps1.setInt(1, rs.getInt("id"));
				ResultSet rs1 = ps1.executeQuery();

				ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
				while (rs1.next()) {
					TypeMateriel tm = new TypeMateriel(rs1.getString("internalRef"), rs1.getString("label"));
					Materiel m = new Materiel(rs1.getInt("id"), rs1.getDate("saleDate"), rs1.getDate("installationDate"), rs1.getDouble("salePrice"), rs1.getString("location"), tm);
					lesMateriels.add(m);
				}				

				list.add(
					new Client(
						rs.getInt("id"),
						rs.getString("socialReason"),
						rs.getString("sirenNum"),
						rs.getString("apeCode"),
						rs.getString("address"),
						rs.getString("phoneNumber"),
						rs.getString("mailAddress"),
						rs.getInt("travelTime"),
						rs.getInt("distanceKm"),
						lesMateriels
					));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return list;
	}

}

