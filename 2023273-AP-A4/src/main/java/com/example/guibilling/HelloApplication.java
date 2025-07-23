package com.example.guibilling;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final float worldX = 1024, worldY = 576;

    public float getWorldX(){
        return worldX;
    }
    public float getWorldY(){
        return worldY;
    }


    private Stage stage;

    private Login login;
    BackgroundImage backgroundImage = new BackgroundImage(
            new Image(getClass().getResource("/bg.png").toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(
                    BackgroundSize.AUTO,
                    BackgroundSize.AUTO,   // Automatically adjust height
                    false, false,          // Do not stretch
                    true, true
            )
    );
    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;

        // Create root container
        VBox root = new VBox(20);

        root.setStyle("-fx-alignment: center; -fx-padding: 20;");


        root.setBackground(new Background(backgroundImage));

        Text welcomeLabel = new Text(100, 200,"WELCOME TO BYTE ME");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Enter/Exit Application Buttons
        Button enterButton = new Button("Enter Application");
        Button exitButton = new Button("Exit Application");
        exitButton.setPrefSize(200, 50);
        enterButton.setPrefSize(200, 50);
        enterButton.setStyle("-fx-font-size: 18px;");
        exitButton.setStyle("-fx-font-size: 18px;");

        // Event handlers for enter and exit buttons
        enterButton.setOnAction(e -> showLoginPage(primaryStage));
        exitButton.setOnAction(e -> {
            System.out.println("YOU EXITED THE APPLICATION :)");
            primaryStage.close();
        });

        root.getChildren().addAll(welcomeLabel, enterButton, exitButton);

        Scene scene = new Scene(root, getWorldX(), getWorldY());
        primaryStage.setTitle("BYTE ME");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display the login/sign-up screen
    public void showLoginPage(Stage primaryStage) {
        VBox loginRoot = new VBox(20);
        loginRoot.setStyle("-fx-alignment: center; -fx-padding: 20;");
        loginRoot.setBackground(new Background(backgroundImage));
        Label loginLabel = new Label("○ LOG IN                          ○  SIGN UP");
        loginLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox button = new HBox(100);
        button.setStyle("-fx-alignment: center;");
        Button loginButton = new Button("Log in");
        loginButton.setPrefSize(150, 50);
        Button signUpButton = new Button("Sign Up");

        Button backButton = new Button("Back");

        signUpButton.setPrefSize(150, 50);
        button.getChildren().addAll(loginButton, signUpButton);

        loginButton.setStyle("-fx-font-size: 18px;");
        signUpButton.setStyle("-fx-font-size: 18px;");

        loginButton.setOnAction(e -> {
            try {
                handleLoginOrSignUp(primaryStage, 1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        signUpButton.setOnAction(e -> {
            try {
                handleLoginOrSignUp(primaryStage, 0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        loginRoot.getChildren().addAll(loginLabel,button);

        Scene loginScene = new Scene(loginRoot, getWorldX(), getWorldY());
        primaryStage.setScene(loginScene);
    }

    private void handleLoginOrSignUp(Stage primaryStage, int userMode) throws IOException {
        // Call the Login object method to handle logic based on the mode (login/sign-up)
        login = new Login(this, primaryStage, userMode);
        if (userMode !=0){
//            System.out.println("You chose Log In ");
        }else {
//            System.out.println("You chose SignUp ");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
