package com.example.guibilling.Admin_Interface;

import com.example.guibilling.PendingOrder;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Order_Management {
    Menu_Management mainMenu;
    private static final long serialVersionUID = 1L;
    public Order_Management(Menu_Management mainMenu) {
        this.mainMenu = mainMenu;
    }
    public void savePendingOrders(ObservableList<PendingOrder> pendingOrders, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(new ArrayList<>(pendingOrders)); // Convert ObservableList to ArrayList for serialization
            System.out.println("Pending orders saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<PendingOrder> readPendingOrders(String filePath) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<PendingOrder> list = (List<PendingOrder>) ois.readObject();
            return FXCollections.observableArrayList(list); // Convert back to ObservableList
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList();
    }

    public void View_pending_order(ObservableList<PendingOrder> pendingOrder) {
        boolean fg = false;
//        System.out.println("------------------------------------");
        for(Order o : mainMenu.getOrderQueue()) {
            if (!o.getStatus().equals("DELIVERED") && !o.getStatus().equals("REFUNDED")) {
                fg = true;
//                System.out.print("Order Id: "+o.getOrderId());
//                System.out.printf(" Customer:  %s  (%d)\n",o.getCustomer().getName() ,o.getCustomer().getCustomerId());
//                System.out.println("Order Status: " + o.getStatus());
                StringBuilder foodItems = new StringBuilder();
                for (Food_Item f: o.getOrder().keySet()) {
                    foodItems.append(o.getOrder().get(f)).append(" x ").append(f.getName()).append("\n");
//                    System.out.printf("%d  x  1    %s\n", o.getOrder().get(f), f.getName());
                }
                pendingOrder.add(new PendingOrder(o.getOrderId(), o.getCustomer().getName(), o.getCustomer().getCustomerId(), o.getStatus(),foodItems.toString()));
            }
//            System.out.println("------------------------------------");
        }
//        if (!fg){
//            System.out.println("No pending order");
//        }
        savePendingOrders(pendingOrder, "pending.ser");
    }

    public void Update_order_status(int id,String status) {
        for(Order o : Menu_Management.getOrderQueue()) {
            if(o.getOrderId() == id) {
                o.setStatus(status);
                if(status.equals("DELIVERED") || status.equals("REFUNDED") || status.equals("DENIED")) {
                    Menu_Management.getOrderQueue().remove(o);
                    Menu_Management.getHistory().add(o);
                }
                System.out.println("Order Updated Successfully");
                return;
            }
        }
        System.out.println("Order Updated Failed");
    }

    public void Process_refund(int id) {
        for(Order o : mainMenu.getOrderQueue()) {
            if(o.getOrderId() == id && o.getStatus().equals("CANCELLED")) {
                o.setStatus("REFUNDED");
                Menu_Management.getOrderQueue().remove(o);
                Menu_Management.getHistory().add(o);
                System.out.println("Order Refunded Successfully");
                return;
            }
        }
        System.out.println("Order with order id " + id + " not found"  );
    }

    public void Handle_special_request(int id) {
        for (Order o : Menu_Management.getOrderQueue()) {
            if(o.getOrderId() == id) {
                if (!o.getSpecial().equals("NA")) {
                    String s = o.getSpecial();
                    String s1 = o.getSpecial() + "Checked";
                    o.setSpecial(s1);
                    System.out.printf("Order with special request '%s' Updated Successfully\n", s);

                }else{
                    System.out.println("No Special Request for order id "+ id);
                }
                return;
            }
        }
        System.out.println("Order with order id " +id+ " not found"  );
    }
}
