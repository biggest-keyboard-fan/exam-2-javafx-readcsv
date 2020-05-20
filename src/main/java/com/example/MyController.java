package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MyController {
    public MyController(){}
    @FXML
    private Pane root;

    @FXML
    protected void initialize() {
    }

    public void addButton(String text, double x, double y){
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        root.getChildren().add(button);
    }
    public void addLabel(String text, double x, double y){
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        root.getChildren().add(label);
    }
}
