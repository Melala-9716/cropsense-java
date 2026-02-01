package com.cropsense.gui.view;

import com.cropsense.model.Product;
import com.cropsense.service.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BuyerDashboard implements Initializable {

    private ProductService service = new ProductService();

    @FXML
    private TextArea area;

    @FXML
    private TextField idField;

    @FXML
    private TextField qtyField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
    }

    @FXML
    private void handleBuy() {
        try {
            String id = idField.getText().trim();
            String qtyText = qtyField.getText().trim();

            if (id.isEmpty() || qtyText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Please enter both Product ID and Quantity");
                return;
            }

            int qty = Integer.parseInt(qtyText);
            if (qty <= 0) {
                showAlert(Alert.AlertType.ERROR, "Quantity must be positive");
                return;
            }

            boolean success = service.purchaseProduct(id, qty);

            Alert alert = new Alert(
                    success ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING
            );
            alert.setTitle("Purchase Result");
            alert.setContentText(
                    success ? "Purchase Successful!" : "Not enough stock or invalid Product ID"
            );
            alert.show();

            refresh();
            clearFields();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Please enter a valid number for quantity");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage());
        }
    }

    private void refresh() {
        area.clear();
        area.appendText("=== Available Products ===\n");
        area.appendText("ID  |  Name  |  Price  |  Stock\n");
        area.appendText("--------------------------------\n");

        for (Product p : service.getAllProducts()) {
            area.appendText(
                    String.format("%-6s | %-10s | $%-6.2f | %-4d\n",
                            p.getProductId(),
                            p.getProductName(),
                            p.getPrice(),
                            p.getQuantity()
                    )
            );
        }
    }

    private void clearFields() {
        idField.clear();
        qtyField.clear();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("CropSense");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}