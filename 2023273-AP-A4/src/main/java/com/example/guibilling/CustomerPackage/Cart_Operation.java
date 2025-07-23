package com.example.guibilling.CustomerPackage;


import com.example.guibilling.Admin_Interface.Food_Item;
import com.example.guibilling.Admin_Interface.Menu_Management;
import com.example.guibilling.Admin_Interface.Order;

import java.time.LocalDateTime;

public class Cart_Operation{
    Cart my_cart;
    Order my_order;
    Customer_data customer;
    Menu_Management menu;
    public Cart_Operation(Customer_data customer){
        my_cart = new Cart();
        this.customer = customer;
        menu = new Menu_Management();

    }
    public Cart_Operation(){
        my_cart = new Cart();
        menu = new Menu_Management();
    }

    public String add_to_cart(String item_name, int quantity){

        if (Menu_Management.getFooditems().containsKey(item_name)){
            Food_Item food_item = menu.getFooditems().get(item_name);

            my_cart.addItem(food_item, quantity);
            if (!food_item.isAvailable()){
                return ("not available");
            }
            return ("Item added to the cart");
        }else {
            return ("Item not found");
        }
    }

    public void modify_quantity(String item_name, int quantity){
        Food_Item food_item = menu.getFooditems().get(item_name);

        if (food_item!=null && my_cart.getCart().containsKey(food_item)){
            my_cart.getCart().replace(food_item, quantity);
        }else {
            System.out.println("Item not found");
        }
    }

    public void remove_from_cart(String item_name){
        Food_Item food_item = menu.getFooditems().get(item_name);

        if (food_item!=null && my_cart.getCart().containsKey(food_item)){
            my_cart.getCart().remove(menu.getFooditems().get(item_name));

        }else {
            System.out.println("Item not found");
        }
    }

    public void view_cart() {

        int cartTotal = 0;
        if (!my_cart.getCart().isEmpty()) {

            System.out.println("_______________________________________________________________________________________________________________________");
            System.out.printf("%-20s %-15s %-15s %-20s %-15s %-15s\n", "Item Name", "Item Price", "Availability", "Category", "Quantity", "Subtotal");
            System.out.println("_______________________________________________________________________________________________________________________");
            for (Food_Item item : my_cart.getCart().keySet()) {
                int quantity = my_cart.getCart().get(item);
                int subtotal = item.getPrice() * quantity;
                cartTotal += subtotal;

                System.out.printf("%-20s %-15d %-15s %-20s %-15d %-15d\n",
                        item.getName(),
                        item.getPrice(),
                        item.isAvailable() ? "Available" : "Not Available",
                        item.getCategory(),
                        quantity,
                        subtotal);
            }
            System.out.println("_______________________________________________________________________________________________________________________");
            System.out.println("Cart Total: "+ cartTotal);
            System.out.println("_______________________________________________________________________________________________________________________");

        }else {
            System.out.println("Empty cart");
        }
    }

    public void checkout(String special, int money){
        int cartTotal = 0;
        if (!my_cart.getCart().isEmpty()) {
            for (Food_Item item : my_cart.getCart().keySet()) {
                int quantity = my_cart.getCart().get(item);
                int subtotal = item.getPrice() * quantity;
                cartTotal += subtotal;
            }
            if (money>=cartTotal){
                LocalDateTime now = LocalDateTime.now();
                my_order = new Order(now, customer, my_cart.getCart(),"PENDING", special);
                my_order.setOrder(my_cart.getCart());
                menu.addOrder(my_order);
                System.out.println("Ordered Placed");

            }else {
                System.out.println("You don't have enough money");
            }

        }else {
            System.out.println("Empty cart");
        }

    }
}
