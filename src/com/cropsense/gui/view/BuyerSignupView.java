package com.cropsense.gui.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Parent;



public class BuyerSignupView {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        try (FileWriter writer = new FileWriter("buyers.txt", true)) {
            writer.write(username + "," + password + "\n");
            showAlert("Success", "Account created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not save account.");
        }
    }
    public void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/cropsense/gui/fxml/BuyerSignup.fxml")
            );

            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Buyer Sign Up");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleBack() {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/cropsense/gui/fxml/Loginview.fxml")
            );
            loader.setController(new LoginView("BUYER"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Buyer Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
