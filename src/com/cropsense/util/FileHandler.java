package com.cropsense.util;

import com.cropsense.model.Product;
import java.io.*;
import java.util.*;
public class FileHandler {




        public static List<Product> loadProducts() {
            List<Product> list = new ArrayList<>();
            File file = new File(constants.PRODUCT_FILE);

            if (!file.exists()) return list;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] d = line.split(",");
                    list.add(new Product(
                            d[0], d[1],
                            Double.parseDouble(d[2]),
                            Integer.parseInt(d[3])
                    ));
                }
            } catch (IOException e) {
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
