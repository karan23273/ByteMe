package com.example.guibilling;

import java.io.Serializable;

public class PendingOrder  implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderId;
    private String customerName;
    private int customerId;
    private String orderStatus;
    private String foodItems;

    // Constructor
    public PendingOrder(int orderId, String customerName, int customerId, String orderStatus, String foodItems) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.foodItems = foodItems;
    }
    public String toString() {
        return "PendingOrder{" + "orderId=" + orderId + ", customerName='" + customerName + '\'' + ", customerId=" + customerId + ", status='" + orderStatus + '\'' + ", foodItems='" + foodItems + '\'' + '}';
    }

    // Getter Methods
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getFoodItems() {
        return foodItems;
    }
}

