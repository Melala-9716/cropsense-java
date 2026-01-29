package com.cropsense.util;

import com.cropsense.model.Product;
import java.io.*;
import java.util.*;

public class FileHandler {

    public static List<Product> loadProducts() {
        List<Product> list = new ArrayList<>();

        try {
            File file = new File(constants.PRODUCT_FILE);

            // Create file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
                // Optionally add default products
                List<Product> defaultProducts = Arrays.asList(
                        new Product("c001", "Maize", 45.0, 120),
                        new Product("c002", "Wheat", 55.0, 90),
                        new Product("c003", "Teff", 85.0, 60),
                        new Product("c004", "Sorghum", 40.0, 150),
                        new Product("c005", "Barley", 50.0, 100)
                );
                saveProducts(defaultProducts);
                return defaultProducts;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        String[] d = line.split(",");
                        if (d.length == 4) {
                            list.add(new Product(
                                    d[0].trim(),
                                    d[1].trim(),
                                    Double.parseDouble(d[2].trim()),
                                    Integer.parseInt(d[3].trim())
                            ));
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveProducts(List<Product> products) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(constants.PRODUCT_FILE))) {
            for (Product p : products) {
                pw.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}