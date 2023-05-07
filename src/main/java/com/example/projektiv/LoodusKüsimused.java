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

public class LoodusKüsimused {
    public ArrayList<String> loetud;
    public int loendur = 0;

    public ArrayList<Integer> bioT = new ArrayList<>();
    public ArrayList<Integer> FKMT = new ArrayList<>();
    public ArrayList<Integer> geoT = new ArrayList<>();

    public void start(Stage stage) throws Exception {
        loetud = new ArrayList<>();
        String rida = null;
        try (BufferedReader br = new BufferedReader(new FileReader("punktidReaal.txt"))) {
            while ((rida = br.readLine()) != null) {
                loetud.add(rida);
            }
        }
        küsimus(stage);
    }
    public void küsimus(Stage stage){
        if(loendur != loetud.size()-1) {
            String[] osad = loetud.get(loendur).split(" - ");
            Label label = new Label(osad[0]);
            RadioButton bio = new RadioButton(osad[1]);
            RadioButton fkm = new RadioButton(osad[2]);
            RadioButton geo = new RadioButton(osad[3]);

            ToggleGroup toggleGroup1 = new ToggleGroup();
            bio.setToggleGroup(toggleGroup1);
            fkm.setToggleGroup(toggleGroup1);
            geo.setToggleGroup(toggleGroup1);

            Button valik = new Button("Vali");
            valik.setOnAction(e -> {
                if(bio.isSelected()){
                    bioT.add(1);
                }
                else if(fkm.isSelected()){
                    FKMT.add(1);
                }
                else if(geo.isSelected()){
                    geoT.add(1);
                }

                loendur++;
                küsimus(stage);
            });
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(label, bio, fkm, geo, valik);

            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
            stage.show();

        }
    }

}


