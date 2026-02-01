package com.cropsense.service;

import com.cropsense.model.Product;
import com.cropsense.util.FileHandler;
import java.util.List;

public class ProductService {

    private List<Product> products;

    public ProductService() {
        products = FileHandler.loadProducts();
    }

    // READ
    public List<Product> getAllProducts() {
        return products;
    }

    // BUYER ACTION (already existed)
    public boolean purchaseProduct(String id, int qty) {
        for (Product p : products) {
            if (p.getProductId().equals(id)) {
                if (p.getQuantity() >= qty && qty > 0) {
                    p.setQuantity(p.getQuantity() - qty);
                    FileHandler.saveProducts(products);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    // CREATE (ADDED)
    public void addProduct(Product product) {
        products.add(product);
        FileHandler.saveProducts(products);
    }

    // UPDATE (ADDED)
    public void updateProduct(Product updatedProduct) {
        for (Product p : products) {
            if (p.getProductId().equals(updatedProduct.getProductId())) {
                p.setPrice(updatedProduct.getPrice());
                p.setQuantity(updatedProduct.getQuantity());
                FileHandler.saveProducts(products);
                return;
            }
        }
    }

    // DELETE (ADDED)
    public void deleteProduct(Product product) {
        products.removeIf(p -> p.getProductId().equals(product.getProductId()));
        FileHandler.saveProducts(products);
    }
}
