package Customer;

import Admin_Interface.Food_Item;
import Admin_Interface.Menu_Management;
import Admin_Interface.Order;

import java.util.Scanner;

public class Order_Track {
    Menu_Management menu;
    public Order_Track() {
        this.menu = new Menu_Management();
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
        System.out.println("------------------------------------");
        for(Order o : menu.getHistory()) {
            if (o.getCustomer().getName().equals(id)) {
                cnt++;
                System.out.println("Id: " + cnt);
                fg = true;
                System.out.println("Order Id: " + o.getOrderId() + " Date: " + o.getT());
                System.out.println("Current Status: "+o.getStatus());
                for (Food_Item f: o.getOrder().keySet()) {
                    System.out.printf("%d  x  1    %s\n", o.getOrder().get(f), f.getName());
                }
            }
            System.out.println("------------------------------------");
        }


        if (!fg){
            System.out.println("No Previous Order");
        }else {
            System.out.println("Enter reorder Id? (0 if not)");
            int cnt2 = 0;
            int reorder = sc.nextInt();
            if (reorder != 0){
                for(Order o : menu.getHistory()) {
                    if (o.getCustomer().getName().equals(id)) {
                        cnt2++;
                        if (cnt2 == reorder){
                            o.setStatus("PENDING");
                            menu.getOrderQueue().add(o);
                            System.out.println("Order Placed");
                        }
                    }
                }
            }

        }
    }
}