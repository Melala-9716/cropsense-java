package com.cropsense.model;

// Farmer class extends User and represents a user with the role "FARMER"
public class Farmer extends User {
    public Farmer(String id, String name, String role) {
        // Calls the User constructor with id, name, and fixed role "FARMER"
        super(id, name, "FARMER");
    }
}

