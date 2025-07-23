package com.example.guibilling;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.IOException;


public class Login {
    private Stage stage;
    private int loginMode;
    private static final float worldX = 1024, worldY = 576;
    private Customer customer;
    private Administrator administrator;
    public float getWorldX(){
        return worldX;
    }
    public float getWorldY(){
        return worldY;
    }
    private  HelloApplication helloApplication;
    public Login(HelloApplication helloApplication, Stage stage, int loginMode) throws IOException {
        this.stage = stage;
        this.loginMode = loginMode;
        this.helloApplication = helloApplication;
//        super(stage);
        set();
    }

//    @Override
    public void set() {

        VBox loginLayout = new VBox(20);


        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/bg.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                    BackgroundSize.AUTO,
                    BackgroundSize.AUTO,
                    false, false,
                    true, true
            )
        );
        loginLayout.setBackground(new Background(backgroundImage));
        loginLayout.setStyle("-fx-alignment: center; -fx-padding: 25; -fx-font-size: 20;");

        Label log_Sign = new Label();
        log_Sign.setStyle("-fx-font-weight: bold; -fx-alignment: center;");
        if (loginMode == 1) {
            log_Sign.setText("Log in as");

        }else if (loginMode == 0) {
            log_Sign.setText("Sign in as");
        }

        Button Customer_button = new Button("Customer");
        Button Admin_button = new Button("Admin");
        Button Back_button = new Button("Back");

        Back_button.setStyle(
                        "-fx-background-position: center; " +
                        "-fx-padding: 10px 20px;" +
                        "-fx-alignment: center;"
        );
        Back_button.setOnAction(e -> {
//            System.out.println("Back button clicked");
            try {

                helloApplication.showLoginPage(stage);
            }catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Customer_button.setPrefSize(150, 50);

//        Admin_button.setStyle(
//                "-fx-background-image: url('/C:/Users/Karan/Desktop/Project_Main/assets/bar.png'); " +
//                        "-fx-background-size: cover; " +
//                        "-fx-background-repeat: no-repeat; " +
//                        "-fx-background-position: center; " +
//                        "-fx-padding: 10px 20px;"
//        );
//        Admin_button.setBackground(new Background(backgroundImage));
        Admin_button.setPrefSize(150, 50);
        Admin_button.setOnAction(e -> {
            try {
                stage.hide();
                usertype(stage, 1);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Customer_button.setOnAction(e -> {
            try {
                // Navigate based on user type (1 for Admin)
                stage.hide();
                usertype(stage, 0);
                stage.show();
            } catch (IOException ex) {
                // Handle potential IOExceptions gracefully
                System.err.println("Error navigating to the Admin interface: " + ex.getMessage());
                ex.printStackTrace();  // Optionally log the stack trace
                // You could also show an error message to the user if needed
            }
        });



        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(Customer_button, Admin_button);

        loginLayout.getChildren().addAll(log_Sign, buttons, Back_button);
        Scene scene = new Scene(loginLayout, getWorldX(), getWorldY());
        stage.setScene(scene);
    }
    public void usertype(Stage stage, int val) throws IOException {
        if (val == 0){
            customer = new Customer(helloApplication, stage, loginMode);
        }else {
            administrator = new Administrator(helloApplication, stage, loginMode);
        }
    }
}
