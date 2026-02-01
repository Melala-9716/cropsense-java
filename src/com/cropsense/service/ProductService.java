package com.cropsense.service;

import com.cropsense.model.Product;
import java.util.List;

public class ProductService {

    private InventoryService inventory = new InventoryService();

    public List<Product> getAllProducts() {
        return inventory.getInventory();
    }

    public boolean purchaseProduct(String id, int qty) {
        return inventory.reduceStock(id, qty);
    }
}
