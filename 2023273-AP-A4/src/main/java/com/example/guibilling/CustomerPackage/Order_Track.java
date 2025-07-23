package com.example.guibilling.CustomerPackage;



import com.example.guibilling.Admin_Interface.Food_Item;
import com.example.guibilling.Admin_Interface.Menu_Management;
import com.example.guibilling.Admin_Interface.Order;
import com.example.guibilling.HelloController;

import java.io.*;
import java.util.Scanner;

public class Order_Track {

    private FileOutputStream fileOutputStream;
    private PrintStream printStream;
    private File file;

    public File getFile() {
        return file;
    }
    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }
    public PrintStream getPrintStream() {
        return printStream;
    }

    Menu_Management menu;
    public Order_Track() throws FileNotFoundException {
        this.menu = new Menu_Management();
        file = new File("Order history.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        fileOutputStream = new FileOutputStream(file);
        printStream = new PrintStream(fileOutputStream);
    }

    public void View_Order_Status(String id) {
        boolean fg = false;

        System.out.println("------------------------------------");
        for(Order o : menu.getOrderQueue()) {
            if (!o.getStatus().equals("DELIVERED") && o.getCustomer().getName().equals(id)) {
                fg = true;
                System.out.println("Order Id: "+o.getOrderId());
                System.out.println("Current Status: "+o.getStatus());
                for (Food_Item f: o.getOrder().keySet()) {
                    System.out.printf("%d  x  1    %s\n", o.getOrder().get(f), f.getName());
                }
            System.out.println("------------------------------------");
            }
        }
        if (!fg){
            System.out.println("No pending order");
        }
    }

    public void Cancelled_Order(String id) {
        boolean fg = false;

        for(Order o : menu.getOrderQueue()) {
            if (o.getCustomer().getName().equals(id)) {
                if (o.getStatus().equals("PENDING")){
                    fg = true;
                    System.out.println("Order Id: "+o.getOrderId());
                    System.out.println("Current Status: "+o.getStatus());
                    o.setStatus("CANCELLED");
                }else if (!o.getStatus().equals("DELIVERED")){
                    System.out.println("Order Cannot be Cancelled");
                }

            }
        }
        if (!fg){
            System.out.println("No pending order");
        }else {
            System.out.println("Order Cancelled");
        }
    }

    public void Order_History(String id) {
        boolean fg = false;
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        HelloController helloController = new HelloController();
        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("Customer: " + id);
            bw.newLine();

            for (Order o : menu.getHistory()) {
                helloController.writeData();
                if (o.getCustomer().getName().equals(id)) {
                    cnt++;
                    fg = true;
                    bw.write("Id: " + cnt);
                    bw.newLine();
                    bw.write("Order Id: " + o.getOrderId() + " Date: " + o.getT());
                    bw.newLine();
                    bw.write("Current Status: " + o.getStatus());
                    bw.newLine();
                    for (Food_Item f : o.getOrder().keySet()) {
                        bw.write(String.format("%d  x  1    %s", o.getOrder().get(f), f.getName()));
                        bw.newLine();
                    }
                    bw.write("----------------------------------------------------");
                    bw.newLine();
                }
            }

            if (!fg) {
                bw.write("No Previous Order");
                bw.newLine();
            }

            if (fg) {
                System.out.println("Enter reorder Id? (0 if not)");
                int cnt2 = 0;
                int reorder = sc.nextInt();
                if (reorder != 0) {
                    for (Order o : menu.getHistory()) {
                        if (o.getCustomer().getName().equals(id)) {
                            cnt2++;
                            if (cnt2 == reorder) {
                                o.setStatus("PENDING");
                                menu.getOrderQueue().add(o);
                                System.out.println("Order Placed");
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}