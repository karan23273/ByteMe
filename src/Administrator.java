import Admin_Interface.Generate_Report;
import Admin_Interface.Menu_Management;
import Admin_Interface.Order_Management;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Administrator extends Users implements ID_PASS{
    private String Admin_id;
    private String Admin_pass = "1234";

    @Override
    public String getID() {
        return Admin_id;
    }

    @Override
    public void setID(String stu_Email) {
        Admin_id = stu_Email;
    }

    @Override
    public String getPass() {
        return Admin_pass;
    }

    @Override
    public void setPass(String stu_Password) {
        Admin_pass = stu_Password;
    }

    public int login(int x) {
        Scanner i = new Scanner(System.in);
        String email_id = "";

        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter Your Email Id: ");
            email_id = i.nextLine();
            try {
                if (!isInteger(email_id)) {
                    throw new InvalidLoginException("Invalid Input");
                }
                validInput = true;
            } catch (InvalidLoginException e) {
                System.err.println(e.getMessage());
                System.out.println();
            }
        }

        setID(email_id);

        while (true) {
            try {
                System.out.print("Enter Password: ");
                String password = i.nextLine();

                if (!Objects.equals(password, getPass())) {
                    throw new InvalidLoginException("Wrong password!");
                } else {
                    System.out.println("Login successful!");
                    return -1;
                }
            } catch (InvalidLoginException e) {
                System.err.println(e.getMessage());
                System.out.println();
                System.out.print("Do you want to try again? 1 / 0: ");
                int choice = getYesOrNo(i);

                if (choice == 0) {
                    System.out.println("Exiting login process.");
                    return 0;
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                return 0;
            }
        }
    }



    public int Method(){
        System.out.println("                                      ___ADMIN ERP HOMEPAGE___                              ");
        System.out.println("    ○  Menu Management                             ○  Order Management                    ○  Generate Reports\n");


        Scanner i = new Scanner(System.in);
        int method_no = 0;
        while (true) {
            System.out.println("Choose what you want to see..\n");
            System.out.println("1 ->    Menu Management");
            System.out.println("2 ->    Order Management");
            System.out.println("3 ->    Generate Reports");
            System.out.println("                                                       LOG OUT(PRESS 0)                            \n");

            try {
                method_no = i.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input! Please enter a valid number");
                i.next();
            }
        }
        i.nextLine();

        while (method_no!=0){
            Menu_Management A1 = new Menu_Management();

            if (method_no==3){
                Generate_Report A = new Generate_Report(A1);
                A.view_Sales();


            } else if (method_no == 2) {
                Order_Management o = new Order_Management(A1);
                System.out.println("    ○  View pending orders         ○  Update order status               ○ Process refunds               ○ Handle special requests        \n");
                System.out.println("Choose between 1/2/3/4                    0 -> go Back");
                int select = i.nextInt();
                i.nextLine();
                if (select == 1){
                    o.View_pending_order();
                }else if (select == 2){
                    System.out.print("Enter Order ID: ");
                    int Order_id = i.nextInt();
                    i.nextLine();

                    System.out.print("Set status: ");
                    String status = i.nextLine();
                    o.Update_order_status(Order_id, status);
                }else if (select == 3){
                    System.out.print("Enter Order ID: ");
                    int Order_id = i.nextInt();
                    i.nextLine();
                    o.Process_refund(Order_id);

                }else if (select == 4){
                    System.out.print("Enter Order ID: ");
                    int Order_id = i.nextInt();
                    i.nextLine();

                    o.Handle_special_request(Order_id);

                }else {
                    break;
                }

            } else if (method_no == 1) {
                System.out.println("    ○  Add New Items               ○  Update existing items             ○ Remove items         \n");
                System.out.println("Choose between 1/2/3                    0 -> go Back");
                System.out.print("Enter: ");
                int select = i.nextInt();
                i.nextLine();
                if (select == 1){

                    System.out.print("Enter Item Name: ");
                    String Item_Name = i.nextLine();

                    System.out.print("Enter Price: ");
                    int Item_price = i.nextInt();
                    i.nextLine();

                    System.out.print("Enter Category: ");
                    String Item_category = i.nextLine();

                    A1.add_New_items(Item_Name, Item_price, Item_category);
                    System.out.println("Item Added Successfully");

                } else if (select == 2) {
                    System.out.print("Item Name:.");
                    String item_name = i.nextLine();

                    A1.Update_existing_items(item_name);


                } else if (select == 3) {
                    System.out.print("Item Name:.");
                    String item_name = i.nextLine();

                    A1.remove_item(item_name);

                } else if(select == 0){
                    break;
                }
            }else {
                break;
            }
            break;
        }
        return method_no;
    }
}
