package com.example.guibilling.Admin_Interface;

import com.example.guibilling.CustomerPackage.Customer_data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private final int orderId;
    private HashMap<Food_Item,Integer> order;
    private HashMap<Food_Item, String>review;

    private LocalDateTime t;
    private Customer_data customer;
    private String status;
    private String special;

    public Order(LocalDateTime t, Customer_data customer, HashMap<Food_Item,Integer> o, String status, String special) {
        orderId=idCounter++;
        this.order = o;
        this.t = t;
        this.customer = customer;
        this.status = status;
        this.special = special;
        review = new HashMap<>();
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public int getOrderId() {
        return orderId;
    }

    public HashMap<Food_Item, Integer> getOrder() {
        return order;
    }

    public void setOrder(HashMap<Food_Item,Integer> order) {
        this.order = order;
    }

    public HashMap<Food_Item, String> getReview() {
        return review;
    }

    public void setReview(HashMap<Food_Item, String> review) {
        this.review = review;
    }

    public LocalDateTime getT() {
        return t;
    }

    public void setT(LocalDateTime t) {
        this.t = t;
    }

    public Customer_data getCustomer() {
        return customer;
    }

    public void setCustomer(Customer_data customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}