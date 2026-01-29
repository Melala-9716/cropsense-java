package com.cropsense.model;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private  int quantity;
     public Product(String productId, String productName, double price ,int quantity){
         this.productName=productName;
         this.productId=productId;
         this.price=price;
         this.quantity=quantity;
     }

    public String getProductId() {
        return productId;
    }
     public  String getProductName(){
         return productName;
     }
     public double getPrice(){
         return price;
     }
     public int getQuantity(){
         return quantity;
     }
     public void setPrice(double price){
         this.price=price;
     }
     public void setQuantity(int quantity){
         this.quantity=quantity;
     }
    public String toString(){
         return productId + ","+ productName + "," + price +"," + quantity;
    }



}
