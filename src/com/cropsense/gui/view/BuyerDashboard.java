package com.cropsense.gui.view;

import com.cropsense.model.Product;
import com.cropsense.service.ProductService;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyerDashboard implements Initializable {

    private final ProductService service = new ProductService();

    @FXML private TextArea area;
    @FXML private TextField idField;
    @FXML private TextField qtyField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
    }

    @FXML
    private void handleBuy() {
        try {
            boolean success = service.purchaseProduct(
                    idField.getText().trim(),
                    Integer.parseInt(qtyField.getText().trim())
            );
            show(success ? "Purchase Successful" : "Purchase Failed");
            refresh();
        } catch (Exception e) {
            show("Invalid input");
        }
    }

    private void refresh() {
        area.clear();
        for (Product p : service.getAllProducts()) {
            area.appendText(
                    p.getProductId() + " | " +
                            p.getProductName() + " | " +
                            p.getPrice() + " | " +
                            p.getQuantity() + "\n"
            );
        }
    }

    private void show(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
