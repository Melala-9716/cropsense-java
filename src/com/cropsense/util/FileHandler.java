package com.cropsense.util;

import com.cropsense.model.Product;
import java.io.*;
import java.util.*;

public class FileHandler {

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        File file = new File(constants.PRODUCT_FILE);

        try {
            if (!file.exists()) {
                products = List.of(
                        new Product("c001", "Maize", 45, 120),
                        new Product("c002", "Wheat", 55, 90),
                        new Product("c003", "Teff", 85, 60),
                        new Product("c004", "Sorghum", 40, 150),
                        new Product("c005", "Barley", 50, 100)
                );
                saveProducts(products);
                return new ArrayList<>(products);
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                products.add(new Product(
                        d[0], d[1],
                        Double.parseDouble(d[2]),
                        Integer.parseInt(d[3])
                ));
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void saveProducts(List<Product> products) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(constants.PRODUCT_FILE))) {
            for (Product p : products) pw.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
