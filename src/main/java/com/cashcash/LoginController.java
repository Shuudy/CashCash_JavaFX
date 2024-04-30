package com.cashcash;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label label_msg;

    @FXML
    void handleButtonAuth(MouseEvent event) throws IOException {

        String email = txtfield_email.getText();
        String password = txtfield_password.getText();
        
        if (!email.isEmpty() && !password.isEmpty()) {
            if (email.equals("test") && password.equals("test")) {
                App.setRoot("primary");
            } else {
                label_msg.setText("L'adresse e-mail ou le mot de passe sont incorrect.");
            }
        } else {
            label_msg.setText("Les champs doivent être remplis.");
        }

    }
}
