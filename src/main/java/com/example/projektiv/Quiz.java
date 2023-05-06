package com.example.projektiv;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Quiz extends Application {

    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        // Scene 1
        Label label1 = new Label("What is the capital of France?");
        RadioButton paris = new RadioButton("Paris");
        RadioButton london = new RadioButton("London");
        RadioButton berlin = new RadioButton("Berlin");

        ToggleGroup group1 = new ToggleGroup();
        paris.setToggleGroup(group1);
        london.setToggleGroup(group1);
        berlin.setToggleGroup(group1);

        Button button1 = new Button("Submit");

        VBox layout1 = new VBox(10);
        layout1.setPadding(new Insets(20, 20, 20, 20));
        layout1.getChildren().addAll(label1, paris, london, berlin, button1);

        scene1 = new Scene(layout1, 400, 200);

        // Scene 2
        Label label2 = new Label("What is the largest country in the world?");
        RadioButton russia = new RadioButton("Russia");
        RadioButton canada = new RadioButton("Canada");
        RadioButton china = new RadioButton("China");

        ToggleGroup group2 = new ToggleGroup();
        russia.setToggleGroup(group2);
        canada.setToggleGroup(group2);
        china.setToggleGroup(group2);

        Button button2 = new Button("Submit");

        VBox layout2 = new VBox(10);
        layout2.setPadding(new Insets(20, 20, 20, 20));
        layout2.getChildren().addAll(label2, russia, canada, china, button2);

        scene2 = new Scene(layout2, 400, 200);

        // Button actions
        button1.setOnAction(e -> {
            if (paris.isSelected()) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
            window.setScene(scene2);
        });

        button2.setOnAction(e -> {
            if (russia.isSelected()) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
            window.close();
        });
        window.setScene(scene1);
        window.show();

    }

    public static void main(String[] args) {
        launch();
    }

}