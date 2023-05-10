package com.example.projektiv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
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
        //esimese küsimuse juures on selgitus, kuidas peaks vastama
        if(loendur == 0){
            String[] osad = loetud.get(loendur).split(" - ");
            Label selgitus = new Label("Järgmistele küsimusele vastata numbritega vahemikus 1-3, vastavalt sellele, mis tundub kõige sobilikum. \n1 - kehtib sinu kohta vähe, 2 - kehtib sinu kohta mingil määral, 3 - kehtib sinu kohta palju");
            Label label = new Label(osad[0]);
            RadioButton ajal = new RadioButton(osad[1]);
            RadioButton ik = new RadioButton(osad[2]);
            RadioButton ek = new RadioButton(osad[3]);

            Button valik = new Button("Vali");

            valik.setOnAction(e -> {
                if(!(ajal.isSelected() || ik.isSelected() || ek.isSelected())){
                    Alert valeValik = new Alert(Alert.AlertType.ERROR);
                    valeValik.setTitle("Viga");
                    valeValik.setHeaderText("Ei valitud midagi");
                    valeValik.setContentText("Palun vali uuesti");
                    valeValik.showAndWait();
                }
                else {
                    if (ajal.isSelected()) {
                        juuraT.add(1);
                    } else if (ik.isSelected()) {
                        psuhhT.add(1);
                    } else if (ek.isSelected()) {
                        majT.add(1);
                    }
                    loendur++;
                    küsimus(stage);
                }
            });
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(selgitus, label, ajal, ik, ek, valik);

            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
            stage.show();

        }
        else if (loendur != loetud.size()) {
            String[] osad = loetud.get(loendur).split(" - ");
            Label label = new Label(osad[0]);
            RadioButton ajal = new RadioButton(osad[1]);
            RadioButton ik = new RadioButton(osad[2]);
            RadioButton ek = new RadioButton(osad[3]);

            Button valik = new Button("Vali");

            valik.setOnAction(e -> {
                if(!(ajal.isSelected() || ik.isSelected() || ek.isSelected())){
                    Alert valeValik = new Alert(Alert.AlertType.ERROR);
                    valeValik.setTitle("Viga");
                    valeValik.setHeaderText("Ei valitud midagi");
                    valeValik.setContentText("Palun vali uuesti");
                    valeValik.showAndWait();
                }
                else {
                    if (ajal.isSelected()) {
                        juuraT.add(1);
                    } else if (ik.isSelected()) {
                        psuhhT.add(1);
                    } else if (ek.isSelected()) {
                        majT.add(1);
                    }
                    loendur++;
                    küsimus(stage);
                }
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
            if (juuraT.size() > majT.size() && juuraT.size() > psuhhT.size()) {
                Text tulemus = new Text("Sobid õigusteadusesse");
                Text protsent = new Text(protsendid(juuraT, loetud) + "% vastuseid sobitavad sind kõige paremini õigusteadusesse.");
                layout.getChildren().addAll(tulemus, protsent);

            } else if (majT.size() > juuraT.size() && majT.size() > psuhhT.size()) {
                Text tulemus = new Text("Sobid majandusse");
                Text protsent = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini majandusse.");
                layout.getChildren().addAll(tulemus, protsent);

            } else if (psuhhT.size() > majT.size() && psuhhT.size() > juuraT.size()) {
                Text tulemus = new Text("Sobid psühholoogiasse");
                Text protsent = new Text(protsendid(psuhhT, loetud) + "% vastuseid sobitavad sind kõige paremini psühholoogiasse.");
                layout.getChildren().addAll(tulemus, protsent);

            } else if (juuraT.size() == majT.size()) {
                Text tulemus = new Text("Sobid nii majandusse kui õigusteadusesse");
                Text protsent = new Text(protsendid(majT, loetud) + "% vastuseid sobitavad sind kõige paremini majandusse.");
                Text protsent2 = new Text(protsendid(juuraT, loetud) + "% vastuseid sobitavad sind kõige paremini õigusteadusesse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);

            } else if (juuraT.size() == psuhhT.size()) {
                Text tulemus = new Text("Sobid nii õigusteadusesse kui psühholoogiasse");
                Text protsent = new Text(protsendid(juuraT, loetud) + "% vastuseid sobitavad sind kõige paremini õigusteadusesse.");
                Text protsent2 = new Text(protsendid(psuhhT, loetud) + "% vastuseid sobitavad sind kõige paremini psühholoogiasse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);
            } else if (psuhhT.size() == majT.size()) {
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

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tulemused.txt", true), "UTF-8"))) {
                bw.write("Õigusteadus - " + protsendid(juuraT, loetud));
                bw.newLine();
                bw.write("Majandus - " + protsendid(majT, loetud));
                bw.newLine();
                bw.write("Psühholoogia - " + protsendid(psuhhT, loetud));
                bw.newLine();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String protsendid(ArrayList<Integer> üksList, ArrayList<String> teineList){
        int protsent = üksList.size() * 100/ teineList.size();
        String protsentString = Integer.toString(protsent);
        return protsentString;
    }

}

