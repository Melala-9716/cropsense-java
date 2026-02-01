package com.cropsense.gui.view;

import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoginView {

    private String role;

    public LoginView(String role) {
        this.role = role;
    }

    public LoginView() {}

    public void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/cropsense/gui/fxml/Loginview.fxml")
            );
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("CropSense - Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button signupButton;

    @FXML
    private void initialize() {
        if ("FARMER".equals(role)) {
            signupButton.setVisible(false);
            signupButton.setManaged(false);
        }
    }

    @FXML
    private void handleLogin() {
        String file = role.equals("BUYER") ? "buyers.txt" : "farmers.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p[0].equals(usernameField.getText())
                        && p[1].equals(passwordField.getText())) {

                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    String fxml = role.equals("BUYER") ? "Buyer.fxml" : "Farmer.fxml";
                    stage.setScene(new Scene(
                            FXMLLoader.load(getClass().getResource("/com/cropsense/gui/fxml/" + fxml))
                    ));
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        alert("Login Failed", "Invalid credentials");
    }

    @FXML
    private void handleSignup() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        new BuyerSignupView().handleBack();
    }

    private void alert(String t, String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(t);
        a.setContentText(m);
        a.showAndWait();
    }
}
