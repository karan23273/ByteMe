package com.example.guibilling;

import javafx.stage.Stage;

import java.io.IOException;

public abstract class customClass {
    protected Stage stage;
    public Stage getStage() {
        return stage;
    }
    public customClass(Stage stage) throws IOException {
        this.stage = stage;
        set();
    }
    public abstract void set() throws IOException;
}
