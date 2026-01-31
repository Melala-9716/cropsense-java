package com.cropsense.gui.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class RoleSelectionView {

    public void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/cropsense/gui/fxml/RoleSelection.fxml")
            );

            Parent root = loader.load();

            stage.setScene(new Scene(root));
            stage.setTitle("CropSense - Select Role");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBuyer(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new LoginView("BUYER").show(stage);
    }

    @FXML
    private void handleFarmer(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new LoginView("FARMER").show(stage);
    }
}
