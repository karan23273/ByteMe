package com.example.guibilling;

import com.example.guibilling.CustomerPackage.Customer_Info;
import com.example.guibilling.CustomerPackage.Customer_data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    private static final String USER_FILE = "history.ser";

    // Save credentials to file
    public void writeData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            Customer_Info portal = new Customer_Info();
            Map<String, Customer_data> credentials = portal.getCredential();

            if (credentials == null || credentials.isEmpty()) {
                System.out.println("No data to save.");
                return;
            }

            oos.writeObject(credentials);
            System.out.println("Users saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load credentials from file
    @SuppressWarnings("unchecked")
    public void load() {
        File file = new File(USER_FILE);

        if (!file.exists()) {
            System.out.println("No saved data found.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Map<String, Customer_data> loadedMenu = (Map<String, Customer_data>) ois.readObject();

            if (loadedMenu == null || loadedMenu.isEmpty()) {
                System.out.println("No data loaded from file.");
                return;
            }

            Customer_Info portal = new Customer_Info();
            portal.setCredential((HashMap<String, Customer_data>) loadedMenu);
            System.out.println("Users loaded from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }


}
