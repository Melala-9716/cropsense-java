package com.cropsense.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class BuyerSignupView {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleSignup() {
        try (FileWriter writer = new FileWriter("buyers.txt", true)) {
            writer.write(usernameField.getText() + "," + passwordField.getText() + "\n");
            show("Success", "Buyer account created");
        } catch (IOException e) {
            show("Error", "Failed to save buyer");
        }
    }

    @FXML
    void handleBack() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        new LoginView("BUYER").show(stage);
    }

    private void show(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
