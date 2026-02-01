package com.cropsense.service;

import com.cropsense.model.Product;
import java.util.List;

public class InventoryService {

    private ProductService productService;

    // Constructor: receive ProductService
    public InventoryService(ProductService productService) {
        this.productService = productService;
    }

    // Get all products (via ProductService)
    public List<Product> getInventory() {
        return productService.getAllProducts();
    }

    // Add stock to a product
    public boolean addStock(String productId, int qty) {

        if (qty <= 0) {
            return false;
        }

        List<Product> products = productService.getAllProducts();

        for (Product p : products) {

            if (p.getProductId().equals(productId)) {

                int newQty = p.getQuantity() + qty;
                p.setQuantity(newQty);

                // Reuse ProductService saving logic
                productService.purchaseProduct(productId, 0);

                return true;
            }
        }

        return false; // Product not found
    }

    // Remove stock (admin side)
    public boolean removeStock(String productId, int qty) {

        if (qty <= 0) {
            return false;
        }

        List<Product> products = productService.getAllProducts();

        for (Product p : products) {

            if (p.getProductId().equals(productId)) {

                if (p.getQuantity() >= qty) {

                    p.setQuantity(p.getQuantity() - qty);

                    // Save changes
                    productService.purchaseProduct(productId, 0);

                    return true;
                }

                return false; // Not enough stock
            }
        }

        return false; // Product not found
    }
}
