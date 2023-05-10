package com.example.projektiv;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;

public class Esileht extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        InputStream is = new FileInputStream("tartuulikool.jpeg");
        Image pilt = new Image(is);
        ImageView iw = new ImageView();
        iw.setImage(pilt);
        iw.setFitHeight(500);
        iw.setFitWidth(567);
        iw.setPreserveRatio(true);

        Label pealeht = new Label("Pole kindel, millist eriala õppida ning tahaksid, et keegi teeks sinu valimise kergemaks?");
        Label pealeht2 = new Label("Selleks oleme loonud programmi, mis aitab sul otsustada.");
        Label pealeht3 = new Label("Vajutades nupule 'Jätka' saad valida kahe suuna vahel.");
        Button jätka = new Button("Jätka");
        VBox layout0 = new VBox(20);
        layout0.setStyle("-fx-background-color: #F2F2DC;");
        layout0.setPadding(new Insets(30, 30, 30, 30));

        layout0.getChildren().addAll(iw, pealeht, pealeht2, pealeht3, jätka);

        Scene stseen0 = new Scene(layout0, 625, 525);

        jätka.setOnAction(e -> {
            // Nime sisestamiseks kast
            Label labelNimi = new Label("Sisesta oma nimi");
            TextField nimeTekst = new TextField();

            //Valik, mis küsimustikule soovib vastata
            Label label1 = new Label("Millise suuna küsimustikku sooviksid täita, valikus on: reaalsuund(R) ja sotsiaalsuund(S)");
            TextField vastus = new TextField();

            Button valinupp = new Button("Vali");
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F0F0DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(labelNimi, nimeTekst, label1, vastus, valinupp);

            Scene stseen1 = new Scene(layout, 625, 525);


            valinupp.setOnAction(k -> {
                String valik = vastus.getText();
                    if (valik.equals("R")) {
                        ReaalKüsimused reaalKüsimused = new ReaalKüsimused();
                        try {
                            reaalKüsimused.start(stage);

                            //Lisab "vali" nupu vajutamisel testi tegija nime tulemuste faili
                            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tulemused.txt", true), "UTF-8"))) {
                                bw.write(nimeTekst.getText());
                                bw.newLine();
                            } catch (FileNotFoundException m) {
                                throw new RuntimeException(m);
                            } catch (UnsupportedEncodingException m) {
                                throw new RuntimeException(m);
                            } catch (IOException m) {
                                throw new RuntimeException(m);
                            }

                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (valik.equals("S")) {
                        SotsiaalKüsimused sotsiaalKüsimused = new SotsiaalKüsimused();
                        try {
                            sotsiaalKüsimused.start(stage);

                            //Lisab "vali" nupu vajutamisel testi tegija nime tulemuste faili
                            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tulemused.txt", true), "UTF-8"))) {
                                bw.write(nimeTekst.getText());
                                bw.newLine();
                            } catch (FileNotFoundException m) {
                                throw new RuntimeException(m);
                            } catch (UnsupportedEncodingException m) {
                                throw new RuntimeException(m);
                            } catch (IOException m) {
                                throw new RuntimeException(m);
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }else {
                        Alert valeValik = new Alert(Alert.AlertType.ERROR);
                        valeValik.setTitle("Viga");
                        valeValik.setHeaderText("Vale sisestus");
                        valeValik.setContentText("Palun sisesta valik uuesti.");
                        valeValik.showAndWait();
                    }
            });
            stage.setScene(stseen1);
            stage.show();
        });
        stage.setScene(stseen0);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
