package com.example.guibilling;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class pendingOrderController {
    @FXML
    private Button goBack;
    @FXML
    private Button logOut;
    @FXML
    private Label pendingOrderLabel;
    @FXML
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleGoback(){
//        r();
    }
    @FXML
    private void handleLogout() throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        Login customer = new Login(helloApplication, new Stage(), 1);
    }




}
