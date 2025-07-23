package com.example.guibilling.CustomerPackage;

import com.example.guibilling.Admin_Interface.Food_Item;
import com.example.guibilling.Admin_Interface.Menu_Management;
import com.example.guibilling.Administrator;
import com.example.guibilling.HelloApplication;
import javafx.stage.Stage;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Cart_OperationTest {

    private  Cart_Operation cart_operation;

    @BeforeEach
    void setUp() {
         this.cart_operation = new Cart_Operation();
    }
    @Test
    void Unavailable_cart() throws IOException {
        Menu_Management menuManagement = new Menu_Management();
        menuManagement.getFooditems().put("Momos", new Food_Item("Momos", 120, false, "Snack"));

        String ans = cart_operation.add_to_cart("Momos", 1);
        assertEquals("not available", ans);
        assertNotNull(ans, cart_operation.add_to_cart("Pizza", 2));
    }


    @Test
    void valid_cart_operation() {
        String ans = cart_operation.add_to_cart("Burger", 2);
        assertEquals("Item added to the cart", ans);


    }
    @Test
    void Found_item() {
        String ans = cart_operation.add_to_cart("Test item", 2);
        String ans1 = cart_operation.add_to_cart("Test item2", 2);
        assertEquals("Item not found", ans);
        assertEquals(ans1, ans);
        String ans2 = cart_operation.add_to_cart("Burger", 2);
        assertNotEquals(ans, ans2);

    }
}