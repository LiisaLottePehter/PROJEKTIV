package com.example.projektiv;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReaalKüsimused extends Application {
    public ArrayList<String> loetud;
    public int loendur = 0;

    public ArrayList<Integer> matT = new ArrayList<>();
    public ArrayList<Integer> infT = new ArrayList<>();
    public ArrayList<Integer> majT = new ArrayList<>();
    @Override
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
        if(loendur != loetud.size()) {
            String[] osad = loetud.get(loendur).split(" - ");
            Label label = new Label(osad[0]);
            RadioButton mat = new RadioButton(osad[1]);
            RadioButton inf = new RadioButton(osad[2]);
            RadioButton maj = new RadioButton(osad[3]);

            ToggleGroup toggleGroup1 = new ToggleGroup();
            mat.setToggleGroup(toggleGroup1);
            inf.setToggleGroup(toggleGroup1);
            maj.setToggleGroup(toggleGroup1);

            Button valik = new Button("Vali");
            valik.setOnAction(e -> {
                if(mat.isSelected()){
                    matT.add(1);
                }
                else if(inf.isSelected()){
                    infT.add(1);
                }
                else if(maj.isSelected()){
                    majT.add(1);
                }

                loendur++;
                küsimus(stage);
            });
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(label, mat, inf, maj, valik);

            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
            stage.show();

            }else if (loendur == loetud.size()){
                if(matT.size() > majT.size() && matT.size()>infT.size()){
                    Text tulemus = new Text("Sobid matemaatikasse");
                    VBox layout = new VBox(20);
                    layout.setStyle("-fx-background-color: #F2F2DC;");
                    layout.setPadding(new Insets(30, 30, 30, 30));
                    layout.getChildren().addAll(tulemus);

                    Scene stseen = new Scene(layout, 625, 525);
                    stage.setScene(stseen);
                }
                else if(majT.size() > matT.size() && majT.size() > infT.size()){
                    Text tulemus = new Text("Sobid majandusse");
                    Text protsent = new Text(protsendid(majT, loetud));
                    VBox layout = new VBox(20);
                    layout.setStyle("-fx-background-color: #F2F2DC;");
                    layout.setPadding(new Insets(30, 30, 30, 30));
                    layout.getChildren().addAll(tulemus, protsent);

                    Scene stseen = new Scene(layout, 625, 525);
                    stage.setScene(stseen);
                }
                else if(infT.size() > majT.size() && infT.size() >matT.size()){
                    Text tulemus = new Text("Sobid informaatikasse");
                    VBox layout = new VBox(20);
                    layout.setStyle("-fx-background-color: #F2F2DC;");
                    layout.setPadding(new Insets(30, 30, 30, 30));
                    layout.getChildren().addAll(tulemus);

                    Scene stseen = new Scene(layout, 625, 525);
                    stage.setScene(stseen);
                }
            }
        }

    public String protsendid(ArrayList<Integer> üksList, ArrayList<String> teineList){
        int protsent = üksList.size() * 100/ teineList.size();
        String protsentString = Integer.toString(protsent);
        return protsentString;
    }

}
