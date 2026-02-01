package com.cropsense.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.cropsense.model.Product;
import com.cropsense.model.Farmer;
import com.cropsense.service.ProductService;

public class FarmerDashboard {

    private Farmer farmer;

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    @FXML private TextField productIdField;
    @FXML private TextField productNameField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;
    @FXML private ListView<Product> productListView;

    private ProductService productService = new ProductService();

    @FXML
    public void initialize() {
        productListView.getItems().setAll(productService.getAllProducts());

        productListView.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, selected) -> {
                if (selected != null) {
                    productIdField.setText(selected.getProductId());
                    productNameField.setText(selected.getProductName());
                    priceField.setText(String.valueOf(selected.getPrice()));
                    quantityField.setText(String.valueOf(selected.getQuantity()));
                }
            }
        );
    }

    @FXML
    private void handleAddProduct() {
        Product product = new Product(
                productIdField.getText(),
                productNameField.getText(),
                Double.parseDouble(priceField.getText()),
                Integer.parseInt(quantityField.getText())
        );

        productService.addProduct(product);
        productListView.getItems().add(product);
        clearFields();
    }

    @FXML
    private void handleUpdateProduct() {
        Product selected = productListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setPrice(Double.parseDouble(priceField.getText()));
            selected.setQuantity(Integer.parseInt(quantityField.getText()));

            productService.updateProduct(selected);
            productListView.refresh();
        }
    }

    @FXML
    private void handleDeleteProduct() {
        Product selected = productListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            productService.deleteProduct(selected);
            productListView.getItems().remove(selected);
        }
    }

    private void clearFields() {
        productIdField.clear();
        productNameField.clear();
        priceField.clear();
        quantityField.clear();
    }
}
