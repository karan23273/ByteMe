package com.example.guibilling;

import com.example.guibilling.Admin_Interface.Generate_Report;
import com.example.guibilling.Admin_Interface.Menu_Management;
import com.example.guibilling.Admin_Interface.Order_Management;
import com.example.guibilling.HelloApplication;
import com.example.guibilling.InvalidLoginException;
import com.example.guibilling.customClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Administrator extends customClass {
    private int loginMode;
    private Stage stage;
    private HelloApplication helloApplication;

    private String Admin_id;
    private String Admin_pass;


    public String getID() {
        return Admin_id;
    }

    public void setID(String stu_Email) {
        Admin_id = stu_Email;
    }

    public String getPass() {
        return Admin_pass;
    }

    public void setPass(String stu_Password) {
        Admin_pass = stu_Password;
    }

    private Menu_Management A1;
    private Scanner i;

    public Administrator(HelloApplication helloApplication, Stage stage, int loginMode) throws IOException {
        this.Admin_pass = "1234";
        this.stage = stage;
        this.helloApplication = helloApplication;
        this.loginMode = loginMode;
        this.A1 = new Menu_Management();
        this.i = new Scanner(System.in);
        super(stage);
    }


    @Override
    public void set() {
        int m = login(loginMode);
        if (m!=0){
            r();
        }
//        while (m!=0){
//            m = Method();
//        }
    }
    ;
    public void r(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));

            AnchorPane root = loader.load();

            Button menu_manage = (Button) root.lookup("#MenuManagement");
            Button Order_manage = (Button) root.lookup("#OrderManagement");
            Button GenerateReports = (Button) root.lookup("#GenerateReports");
            Button logoutButton = (Button) root.lookup("#LogOut");

            menu_manage.setOnAction(e -> {
                Handlemenu_manage();
            });
            Order_manage.setOnAction(e -> {
                try {
                    HandleOrder_manage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            GenerateReports.setOnAction(e -> {
                HandleGenerateReports();
            });


            logoutButton.setOnAction(e -> {
                try {
                    Login customer = new Login(helloApplication, stage, 1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            Scene scene = new Scene(root, 1024, 576);
            stage.setScene(scene);
            stage.setTitle("Customer Screen");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void HandleGenerateReports() {
        stage.hide();
        Generate_Report A = new Generate_Report(A1);
        A.view_Sales();
        r();
    }

    private void HandleOrder_manage() throws IOException {
        stage.hide();
        Order_Management o = new Order_Management(A1);
        System.out.println("    ○  View pending orders         ○  Update order status               ○ Process refunds               ○ Handle special requests        \n");
        System.out.println("Choose between 1/2/3/4                    0 -> go Back");
        int select = i.nextInt();
        i.nextLine();
        if (select == 1){

            TableView<PendingOrder> tableView = new TableView<>();


            TableColumn<PendingOrder, String> orderIdColumn = new TableColumn<>("Order ID");
            orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
            orderIdColumn.setPrefWidth(200);

            TableColumn<PendingOrder, String> customerNameColumn = new TableColumn<>("Customer Name");
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            customerNameColumn.setPrefWidth(200);

            TableColumn<PendingOrder, Integer> customerIdColumn = new TableColumn<>("Customer ID");
            customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerIdColumn.setPrefWidth(200);

            TableColumn<PendingOrder, String> orderStatusColumn = new TableColumn<>("Order Status");
            orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
            orderStatusColumn.setPrefWidth(200);

            TableColumn<PendingOrder, String> foodItemsColumn = new TableColumn<>("Food Items");
            foodItemsColumn.setCellValueFactory(new PropertyValueFactory<>("foodItems"));
            foodItemsColumn.setPrefWidth(200);

            tableView.getColumns().addAll(orderIdColumn, customerNameColumn, customerIdColumn, orderStatusColumn, foodItemsColumn);

            ObservableList<PendingOrder> pendingOrders = FXCollections.observableArrayList();
            // saving pending oreder to file
            o.View_pending_order(pendingOrders);
            pendingOrders = o.readPendingOrders("pending.ser");

            tableView.setItems(pendingOrders);

            VBox layout = new VBox(10);

            layout.setPrefSize(1000, 700);
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(tableView);
            layout.setStyle(
                    "-fx-font-family: Algerian;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 20;" +
                            "-fx-text-fill: black;"
            );

            FXMLLoader loader = new FXMLLoader(getClass().getResource("browseMenu.fxml"));

            AnchorPane root = loader.load();
            Button go = (Button) root.lookup("#Goback");
            Button logout = (Button) root.lookup("#Logout");
            go.setOnAction(e -> {
                r();
            });

            logout.setOnAction(e -> {
                try {
                    handleLogout();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            root.getChildren().add(layout);
            go.toFront();
            logout.toFront();

            Scene scene = new Scene(root, 1024, 576);
            stage.setScene(scene);
            stage.show();
            return;

        }else if (select == 2){
            System.out.print("Enter Order ID: ");
            int Order_id = i.nextInt();
            i.nextLine();

            System.out.print("Set status: ");
            String status = i.nextLine();
            o.Update_order_status(Order_id, status);
            System.out.println("Order Status Updated");
            Menu_Management.saveHistory();
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

        }
        r();
    }
    public void handleLogout() throws IOException {
        Login customer = new Login(helloApplication, stage, 1);
    }

    public void Handlemenu_manage() {
        stage.hide();

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
        }
        r();

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



//    public int Method(){
//        System.out.println("                                      ___ADMIN ERP HOMEPAGE___                              ");
//        System.out.println("    ○  Menu Management                             ○  Order Management                    ○  Generate Reports\n");
//
//
//
//        int method_no = 0;
//        while (true) {
//            System.out.println("Choose what you want to see..\n");
//            System.out.println("1 ->    Menu Management");
//            System.out.println("2 ->    Order Management");
//            System.out.println("3 ->    Generate Reports");
//            System.out.println("                                                       LOG OUT(PRESS 0)                            \n");
//
//            try {
//                method_no = i.nextInt();
//                break;
//            } catch (InputMismatchException e) {
//                System.err.println("Invalid input! Please enter a valid number");
//                i.next();
//            }
//        }
//        i.nextLine();
//
//        while (method_no!=0){
//
//
//            if (method_no==3){
//                Generate_Report A = new Generate_Report(A1);
//                A.view_Sales();
//
//
//            } else if (method_no == 2) {
//                Order_Management o = new Order_Management(A1);
//                System.out.println("    ○  View pending orders         ○  Update order status               ○ Process refunds               ○ Handle special requests        \n");
//                System.out.println("Choose between 1/2/3/4                    0 -> go Back");
//                int select = i.nextInt();
//                i.nextLine();
//                if (select == 1){
//                    o.View_pending_order();
//                }else if (select == 2){
//                    System.out.print("Enter Order ID: ");
//                    int Order_id = i.nextInt();
//                    i.nextLine();
//
//                    System.out.print("Set status: ");
//                    String status = i.nextLine();
//                    o.Update_order_status(Order_id, status);
//                }else if (select == 3){
//                    System.out.print("Enter Order ID: ");
//                    int Order_id = i.nextInt();
//                    i.nextLine();
//                    o.Process_refund(Order_id);
//
//                }else if (select == 4){
//                    System.out.print("Enter Order ID: ");
//                    int Order_id = i.nextInt();
//                    i.nextLine();
//
//                    o.Handle_special_request(Order_id);
//
//                }else {
//                    break;
//                }
//
//            } else if (method_no == 1) {
//
//                System.out.println("    ○  Add New Items               ○  Update existing items             ○ Remove items         \n");
//                System.out.println("Choose between 1/2/3                    0 -> go Back");
//                System.out.print("Enter: ");
//                int select = i.nextInt();
//                i.nextLine();
//                if (select == 1){
//
//                    System.out.print("Enter Item Name: ");
//                    String Item_Name = i.nextLine();
//
//                    System.out.print("Enter Price: ");
//                    int Item_price = i.nextInt();
//                    i.nextLine();
//
//                    System.out.print("Enter Category: ");
//                    String Item_category = i.nextLine();
//
//                    A1.add_New_items(Item_Name, Item_price, Item_category);
//                    System.out.println("Item Added Successfully");
//
//                } else if (select == 2) {
//                    System.out.print("Item Name:.");
//                    String item_name = i.nextLine();
//
//                    A1.Update_existing_items(item_name);
//
//
//                } else if (select == 3) {
//                    System.out.print("Item Name:.");
//                    String item_name = i.nextLine();
//
//                    A1.remove_item(item_name);
//
//                } else if(select == 0){
//                    break;
//                }
//            }else {
//                break;
//            }
//            break;
//        }
//        return method_no;
//    }

    int getYesOrNo(Scanner scanner) {
    int choice;
    while (true) {
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1 || choice == 0) {
                break;
            } else {
                System.out.println("Invalid input! Please enter 1 or 0.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter 1 or 0.");
            scanner.next();
        }
    }
    return choice;
}

    private boolean isInteger(String email_id) {
        return email_id.matches("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");
    }

}
