/*package com.cropsense.gui.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginView {

    private String role;

    public LoginView(String role) {
        this.role = role;
    }

    public void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/cropsense/gui/fxml/Loginview.fxml")
            );

            loader.setController(this);
            Parent root = loader.load();

            stage.setScene(new Scene(root));
            stage.setTitle("CropSense - " + role + " Login");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField usernameField;
    
    @FXML
    private Button signupButton;
    @FXML
    private void initialize() {
        if (role.equals("FARMER")) {
            signupButton.setVisible(false);
            signupButton.setManaged(false);
        }
    }
    @FXML
    private void handleFarmerSignup() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        new FarmerSignupView().show(stage);
    }


    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();

            // ===== BUYER LOGIN =====
            if (role.equals("BUYER")) {

                boolean authenticated = false;

                try (BufferedReader reader = new BufferedReader(new FileReader("buyers.txt"))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length == 2) {
                            if (parts[0].equals(username) && parts[1].equals(password)) {
                                authenticated = true;
                                break;
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (authenticated) {
                    FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/cropsense/gui/fxml/Buyer.fxml")
                    );
                    stage.setScene(new Scene(loader.load()));
                    stage.setTitle("Buyer Dashboard");
                } else {
                    showAlert("Login Failed", "Invalid buyer credentials.");
                }

            }
            else if (role.equals("FARMER")) {

                boolean authenticated = false;

                try (BufferedReader reader = new BufferedReader(new FileReader("farmers.txt"))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length == 2) {
                            if (parts[0].equals(username) && parts[1].equals(password)) {
                                authenticated = true;
                                break;
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (authenticated) {
                    FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/cropsense/gui/fxml/Farmer.fxml")
                    );
                    stage.setScene(new Scene(loader.load()));
                    stage.setTitle("Farmer Dashboard");
                } else {
                    showAlert("Login Failed", "Invalid farmer credentials.");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void handleSignup() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        new BuyerSignupView().show(stage);
    }

    // ===== ALERT HELPER =====
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}*/
package com.cropsense.gui.view;

import com.cropsense.model.User;
import com.cropsense.model.Buyer;
import com.cropsense.model.Farmer;
import com.cropsense.service.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginView {

    private String role;
    private UserService userService = new UserService();

    public LoginView(String role) {
        this.role = role;

        // Demo users for testing (you can remove later)
        userService.registerUser(new Buyer("b1", "buyer1", "BUYER"));
        userService.registerUser(new Farmer("f1", "farmer1", "FARMER"));
    }

    public void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/cropsense/gui/fxml/Loginview.fxml")
            );
            loader.setController(this);
            Parent root = loader.load();

            stage.setScene(new Scene(root));
            stage.setTitle("CropSense - " + role + " Login");
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
        if (role.equals("FARMER")) {
            signupButton.setVisible(false);
            signupButton.setManaged(false);
        }
    }

    @FXML
    private void handleLogin() {
        String name = usernameField.getText();
        String roleInput = passwordField.getText(); // using password field for role

        User user = userService.login(name, roleInput);

        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();

            if (user != null) {
                if (user instanceof Buyer) {
                    FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/cropsense/gui/fxml/Buyer.fxml")
                    );
                    stage.setScene(new Scene(loader.load()));
                    stage.setTitle("Buyer Dashboard");
                } else if (user instanceof Farmer) {
                    FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/cropsense/gui/fxml/Farmer.fxml")
                    );
                    stage.setScene(new Scene(loader.load()));
                    stage.setTitle("Farmer Dashboard");
                }
            } else {
                showAlert("Login Failed", "Invalid credentials.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignup() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        new BuyerSignupView().show(stage);
    }

    @FXML
    private void handleFarmerSignup() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        new FarmerSignupView().show(stage);
    }

    // ===== ALERT HELPER =====
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

