package com.example.guibilling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;
    private Scanner scanner;
    @BeforeEach
    void setUp() throws IOException {
        customer = new Customer();
    }
    @Test
    void testValidPassword() {
        String s = customer.login("User@123", "123",0);
//        InvalidLoginException exception = assertThrows(InvalidLoginException.class, () -> {
//            customer.login("User@123", "wrongPass", 1);
//        });
        assertEquals("Wrong password!", customer.login("User@123", "wrongPass", 1));
    }

    @Test
    void testUserFound() {
        assertEquals("User email not found.",customer.login("Use@123", "123",1));
    }

    @Test
    void testEmailFormat(){
        String result = customer.login("Invalidformat", "Password", 0);
        String result2 = customer.login("Invalid?:?>@format", "Password", 1);
        String result3 = customer.login("valid@format", "Password", 2);
        assertEquals(result,"Invalid Email format");
        assertEquals(result2,result);
        assertNotSame(result,result3);

    }
    @Test
    void testAlreadyregisterd(){
        String s = customer.login("User@123", "123",0);
        String ans = customer.login("User@123", "123", 0);
        assertEquals("Email is already registered.", ans);

    }
}


