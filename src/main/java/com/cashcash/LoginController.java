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

/**
 * Contrôleur pour la vue login.fxml, gérant l'authentification des employés.
 */
public class LoginController {

    @FXML
    private Button button_submit;

    @FXML
    private TextField txtfield_email;

    @FXML
    private PasswordField txtfield_password;

    /**
     * Gère l'événement de clic sur le bouton d'authentification.
     *
     * @param event L'événement de clic de la souris.
     * @throws IOException  En cas d'erreur lors de la redirection vers une autre vue.
     * @throws SQLException En cas d'erreur lors de la communication avec la base de données.
     */
    @FXML
    void handleButtonAuth(MouseEvent event) throws IOException, SQLException {

        // Récupération des informations saisies
        String email = txtfield_email.getText();
        String password = txtfield_password.getText();
        Alert a = new Alert(AlertType.ERROR);
        a.setHeaderText(null);

        // Vérification si les champs sont remplis
        if (!email.isEmpty() && !password.isEmpty()) {
            Connection cn = new DatabaseConnection().getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT lastName, firstName, password FROM employees WHERE mailAddress = ? AND id NOT IN (SELECT id FROM technicians)");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Vérification du mot de passe
                String passwordFromDB = rs.getString("password");
                if (BCrypt.checkpw(password, passwordFromDB)) {
                    // Authentification réussie
                    a.setAlertType(AlertType.INFORMATION);
                    a.setContentText("Bienvenue " + rs.getString("lastName") + " " + rs.getString("firstName") + " !");
                    a.show();

                    // Redirection vers la vue principale
                    App.setRoot("primary");
                } else {
                    // Mot de passe incorrect
                    a.setContentText("L'adresse e-mail ou le mot de passe sont incorrect.");
                    a.show();
                }
            } else {        
                // Utilisateur non trouvé
                a.setContentText("L'adresse e-mail ou le mot de passe sont incorrect.");
                a.show();
            }
        } else {
            // Champs non remplis
            a.setContentText("Les champs doivent être remplis.");
            a.show();
        }
    }
}
