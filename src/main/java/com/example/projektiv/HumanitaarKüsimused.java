package com.example.projektiv;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class HumanitaarKüsimused {
    public ArrayList<String> loetud;
    public int loendur = 0;

    public ArrayList<Integer> ajalT = new ArrayList<>();
    public ArrayList<Integer> ikT = new ArrayList<>();
    public ArrayList<Integer> ekT = new ArrayList<>();

    public void start(Stage stage) throws Exception {
        loetud = new ArrayList<>();
        String rida = null;
        try (BufferedReader br = new BufferedReader(new FileReader("punktidHumanitaar.txt"))) {
            while ((rida = br.readLine()) != null) {
                loetud.add(rida);
            }
        }
        küsimus(stage);
    }
    public void küsimus(Stage stage){
        if(loendur != loetud.size()) {
            String[] osad = loetud.get(loendur).split(" - ");
            Label label = new Label(osad[0]);
            RadioButton ajal = new RadioButton(osad[1]);
            RadioButton ik = new RadioButton(osad[2]);
            RadioButton ek = new RadioButton(osad[3]);

            ToggleGroup toggleGroup1 = new ToggleGroup();
            ajal.setToggleGroup(toggleGroup1);
            ik.setToggleGroup(toggleGroup1);
            ek.setToggleGroup(toggleGroup1);

            Button valik = new Button("Vali");
            valik.setOnAction(e -> {
                if(ajal.isSelected()){
                    ajalT.add(1);
                }
                else if(ik.isSelected()){
                    ikT.add(1);
                }
                else if(ek.isSelected()){
                    ekT.add(1);
                }

                loendur++;
                küsimus(stage);
            });
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(label, ajal, ik, ek, valik);

            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
            stage.show();

        }else if(loendur == loetud.size()){
            Label tulemus = new Label("Sinu tulemus on......");
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(tulemus);

            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
        }
    }

}

