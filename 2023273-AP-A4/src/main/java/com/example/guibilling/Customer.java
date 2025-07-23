package com.example.guibilling;

import com.example.guibilling.Admin_Interface.Menu_Management;
import com.example.guibilling.CustomerPackage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Customer{
    private int loginMode;
    private Stage stage;
    private String id;
    private String pass;
    private Customer_data customerData;
    private Cart_Operation cartOperation;
    private Customer_Info customerInfo;
    private HelloApplication helloApplication;
    Scanner i = new Scanner(System.in);

    private FileOutputStream fileOutputStream;
    private PrintStream printStream;

    public Customer(HelloApplication helloApplication, Stage stage, int loginMode) throws IOException {
        this.stage = stage;
        this.helloApplication = helloApplication;
        this.loginMode = loginMode;
//        super(stage);
        set();
    }

    public Customer() {

    }

    public void r() throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer.fxml"));

            AnchorPane root = loader.load();  // FXML file without controller

            Button browseMenuButton = (Button) root.lookup("#BrowseMenu");
            Button cartOperationButton = (Button) root.lookup("#CartOperation");
            Button trackOrderButton = (Button) root.lookup("#TrackOrder");
            Button getVIPaccessButton = (Button) root.lookup("#GetVIPaccess");
            Button itemReviewButton = (Button) root.lookup("#itemReview");
            Button logoutButton = (Button) root.lookup("#LogOut");

            browseMenuButton.setOnAction(e -> {
                try {
                    handleBrowseMenu();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            cartOperationButton.setOnAction(e -> {
                try {
                    handleCartOperation();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            trackOrderButton.setOnAction(e -> {
                try {
                    handleTrackOrder();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            getVIPaccessButton.setOnAction(e -> {
                try {
                    handleGetVIPaccess();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            itemReviewButton.setOnAction(e -> handleItemReview());
            logoutButton.setOnAction(e -> {
                try {
                    handleLogout();
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

    public void set() throws IOException {
        stage.hide();
        String  m = logininput(loginMode);
        if (m== "Account created successfully." || m=="Login successful!"){
            System.out.println(m);;
            r();
        }else {
            System.out.println(m);
        }
//        while (m!=0){
//            m = Method();
//        }
    }
    private String addText() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");  // Append each line and add a new line after
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file.";
        }
        return content.toString();

    }

    public void handleBrowseMenu() throws IOException {


        stage.hide();
//        System.setOut(System.out);
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
//            System.setOut(System.out);
        }else if(select == 2){
            System.out.print("Search item: ");
            String name = i.nextLine();
            v.display_specific_item(name);
//            System.setOut(System.out);
        } else if (select == 3) {
            System.out.print("Filter category: ");
            String name = i.nextLine();
            v.displayBYcategory(name);
//            System.setOut(System.out);
        } else if (select == 4) {
            v.displayBYprice();
//            System.setOut(System.out);
        }else if (select == 0) {
            r();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

        AnchorPane root = loader.load();

//        Label textArea = new Label();
//        textArea.setText(addText());
//        VBox.setStyle(
//                "-fx-font-family: Algerian;" +
//                        "-fx-font-weight: bold;" +
//                        "-fx-font-size: 20;" +
//                        "-fx-text-fill: white;"
//        );
//
//
//
//        VBox layout = new VBox(textArea);
//        layout.setAlignment(Pos.CENTER);
//        layout.setPrefSize(1024, 576);
//        root.getChildren().add(layout);


        String s = addText();
        String[] line = s.split("\n");

        int l = line.length;
        ObservableList<menuItem> menuData = FXCollections.observableArrayList();
        int j = 0;
        while (j<l){
            menuData.add(new menuItem(line[j].split(",")));
            j++;
        }

        TableView<menuItem> tableView = new TableView<>();
        tableView.setPrefSize(500, 300);

        // Create columns
        TableColumn<menuItem, String> nameColumn = new TableColumn<>("Item Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(200);

        TableColumn<menuItem, Double> priceColumn = new TableColumn<>("Price ($)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(200);

        TableColumn<menuItem, String> availaibilityColumn = new TableColumn<>("Availability");
        availaibilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
        availaibilityColumn.setPrefWidth(200);

        TableColumn<menuItem, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setPrefWidth(200);

        tableView.getColumns().addAll(nameColumn, priceColumn, availaibilityColumn, categoryColumn);


        tableView.setItems(menuData);

        // Create a layout
        VBox layout = new VBox(10);

        layout.setPrefSize(800, 576);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(tableView);
        layout.setStyle(
                "-fx-font-family: Algerian;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 20;" +
                        "-fx-text-fill: black;"
        );

        // Add the layout to the root pane
        root.getChildren().add(layout);





        Button go = (Button) root.lookup("#Goback");
        Button logout = (Button) root.lookup("#Logout");
        go.setOnAction(e -> {
            try {
                handleGoback();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        logout.setOnAction(e -> {
            try {
                handleLogout();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Scene scene = new Scene(root, 1024, 576);
        go.toFront();
        logout.toFront();
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }

    public void handleGoback() throws IOException {r();}
    public void handleCartOperation() throws IOException {
        stage.hide();
        System.out.println("Cart Operation");
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

            System.out.println(cartOperation.add_to_cart(item, quan));
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
        r();

    }
    public void handleTrackOrder() throws IOException {
        stage.hide();
        System.out.println("Track Order");
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
        }
        r();
    }
    public void handleGetVIPaccess() throws IOException {
        stage.hide();
        System.out.println("Get VIP access");
        System.out.println("Pay 500 to become our VIP customer");

        int money = i.nextInt();
        i.nextLine();

        if (money == 500) {
            if (setVipStatus(getID(), true)) {
                System.out.println("You are now a VIP customer.");
            } else {
                System.out.println("Error: Could not update VIP status.");
            }
        } else {
            System.out.println("Insufficient money.");
        }
        r();
    }
    private boolean setVipStatus(String email_id, boolean isVip) {

        boolean updated = false;
        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("customer_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(email_id)) {
                    data[2] = isVip ? "1" : "0";
                    updated = true;
                }
                updatedLines.add(String.join(",", data));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("customer_info.txt"))) {
                for (String line : updatedLines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
                return false;
            }
            return true;
        }

        return false;
    }

    public void handleItemReview(){
        stage.hide();
        System.out.println("Item review");
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
            stage.show();
        }
    }

    public void handleLogout() throws IOException {
        Login customer = new Login(helloApplication, stage, 1);
    }

    public String getID() {
        return id;
    }

    public void setID(String stu_Email) {
        id = stu_Email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String stu_Password) {
        pass = stu_Password;
    }


    // Utility Methods
    private boolean isValidEmail(String email_id) {
        return email_id.matches("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");
    }

    private boolean checkUser(String email_id) {
        return customerInfo.getCredential().containsKey(email_id);
    }

    private boolean validatePassword(String email_id, String password) {
        Customer_data customer = customerInfo.getCredential().get(email_id);
        return customer != null && customer.getPassword().equals(password);
    }

    private int getYesOrNo(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if ("1".equals(input) || "0".equals(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.print("Invalid input. Please enter 1 for Yes or 0 for No: ");
            }
        }
    }

    public String logininput(int userMode){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Your Email Id: ");
        String emailId = scanner.nextLine();
        System.out.print("Enter Your Password: ");
        String password = scanner.nextLine();
        return login(emailId,password, userMode);
    }

    public String  login(String email, String pass,int userMode) {
        customerInfo = new Customer_Info();
        customerInfo.loadFromFile();

        String emailId = email;
        if (!isValidEmail(emailId)){
//            System.out.println("Invalid Email format");
            return  "Invalid Email format";
        }
        setID(emailId);

        while (true) {
            try {
                if (userMode == 0) {
                    return handleSignup(emailId, pass);
                } else {
                    return handleLogin(emailId, pass);
                }
            } catch (InvalidLoginException e) {
                    return e.getMessage();
//                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }


    private String handleSignup(String emailId, String pass) {
        if (checkUser(emailId)) {
//            System.out.println("Email is already registered.");
            return ("Email is already registered.");
        }

//        System.out.print("Create new password: ");
        String password = pass;
        setPass(password);

        customerData = new Customer_data(getID(), getPass());
        cartOperation = new Cart_Operation(customerData);

        customerInfo.getCredential().put(getID(), customerData);
        customerInfo.saveToFile();

//        System.out.println ("Account created successfully.");
        return ("Account created successfully.");
    }

    private String handleLogin(String emailId, String pass) throws InvalidLoginException {
        if (!checkUser(emailId)) {

//            System.out.println("User email not found.");
            return ("User email not found.");
        }
        String password = pass;
        setPass(password);

        if (!validatePassword(emailId, getPass())) {
            throw new InvalidLoginException("Wrong password!");
        }

        customerData = customerInfo.getCredential().get(getID());
        cartOperation = new Cart_Operation(customerData);
//        System.out.println("Login successful!");
        return ("Login successful!");
    }
//    public String login(String email, String pass, int user_mode) {
//        customerInfo = new Customer_Info();
//        customerInfo.loadFromFile();
//
//        try {
//            if (!isValidEmail(email)) {
//                throw new InvalidLoginException("Invalid Email Format");
//            }
//
//            setID(email);
//
//            if (user_mode == 0) {
//                if (checkUser(email)) {
//                    return "Email is already registered.";
//                }
//
//
//                setPass(pass);
//                customerData = new Customer_data(getID(), getPass());
//                cartOperation = new Cart_Operation(customerData);
//
//                customerInfo.getCredential().put(getID(), customerData);
//                customerInfo.saveToFile();
//
//                return "Account created successfully.";
//            } else if (user_mode == 1) {
//                if (!checkUser(email)) {
//                    return "User email not found.";
//                }
//
//                setPass(pass);
//
//                if (!validatePassword(email, getPass())) {
//                    throw new InvalidLoginException("Wrong password!");
//                }
//
//                customerData = customerInfo.getCredential().get(getID());
//                cartOperation = new Cart_Operation(customerData);
//                return "Login successful!";
//            } else {
//                return "Invalid user mode. Please choose 0 (Sign-up) or 1 (Login).";
//            }
//        } catch (InvalidLoginException e) {
//            return e.getMessage();
//        } catch (Exception e) {
//            return "An unexpected error occurred: " + e.getMessage();
//        }
//    }


}



//    public int Method(){
//        System.out.println("                              ___ CUSTOMER PAGE___                              ");
//        System.out.println("    ○  Browse Menu                                          ○  Cart Operations\n");
//        System.out.println("    ○  Track Order                                          ○  Get VIP access\n");
//        System.out.println("    ○  Review Section\n");
//
//
//        int method_no = 0;
//        while (true) {
//            System.out.println("Choose what you want to see..\n");
//            System.out.println("1 ->    Browse Menu");
//            System.out.println("2 ->    Cart Operations");
//            System.out.println("3 ->    Track Order");
//            System.out.println("4 ->    Get VIP access");
//            System.out.println("5 ->    Item Review");
//            System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");
//
//            try {
//                method_no = i.nextInt();
//                break;
//            } catch (InputMismatchException e) {
//                System.err.println("Invalid input! Please enter a valid number");
//                i.next();
//            }
//
//        }
//        while (method_no!=0){
//            if (method_no == 2){
//
//                System.out.println("Cart..\n");
//                System.out.println("1 ->    Add items");
//                System.out.println("2 ->    Modify quantity");
//                System.out.println("3 ->    Remove items");
//                System.out.println("4 ->    View total");
//                System.out.println("5 ->    Checkout");
//                System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");
//
//                System.out.print("Select options: ");
//                int select = i.nextInt();
//                i.nextLine();
//                if (select==1){
//                    Browse_Menu v = new Browse_Menu();
//                    v.displayMenu();
//
//                    System.out.print("Enter Item: ");
//                    String item = i.nextLine();
//
//                    System.out.print("Enter Quantity: ");
//                    int quan = i.nextInt();
//                    i.nextLine();
//
//                    cartOperation.add_to_cart(item, quan);
//                } else if (select==2) {
//                    System.out.print("Enter Item: ");
//                    String item = i.nextLine();
//
//                    System.out.print("Enter new Quantity: ");
//                    int quan = i.nextInt();
//
//                    cartOperation.modify_quantity(item, quan);
//                } else if (select==3) {
//                    System.out.print("Which Item You Want to Remove: ");
//                    String item = i.nextLine();
//
//                    cartOperation.remove_from_cart(item);
//                }else if (select == 4) {
//                    cartOperation.view_cart();
//                }else if (select == 5){
//                    System.out.print("Any Special request: ");
//                    String special = i.nextLine();
//
//
//                    System.out.print("Enter Delivery Address: ");
//                    String add = i.nextLine();
//
//                    System.out.println("COMPLETE PAYMENT ");
//                    System.out.print("Enter payment: ");
//                    int payment = i.nextInt();
//
//                    cartOperation.checkout(special, payment);
//
//                }
//
//            }
//            else if (method_no == 1 ) {
//
//                System.out.println("Choose what you want to see..\n");
//                System.out.println("1 ->    View all items");
//                System.out.println("2 ->    Search functionality");
//                System.out.println("3 ->    Fiter by Category");
//                System.out.println("4 ->    Sort by prices");
//                System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");
//                int select = i.nextInt();
//                i.nextLine();
//                Browse_Menu v = new Browse_Menu();
//                if (select == 1) {
//                    v.displayMenu();
//                }else if(select == 2){
//                    System.out.print("Search item: ");
//                    String name = i.nextLine();
//                    v.display_specific_item(name);
//                } else if (select == 3) {
//                    System.out.print("Filter category: ");
//                    String name = i.nextLine();
//                    v.displayBYcategory(name);
//
//                } else if (select == 4) {
//                    v.displayBYprice();
//                }else{
//                    break;
//                }
//
//
//            }
//            else if (method_no == 3) {
//                System.out.println("View Order..\n");
//                System.out.println("1 ->    View Order Status");
//                System.out.println("2 ->    Cancel Order");
//                System.out.println("3 ->    Order history");
//                System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");
//                System.out.print("Select options: ");
//                int select = i.nextInt();
//                i.nextLine();
//                Order_Track orderTrack = new Order_Track();
//                if (select == 1) {
//                    orderTrack.View_Order_Status(getID());
//                }else if (select == 2) {
//                    orderTrack.Cancelled_Order(getID());
//                }else if (select == 3) {
//                    orderTrack.Order_History(getID());
//                }else {
//                    break;
//                }
//            }
//            else if (method_no == 4) {
//                System.out.println("Pay 500 to become our VIP customer");
//                int money = i.nextInt();
//                i.nextLine();
//                if(money==500) {
//                    Customer_Info c = new Customer_Info();
//                    c.getCredential().get(getID()).setVip(true);
//                    System.out.println("You are VIP customer now");
//                }
//                else{
//                    System.out.println("Insufficient money");
//                }
//            } else if (method_no == 5) {
//                Order_Review o = new Order_Review(new Menu_Management());
//                System.out.println("View Review..\n");
//                System.out.println("1 ->    Give Review");
//                System.out.println("2 ->    View Review");
//                System.out.println("                                                                            LOG OUT(PRESS 0)                            \n");
//                System.out.print("Select options: ");
//                int select = i.nextInt();
//                i.nextLine();
//                if (select == 1) {
//                    o.give_Review(getID());
//
//                }else if (select == 2) {
//                    o.view_review(getID());
//                }else {
//                    break;
//                }
//
//            }
//            break;
//        }
//        return method_no;
//    }
//}
