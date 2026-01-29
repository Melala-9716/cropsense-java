package com.cropsense.service;

import com.cropsense.model.Product;
import com.cropsense.util.FileHandler;
import java.util.List;

public class ProductService {

    private List<Product> products;

    public ProductService() {
        products = FileHandler.loadProducts();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public boolean purchaseProduct(String id, int qty) {
        for (Product p : products) {
            if (p.getProductId().equals(id)) {
                if (p.getQuantity() >= qty && qty > 0) {
                    p.setQuantity(p.getQuantity() - qty);
                    FileHandler.saveProducts(products);
                    return true;
                }
                return false; // Not enough stock or invalid quantity
            }
        }
        return false; // Product ID not found
    }
}