package com.example.projektiv;

import javafx.application.Application;
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

public class ReaalKüsimused extends Application {
    public ArrayList<String> loetud;
    public int loendur = 0;

    public ArrayList<Integer> matT = new ArrayList<>();
    public ArrayList<Integer> infT = new ArrayList<>();
    public ArrayList<Integer> arvTehT = new ArrayList<>();
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
        //esimese küsimuse juures on selgitus, kuidas peaks vastama
        if(loendur == 0){
            String[] osad = loetud.get(loendur).split(" - ");
            Label selgitus = new Label("Järgmistele küsimusele vastata numbritega vahemikus 1-3, " +
                    "vastavalt sellele, mis tundub kõige sobilikum. \n1 - kehtib sinu kohta vähe, 2 - kehtib sinu kohta mingil määral, 3 - kehtib sinu kohta palju");
            Label label = new Label(osad[0]);
            RadioButton mat = new RadioButton(osad[1]);
            RadioButton inf = new RadioButton(osad[2]);
            RadioButton arvTeh = new RadioButton(osad[3]);

            Button valik = new Button("Vali");
            valik.setOnAction(e -> {
                if(!(mat.isSelected() || inf.isSelected() || arvTeh.isSelected())){
                    Alert valeValik = new Alert(Alert.AlertType.ERROR);
                    valeValik.setTitle("Viga");
                    valeValik.setHeaderText("Ei valitud midagi");
                    valeValik.setContentText("Palun vali uuesti");
                    valeValik.showAndWait();
                }else {
                    if (mat.isSelected()) {
                        matT.add(1);
                    } else if (inf.isSelected()) {
                        infT.add(1);
                    } else if (arvTeh.isSelected()) {
                        arvTehT.add(1);
                    }

                    loendur++;
                    küsimus(stage);
                }
            });
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(selgitus, label, mat, inf, arvTeh, valik);

            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
            stage.show();
        }
        else if(loendur != loetud.size()) {
            String[] osad = loetud.get(loendur).split(" - ");
            Label label = new Label(osad[0]);
            RadioButton mat = new RadioButton(osad[1]);
            RadioButton inf = new RadioButton(osad[2]);
            RadioButton arvTeh = new RadioButton(osad[3]);

            Button valik = new Button("Vali");
            valik.setOnAction(e -> {
                if(!(mat.isSelected() || inf.isSelected() || arvTeh.isSelected())){
                    Alert valeValik = new Alert(Alert.AlertType.ERROR);
                    valeValik.setTitle("Viga");
                    valeValik.setHeaderText("Ei valitud midagi");
                    valeValik.setContentText("Palun vali uuesti");
                    valeValik.showAndWait();
                }else {
                    if (mat.isSelected()) {
                        matT.add(1);
                    } else if (inf.isSelected()) {
                        infT.add(1);
                    } else if (arvTeh.isSelected()) {
                        arvTehT.add(1);
                    }

                    loendur++;
                    küsimus(stage);
                }
            });
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(label, mat, inf, arvTeh, valik);

            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
            stage.show();
        }
        else if (loendur == loetud.size()){
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F2F2DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));

            //Võrdleme, millisele erialale sobib kõige paremini
            if(matT.size() > arvTehT.size() && matT.size()>infT.size()){
                Text tulemus = new Text("Sobid matemaatikasse");
                Text protsent = new Text(protsendid(matT, loetud) + "% vastuseid sobitavad sind kõige paremini matemaatikasse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if (infT.size() == arvTehT.size() && arvTehT.size() == matT.size()) {
                Text tulemus = new Text("Sobid igale erialale võrdselt!");
                Text protsent = new Text(protsendid(infT, loetud) + "% vastuseid sobitavad sind kõige paremini informaatikasse.");
                Text protsent2 = new Text(protsendid(arvTehT, loetud) + "% vastuseid sobitavad sind kõige paremini arvutitehnikasse.");
                Text protsent3 = new Text(protsendid(matT, loetud) + "% vastuseid sobitavad sind kõige paremini matemaatikasse.");
                layout.getChildren().addAll(tulemus, protsent, protsent2, protsent3);
            }
            else if(arvTehT.size() > matT.size() && arvTehT.size() > infT.size()){
                Text tulemus = new Text("Sobid arvutitehnikuks");
                Text protsent = new Text(protsendid(arvTehT, loetud) + "% vastuseid sobitavad sind kõige paremini arvutitehnikasse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if(infT.size() > arvTehT.size() && infT.size() >matT.size()){
                Text tulemus = new Text("Sobid informaatikasse");
                Text protsent = new Text(protsendid(infT, loetud) + "% vastuseid sobitavad sind kõige paremini informaatikasse.");
                layout.getChildren().addAll(tulemus, protsent);

            }
            else if(matT.size() == arvTehT.size()){
                Text tulemus = new Text("Sobid nii arvutitehnikuks kui matemaatikasse");
                Text protsent = new Text(protsendid(arvTehT, loetud) + "% vastuseid sobitavad sind kõige paremini arvutitehnikasse.");
                Text protsent2 = new Text(protsendid(matT, loetud) + "% vastuseid sobitavad sind kõige paremini matemaatikasse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);

            }
            else if(matT.size() == infT.size()){
                Text tulemus = new Text("Sobid nii informaatikasse kui matemaatikasse");
                Text protsent = new Text(protsendid(infT, loetud) + "% vastuseid sobitavad sind kõige paremini informaatikasse.");
                Text protsent2 = new Text(protsendid(matT, loetud) + "% vastuseid sobitavad sind kõige paremini matemaatikasse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);
            }
            else if(infT.size() == arvTehT.size()){
                Text tulemus = new Text("Sobid nii informaatikasse kui arvutitehnikasse");
                Text protsent = new Text(protsendid(infT, loetud) + "% vastuseid sobitavad sind kõige paremini informaatikasse.");
                Text protsent2 = new Text(protsendid(arvTehT, loetud) + "% vastuseid sobitavad sind kõige paremini arvutitehnikasse.");

                layout.getChildren().addAll(tulemus, protsent, protsent2);

            }


            //loome sektordiagrammi, kus tehakse võrdlus kõigi erialade vahel
            ObservableList<PieChart.Data> sektordiagrammiSisu = FXCollections.observableArrayList(
                    new PieChart.Data("Informaatika", infT.size()),
                    new PieChart.Data("Arvutitehnika", arvTehT.size()),
                    new PieChart.Data("Matemaatika", matT.size())
            );

            PieChart sektordiagramm = new PieChart(sektordiagrammiSisu);
            layout.getChildren().add(sektordiagramm);

            Scene stseen = new Scene(layout, 625, 525);
            stage.setScene(stseen);
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tulemused.txt", true), "UTF-8"))) {
                bw.write("Informaatika - " + protsendid(infT, loetud));
                bw.newLine();
                bw.write("Matemaatika - " + protsendid(matT, loetud));
                bw.newLine();
                bw.write("Arvutitehnika - " + protsendid(arvTehT, loetud));
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
