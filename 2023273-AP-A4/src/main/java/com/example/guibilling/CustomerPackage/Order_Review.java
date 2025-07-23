package com.example.guibilling.CustomerPackage;



import com.example.guibilling.Admin_Interface.Food_Item;
import com.example.guibilling.Admin_Interface.Menu_Management;
import com.example.guibilling.Admin_Interface.Order;

import java.util.Scanner;

public class Order_Review {
    private final Menu_Management menu;
    private final Scanner sc = new Scanner(System.in);

    public Order_Review(Menu_Management menu) {
        this.menu = menu;
    }

    public void give_Review(String customerId) {
        boolean hasOrder = false;

        for (Order order : menu.getHistory()) {
            if (order.getCustomer().getName().equals(customerId)) {
                hasOrder = true;
                System.out.println("_____________________________________________________");

                for (Food_Item item : order.getOrder().keySet()) {
                    System.out.printf("Review for %s: ", item.getName());
                    String reviewText = sc.nextLine();
                    String review = reviewText + " by " + order.getCustomer().getName();

                    // Add or append the review to the order's review map
                    order.getReview().merge(item, review, (existing, newReview) -> existing + "\n" + newReview);
                }
            }
        }

        if (!hasOrder) {
            System.out.println("No previous orders found for this customer.");
        } else {
            System.out.println("Review added successfully.");
        }
    }

    public void view_review(String customerId) {
        boolean foundReview = false;
        for (Order or : menu.getOrderQueue()) {
            if (or.getCustomer().getName().equals(customerId)) {

                for (Food_Item i : or.getOrder().keySet()) {

                    for (Order order : menu.getHistory()) {

                        for (Food_Item item : order.getOrder().keySet()) {
                            if (i == item){
                                String review = order.getReview().get(item);
                                if (review != null && !review.isEmpty()) {
                                    foundReview = true;
                                    System.out.println("Review for " + item.getName() + " -> " + review);
                                }

                            }
                        }

                    }

                }

            }
        }

        if (!foundReview) {
            System.out.println("No reviews found for this customer.");
        }
    }
}
