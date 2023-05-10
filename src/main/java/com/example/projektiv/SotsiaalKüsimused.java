package com.example.projektiv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

public class SotsiaalKüsimused {
    public ArrayList<String> loetud;
    public int loendur = 0;

    public ArrayList<Integer> juuraT = new ArrayList<>();
    public ArrayList<Integer> psuhhT = new ArrayList<>();
    public ArrayList<Integer> majT = new ArrayList<>();

    public void start(Stage stage) throws Exception {
        loetud = new ArrayList<>();
        String rida = null;
        try (BufferedReader br = new BufferedReader(new FileReader("punktidSotsiaal.txt"))) {
            while ((rida = br.readLine()) != null) {
                loetud.add(rida);
            }
        }
        küsimus(stage);
    }

    public void küsimus(Stage stage) {

        if (loendur != loetud.size()) {
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
                    if (ajal.isSelected()) {
                        juuraT.add(1);
                    } else if (ik.isSelected()) {
                        psuhhT.add(1);
                    } else if (ek.isSelected()) {
                        majT.add(1);
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

        } else if (loendur == loetud.size()) {
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));

            //Võrdleme, millisele erialale sobib kõige paremini
            if(juuraT.size() > majT.size() && juuraT.size()>psuhhT.size()){
                Text tulemus = new Text("Sobid õigusteadusesse");
                Text protsent = new Text(protsendid(juuraT, loetud) + "% vastuseid sobitavad sind kõige paremini õigusteadusesse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if(majT.size() > juuraT.size() && majT.size() > psuhhT.size()){
                Text tulemus = new Text("Sobid majandusse");
                Text protsent = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini majandusse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if(psuhhT.size() > majT.size() && psuhhT.size() >juuraT.size()){
                Text tulemus = new Text("Sobid psühholoogiasse");
                Text protsent = new Text(protsendid(psuhhT, loetud) + "% vastuseid sobitavad sind kõige paremini psühholoogiasse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if(juuraT.size() == majT.size()){
                Text tulemus = new Text("Sobid nii majandusse kui õigusteadusesse");
                Text protsent = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini majandusse.");
                Text protsent2 = new Text(protsendid(juuraT, loetud) + "% vastuseid sobitavad sind kõige paremini õigusteadusesse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);

            }
            else if(juuraT.size() == psuhhT.size()){
                Text tulemus = new Text("Sobid nii õigusteadusesse kui psühholoogiasse");
                Text protsent = new Text(protsendid(juuraT, loetud) + "% vastuseid sobitavad sind kõige paremini õigusteadusesse.");
                Text protsent2 = new Text(protsendid(psuhhT, loetud) + "% vastuseid sobitavad sind kõige paremini psühholoogiasse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);
            }
            else if(psuhhT.size() == majT.size()){
                Text tulemus = new Text("Sobid nii psühholoogiasse kui majandusse");
                Text protsent = new Text(protsendid(psuhhT, loetud) + "% vastuseid sobitavad sind kõige paremini psühholoogiasse.");
                Text protsent2 = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini majandusse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);

            }
            //loome sektordiagrammi, kus tehakse võrdlus kõigi erialade vahel
            ObservableList<PieChart.Data> sektordiagrammiSisu = FXCollections.observableArrayList(
                    new PieChart.Data("Õigusteadus", juuraT.size()),
                    new PieChart.Data("Majandus", majT.size()),
                    new PieChart.Data("Psühholoogia", psuhhT.size())
            );

            PieChart sektordiagramm = new PieChart(sektordiagrammiSisu);
            layout.getChildren().add(sektordiagramm);
            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
        }

    }
    public String protsendid(ArrayList<Integer> üksList, ArrayList<String> teineList){
        int protsent = üksList.size() * 100/ teineList.size();
        String protsentString = Integer.toString(protsent);
        return protsentString;
    }

}

