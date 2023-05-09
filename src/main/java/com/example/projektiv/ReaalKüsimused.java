package com.example.projektiv;

import javafx.application.Application;
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
        }
        else if (loendur == loetud.size()){
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));

            //Võrdleme, millisele erialale sobib kõige paremini
            if(matT.size() > majT.size() && matT.size()>infT.size()){
                Text tulemus = new Text("Sobid matemaatikasse");
                Text protsent = new Text(protsendid(matT, loetud) + "% vastuseid sobitavad sind kõige paremini matemaatikasse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if(majT.size() > matT.size() && majT.size() > infT.size()){
                Text tulemus = new Text("Sobid majandusse");
                Text protsent = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini majandusse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if(infT.size() > majT.size() && infT.size() >matT.size()){
                Text tulemus = new Text("Sobid informaatikasse");
                Text protsent = new Text(protsendid(infT, loetud) + "% vastuseid sobitavad sind kõige paremini informaatikasse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if(matT.size() == majT.size()){
                Text tulemus = new Text("Sobid nii majandusse kui matemaatikasse");
                Text protsent = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini majandusse.");
                Text protsent2 = new Text(protsendid(matT, loetud) + "% vastuseid sobitavad sind kõige paremini matemaatikasse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);

            }
            else if(matT.size() == infT.size()){
                Text tulemus = new Text("Sobid nii informaatikasse kui matemaatikasse");
                Text protsent = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini informaatikasse.");
                Text protsent2 = new Text(protsendid(matT, loetud) + "% vastuseid sobitavad sind kõige paremini matemaatikasse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);
            }
            else if(infT.size() == majT.size()){
                Text tulemus = new Text("Sobid nii informaatikasse kui majandusse");
                Text protsent = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini informaatikasse.");
                Text protsent2 = new Text(protsendid(matT, loetud) + "% vastuseid sobitavad sind kõige paremini majandusse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);

            }
            //loome sektordiagrammi, kus tehakse võrdlus kõigi erialade vahel
            ObservableList<PieChart.Data> sektordiagrammiSisu = FXCollections.observableArrayList(
                    new PieChart.Data("Informaatika", infT.size()),
                    new PieChart.Data("Majandus", majT.size()),
                    new PieChart.Data("Matemaatika", matT.size())
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
