package com.cropsense.gui.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginView {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {

        if(usernameField.getText().equals("buyer")
                && passwordField.getText().equals("123")) {

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/cropsense/gui/view/BuyerDashboard.fxml")
                );

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Username or Password");
            alert.show();
        }
    }

    public void show(Stage stage) {
    }
}
