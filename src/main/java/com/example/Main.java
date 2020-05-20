package com.example;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main extends Application {
    static private String fileContent;
    public static Pane root;
    public static MyController controller;
    public static void main(String[] args) throws Exception  {launch(args);}
    private static void processQuestion(String[] cells,int row){
        String questionText = cells[1];
        System.out.println("Processing question "+cells[0]+":"+cells[1]);
        controller.addLabel(questionText,300,10+(30*row));
    }
    private static void processAnswers(String[] cells,int row){
        System.out.println("Processing answers "+String.join(",",cells));
        for (int i = 0; i < 4; i++) {
            String cellText = cells[i].replace("\n","").replace("\r","");
            //Not incrementing bc first cell is empty
            if(!cellText.isEmpty()) controller.addButton(cellText,150*i,15+(30*row));
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        try(Scanner scanner = new Scanner(new File("lab-questions.csv"))){
            fileContent = scanner.useDelimiter("\\Z").next();
        }
        //Load javafx form
        try(FileInputStream fi = new FileInputStream("csv-to-form.fxml")){
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(fi);
            controller = loader.getController();
        }
        primaryStage.setTitle("sql-label-app");
        try {
            primaryStage.setScene(new Scene(root, 640, 480));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String[] lines = fileContent.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] cells = lines[i].split(",");
            if(cells[0].isEmpty()){
                //This is a answer row
                processAnswers(cells,i);
            }else{
                //This is a question row
                processQuestion(cells,i);
            }
        }
    }
}
