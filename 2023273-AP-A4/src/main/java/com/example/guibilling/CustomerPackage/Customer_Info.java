package com.example.guibilling.CustomerPackage;

import java.io.*;
import java.util.HashMap;

public class Customer_Info implements Serializable {
    private static final long serialVersionUID = 1L;
    private static HashMap<String, Customer_data> Credential = new HashMap<>();
    private static final String FILE_PATH = "customers.ser";

    public HashMap<String, Customer_data> getCredential() {
        return Credential;
    }

    public void setCredential(HashMap<String, Customer_data> credential) {
        Credential = credential;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(Credential);
//            System.out.println("Customer data saved successfully to " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving customer data: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                Credential = (HashMap<String, Customer_data>) ois.readObject();
//                System.out.println("Customer data loaded successfully from " + FILE_PATH);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading customer data: " + e.getMessage());
            }
        } else {
            System.out.println("No customer data file found");
        }
    }

//    public void signUp(String username, String password) {
//        if (Credential.containsKey(username)) {
//            System.out.println("Username already exists");
//        } else {
//            Customer_data newCustomer = new Customer_data(username, password);
//            Credential.put(username, newCustomer);
//            System.out.println("Customer signed up successfully.");
//        }
//    }
//    public boolean login(String username, String password) {
//        if (Credential.containsKey(username)) {
//            Customer_data customer = Credential.get(username);
//            if (customer.getPassword().equals(password)) {
//                System.out.println("Login successful. Welcome, " + customer.getName());
//                return true;
//            } else {
//                System.out.println("Invalid password.");
//            }
//        } else {
//            System.out.println("User does not exist.");
//        }
//        return false;
//    }
}
