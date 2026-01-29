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
            boolean success = service.purchaseProduct(
                    idField.getText(),
                    Integer.parseInt(qtyField.getText())
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(
                    success ? "Purchase Successful" : "Not enough stock"
            );
            alert.show();

            refresh();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter valid inputs.");
            alert.show();
        }
    }

    private void refresh() {
        area.clear();
        for (Product p : service.getAllProducts()) {
            area.appendText(
                    p.getProductId() + " | " +
                    p.getProductName() + " | Price: " +
                    p.getPrice() + " | Stock: " +
                    p.getQuantity() + "\n"
            );
        }
    }
}
