package com.cashcash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button button_submit;

    @FXML
    private TextField txtfield_email;

    @FXML
    private PasswordField txtfield_password;

    @FXML
    void handleButtonAuth(MouseEvent event) throws IOException, SQLException {

        String email = txtfield_email.getText();
        String password = txtfield_password.getText();
        Alert a = new Alert(AlertType.ERROR);
        a.setHeaderText(null);

        if (!email.isEmpty() && !password.isEmpty()) {
            Connection cn = new DatabaseConnection().getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT lastName, firstName, password FROM employees WHERE mailAddress = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String passwordFromDB = rs.getString("password");
                if (BCrypt.checkpw(password, passwordFromDB)) {
                    a.setAlertType(AlertType.INFORMATION);
                    a.setContentText("Bienvenue " + rs.getString("lastName") + " " + rs.getString("firstName") + " !");
                    a.show();

                    App.setRoot("primary");
                } else {
                    a.setContentText("L'adresse e-mail ou le mot de passe sont incorrect.");
                    a.show();
                }
            } else {            
                a.setContentText("L'adresse e-mail ou le mot de passe sont incorrect.");
                a.show();
            }
        } else {
            a.setContentText("Les champs doivent être remplis.");
            a.show();
        }
    }
}
