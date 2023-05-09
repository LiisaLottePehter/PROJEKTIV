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

import java.io.FileInputStream;
import java.io.InputStream;

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
        Label pealeht3 = new Label("Vajutades nupule 'Jätka' saad valida kolme suuna vahel.");
        Button jätka = new Button("Jätka");
        VBox layout0 = new VBox(20);
        layout0.setStyle("-fx-background-color: #F2F2DC;");
        layout0.setPadding(new Insets(30, 30, 30, 30));

        layout0.getChildren().addAll(iw, pealeht, pealeht2, pealeht3, jätka);

        Scene stseen0 = new Scene(layout0, 625, 525);

        jätka.setOnAction(e -> {
            //Valik, mis küsimustikule soovib vastata
            Label label1 = new Label("Millise suuna küsimustikku sooviksid täita, valikus on: reaalsuund(R), loodussuund(L), humanitaarsuund(H)");
            TextField vastus = new TextField();

            Button valinupp = new Button("Vali");
            VBox layout = new VBox(20);
            layout.setStyle("-fx-background-color: #F0F0DC;");
            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(label1, vastus, valinupp);

            Scene stseen1 = new Scene(layout, 625, 525);

            valinupp.setOnAction(k -> {
                String valik = vastus.getText();
                    if (valik.equals("R")) {
                        ReaalKüsimused reaalKüsimused = new ReaalKüsimused();
                        try {
                            reaalKüsimused.start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (valik.equals("L")) {
                        LoodusKüsimused loodusKüsimused = new LoodusKüsimused();
                        try {
                            loodusKüsimused.start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (valik.equals("H")) {
                        HumanitaarKüsimused humanitaarKüsimused = new HumanitaarKüsimused();
                        try {
                            humanitaarKüsimused.start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }else {
                        Stage uus = new Stage();
                        VBox veateateaken = new VBox(10);
                        layout.setStyle("-fx-background-color: #F0F0DC;");
                        layout.setPadding(new Insets(30, 30, 30, 30));
                        Text valeValik = new Text("Vale valik, palun valige uuesti.");
                        valeValik.setTextAlignment(TextAlignment.CENTER);
                        veateateaken.getChildren().add(valeValik);
                        Scene veateateStseen = new Scene(veateateaken, 200, 100);
                        uus.setScene(veateateStseen);
                        uus.show();
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
