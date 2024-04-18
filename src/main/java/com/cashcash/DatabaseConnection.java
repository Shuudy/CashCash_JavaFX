package com.cashcash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.com.cashcash.Client;

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
						rs.getInt("distanceKm")
					));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return list;
	}

}

