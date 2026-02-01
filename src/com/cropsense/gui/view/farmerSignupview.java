package com.cropsense.gui.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class FarmerSignupView {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/cropsense/gui/fxml/FarmerSignup.fxml")
            );
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Farmer Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        try (FileWriter writer = new FileWriter("farmers.txt", true)) {
            writer.write(username + "," + password + "\n");
            showAlert("Success", "Farmer account created!");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not save farmer account.");
        }
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        new LoginView("FARMER").show(stage);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
