package com.cropsense.service;

import com.cropsense.model.Product;
import com.cropsense.util.FileHandler;
import java.util.List;

public class InventoryService {

    private List<Product> products;

    public InventoryService() {
        products = FileHandler.loadProducts();
    }

    public List<Product> getInventory() {
        return products;
    }

    public boolean reduceStock(String productId, int qty) {
        for (Product p : products) {
            if (p.getProductId().equals(productId) && p.getQuantity() >= qty) {
                p.setQuantity(p.getQuantity() - qty);
                FileHandler.saveProducts(products);
                return true;
            }
        }
        return false;
    }
}
