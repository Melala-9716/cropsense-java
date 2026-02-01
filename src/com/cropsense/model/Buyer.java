package com.cropsense.model;

// Buyer class extends User and represents a user with the role "BUYER"
public class Buyer extends User {
    public Buyer(String id, String name, String role) {
        // Calls the User constructor with id, name, and fixed role "BUYER"
        super(id, name, "BUYER");
    }
}

