package com.example.projektiv;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Esileht extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        InputStream is = new FileInputStream("tartuülikool.jpeg");
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
        layout0.setPadding(new Insets(30, 30, 30, 30));
        layout0.getChildren().addAll(iw, pealeht, pealeht2, pealeht3, jätka);

        Scene stseen0 = new Scene(layout0, 625, 525);

        jätka.setOnAction(e -> {
            //Valik, mis küsimustikule soovib vastata
            Label label1 = new Label("Millise suuna küsimustikku sooviksid täita");
            RadioButton reaal = new RadioButton("Reaalsuund");
            RadioButton loodus = new RadioButton("Loodussuund");
            RadioButton humanitaar = new RadioButton("Humanitaarsuund");

            ToggleGroup grupp1 = new ToggleGroup();
            reaal.setToggleGroup(grupp1);
            loodus.setToggleGroup(grupp1);
            humanitaar.setToggleGroup(grupp1);


            Button valinupp = new Button("Vali");
            VBox layout = new VBox(20);

            layout.setPadding(new Insets(30, 30, 30, 30));
            layout.getChildren().addAll(label1, reaal, loodus, humanitaar, valinupp);

            Scene stseen1 = new Scene(layout, 625, 525);

            valinupp.setOnAction(k -> {
                if (reaal.isSelected()) {
                    ReaalKüsimused reaalKüsimused = new ReaalKüsimused();
                    try {
                        reaalKüsimused.start(stage);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
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
