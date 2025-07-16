import Admin_Interface.Menu_Management;
import Customer.*;

import java.util.InputMismatchException;

import java.util.Objects;
import java.util.Scanner;

public class Customer extends Users implements ID_PASS {
    private String id;
    private String pass;
    private Customer_data customerData;
    private Cart_Operation cartOperation;
    private Customer_Info customerInfo;
    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setID(String stu_Email) {
        id = stu_Email;
    }

    @Override
    public String getPass() {
        return pass;
    }

    @Override
    public void setPass(String stu_Password) {
        pass = stu_Password;
    }


    public int login(int user_mode) {
        Scanner i = new Scanner(System.in);
        String email_id = "";
        boolean validInput =false;

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
        customerInfo = new Customer_Info();

        while (true) {
            try {
                if (user_mode == 0) {

                    System.out.print("Create new password: ");
                    String password = i.nextLine();
                    setPass(password);
                    customerData = new Customer_data(getID(),getPass());
                    cartOperation = new Cart_Operation(customerData);
                    customerInfo.getCredential().put(getID(), customerData);

                    System.out.println("Account created successfully.");
                    return -1;
                } else {
                    if (!customerInfo.getCredential().containsKey(email_id)) { /////////////////////////////////
                        System.out.println("User email not found.");
                        return 0;
                    }

                    System.out.print("Enter Password: ");
                    String password = i.nextLine();
                    setPass(password);

                    if (!Objects.equals(customerInfo.getCredential().get(getID()).getPassword(), getPass())) {
                        throw new InvalidLoginException("Wrong password!");
                    } else {
                        customerData = new Customer_data(getID(),getPass());
                        cartOperation = new Cart_Operation(customerData);
                        System.out.println("Login successful!");
                        return -1;
                    }
                }
            } catch (InvalidLoginException e) {
                System.err.println(e.getMessage());
                System.out.println();
                System.out.print("Do you want to try again? 1 / 0: ");
                int retry = getYesOrNo(i);

                if (retry != 1) {
                    System.out.println("Exiting login process.");
                    return 0;
                }
            }catch (Exception e){
                System.err.println("An error occurred: " + e.getMessage());
                return 0;
            }
        }

    }



    public int Method(){
        System.out.println("                              ___ CUSTOMER PAGE___                              ");
        System.out.println("    ○  Browse Menu                                          ○  Cart Operations\n");
        System.out.println("    ○  Track Order                                          ○  Get VIP access\n");
        System.out.println("    ○  Review Section\n");

        Scanner i = new Scanner(System.in);
        int method_no = 0;
        while (true) {
            System.out.println("Choose what you want to see..\n");
            System.out.println("1 ->    Browse Menu");
            System.out.println("2 ->    Cart Operations");
            System.out.println("3 ->    Track Order");
            System.out.println("4 ->    Get VIP access");
            System.out.println("5 ->    Item Review");
            System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");

            try {
                method_no = i.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input! Please enter a valid number");
                i.next();
            }

        }
        while (method_no!=0){
            if (method_no == 2){

                System.out.println("Cart..\n");
                System.out.println("1 ->    Add items");
                System.out.println("2 ->    Modify quantity");
                System.out.println("3 ->    Remove items");
                System.out.println("4 ->    View total");
                System.out.println("5 ->    Checkout");
                System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");

                System.out.print("Select options: ");
                int select = i.nextInt();
                i.nextLine();
                if (select==1){
                    Browse_Menu v = new Browse_Menu();
                    v.displayMenu();

                    System.out.print("Enter Item: ");
                    String item = i.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quan = i.nextInt();
                    i.nextLine();

                    cartOperation.add_to_cart(item, quan);
                } else if (select==2) {
                    System.out.print("Enter Item: ");
                    String item = i.nextLine();

                    System.out.print("Enter new Quantity: ");
                    int quan = i.nextInt();

                    cartOperation.modify_quantity(item, quan);
                } else if (select==3) {
                    System.out.print("Which Item You Want to Remove: ");
                    String item = i.nextLine();

                    cartOperation.remove_from_cart(item);
                }else if (select == 4) {
                    cartOperation.view_cart();
                }else if (select == 5){
                    System.out.print("Any Special request: ");
                    String special = i.nextLine();


                    System.out.print("Enter Delivery Address: ");
                    String add = i.nextLine();

                    System.out.println("COMPLETE PAYMENT ");
                    System.out.print("Enter payment: ");
                    int payment = i.nextInt();

                    cartOperation.checkout(special, payment);

                }

            }
            else if (method_no == 1 ) {

                System.out.println("Choose what you want to see..\n");
                System.out.println("1 ->    View all items");
                System.out.println("2 ->    Search functionality");
                System.out.println("3 ->    Fiter by Category");
                System.out.println("4 ->    Sort by prices");
                System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");
                int select = i.nextInt();
                i.nextLine();
                Browse_Menu v = new Browse_Menu();
                if (select == 1) {
                    v.displayMenu();
                }else if(select == 2){
                    System.out.print("Search item: ");
                    String name = i.nextLine();
                    v.display_specific_item(name);
                } else if (select == 3) {
                    System.out.print("Filter category: ");
                    String name = i.nextLine();
                    v.displayBYcategory(name);

                } else if (select == 4) {
                    v.displayBYprice();
                }else{
                    break;
                }


            }
            else if (method_no == 3) {
                System.out.println("View Order..\n");
                System.out.println("1 ->    View Order Status");
                System.out.println("2 ->    Cancel Order");
                System.out.println("3 ->    Order history");
                System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");
                System.out.print("Select options: ");
                int select = i.nextInt();
                i.nextLine();
                Order_Track orderTrack = new Order_Track();
                if (select == 1) {
                    orderTrack.View_Order_Status(getID());
                }else if (select == 2) {
                    orderTrack.Cancelled_Order(getID());
                }else if (select == 3) {
                    orderTrack.Order_History(getID());
                }else {
                    break;
                }
            }
            else if (method_no == 4) {
                System.out.println("Pay 500 to become our VIP customer");
                int money = i.nextInt();
                i.nextLine();
                if(money==500) {
                    Customer_Info c = new Customer_Info();
                    c.getCredential().get(getID()).setVip(true);
                    System.out.println("You are VIP customer now");
                }
                else{
                    System.out.println("Insufficient money");
                }
            } else if (method_no == 5) {
                Order_Review o = new Order_Review(new Menu_Management());
                System.out.println("View Review..\n");
                System.out.println("1 ->    Give Review");
                System.out.println("2 ->    View Review");
                System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");
                System.out.print("Select options: ");
                int select = i.nextInt();
                i.nextLine();
                if (select == 1) {
                    o.give_Review(getID());

                }else if (select == 2) {
                    o.view_review(getID());
                }else {
                    break;
                }

            }
            break;
        }
        return method_no;
    }
}

