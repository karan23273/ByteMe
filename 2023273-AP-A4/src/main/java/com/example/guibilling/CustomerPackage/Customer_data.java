package com.example.guibilling.CustomerPackage;

import java.io.Serializable;

public class Customer_data implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int ctr = 1;
    private final int customerId;
    private String password;
    private String name;
    private boolean vip;

    public Customer_data(String name, String password) {
        this.password = password;
        this.name = name;
        customerId = ctr++;
        this.vip = false;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
