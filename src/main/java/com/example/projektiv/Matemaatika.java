package com.example.projektiv;
import java.util.ArrayList;

public class Matemaatika extends Küsimused2{

    public Matemaatika(ArrayList<String> küsimused) {
        super(küsimused);
    }

    //vaatab kas vastus sobib vastavalt erialale
    @Override
    public ArrayList<String> listid(ArrayList<Integer> vastused) {
        ArrayList<String> matemaatika = new ArrayList<>();
        int vastuseidkokku = 0;
        if(vastused.get(vastuseidkokku) == 3)
            matemaatika.add("3");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 2)
            matemaatika.add("2");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 1)
            matemaatika.add("1");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 1)
            matemaatika.add("1");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 3)
            matemaatika.add("3");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 2)
            matemaatika.add("2");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 1)
            matemaatika.add("1");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 1)
            matemaatika.add("1");
        return matemaatika;
    }

    @Override
    public String protsendid(ArrayList<String> üksList, ArrayList<String> teineList) {
        return super.protsendid(üksList, teineList) + "% vastuseid sobitavad sind kõige paremini matemaatikasse.";
    }
}