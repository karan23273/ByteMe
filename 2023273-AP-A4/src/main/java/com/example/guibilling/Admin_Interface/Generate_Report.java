package com.example.guibilling.Admin_Interface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Generate_Report {
    Scanner sc = new Scanner(System.in);
    Menu_Management menu_management;

    public Generate_Report(Menu_Management menu_management) {
        this.menu_management = menu_management;
    }

    public void view_Sales(){
        System.out.print("Enter Sales Date(yyyy-MM-dd)");
        String date_in = sc.nextLine();
        date_in += "T23:59:59";

        LocalDateTime date = LocalDateTime.parse(date_in);
        int total_order = 0;
        int total_revenue = 0;
        int Max_purchase = 0;
        String Most_popular_product = "";
        ArrayList<String> food= new ArrayList<String>();
        for (Order o : Menu_Management.getHistory()){

            if (Objects.equals(o.getStatus(), "DELIVERED") && o.getT().isBefore(date)){
                System.out.println("Sales Report for orders delivered until " + date);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Order Id: " + o.getOrderId() + "\tCustomer: " +o.getCustomer().getName());
                for(Food_Item i : o.getOrder().keySet()){
                    System.out.println("Food item: "+i.getName()+"\tFood category: "+i.getCategory()+" x "+o.getOrder().get(i)+"\tSum total: "+o.getOrder().get(i) * i.getPrice() );
                    total_revenue += o.getOrder().get(i)*i.getPrice();
                    total_order += o.getOrder().get(i);
                    if (o.getOrder().get(i) > Max_purchase){
                        Max_purchase = o.getOrder().get(i);
                        Most_popular_product = i.getName();
                    }
                }
            }
            for (Food_Item i : o.getOrder().keySet()) {
                if (o.getOrder().get(i) == Max_purchase){
                    food.add(i.getName());
                }
            }

        }
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Report Overview\n");
        System.out.println("Total Item Order: " + total_order);
        System.out.println("Net Revenue: " + total_revenue);
        System.out.println("Most Popular Items: "+ Most_popular_product);
//        for (String i : food){
//            System.out.println("\tItem: "+i);
//        }
    }

}
