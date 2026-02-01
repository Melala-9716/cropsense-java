package com.cropsense.service;

import com.cropsense.model.User;
import com.cropsense.model.Buyer;
import com.cropsense.model.Farmer;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<>();

    // Register a new user (Buyer or Farmer)
    public boolean registerUser(User user) {
        // avoid duplicate names
        for (User u : users) {
            if (u.getName().equals(user.getName())) {
                return false; // already exists
            }
        }
        users.add(user);
        return true;
    }

    // Authenticate login using name + role
    public User login(String name, String role) {
        for (User u : users) {
            if (u.getName().equals(name) && u.getRole().equals(role)) {
                return u; // return the user object (Buyer or Farmer)
            }
        }
        return null; // login failed
    }

    // Find user by ID
    public User findUserById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    // List all users
    public List<User> getAllUsers() {
        return users;
    }

    // Get all buyers
    public List<Buyer> getAllBuyers() {
        List<Buyer> buyers = new ArrayList<>();
        for (User u : users) {
            if (u instanceof Buyer) {
                buyers.add((Buyer) u);
            }
        }
        return buyers;
    }

    // Get all farmers
    public List<Farmer> getAllFarmers() {
        List<Farmer> farmers = new ArrayList<>();
        for (User u : users) {
            if (u instanceof Farmer) {
                farmers.add((Farmer) u);
            }
        }
        return farmers;
    }
}




