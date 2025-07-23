package com.example.guibilling.CustomerPackage;


import com.example.guibilling.Admin_Interface.Food_Item;

import java.util.HashMap;

public class Cart {
    private HashMap<Food_Item,Integer> cart=new HashMap<>();

    public void addItem(Food_Item f, int quantity){
        cart.put(f,quantity);
    }
    public HashMap<Food_Item, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<Food_Item, Integer> cart) {
        this.cart = cart;
    }
}
