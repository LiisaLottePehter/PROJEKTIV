package com.example.projektiv;
import java.util.ArrayList;

public class Informaatika extends Küsimused2{
    public Informaatika(ArrayList<String> küsimused) {
        super(küsimused);
    }

    //vaatab kas vastus sobib vastavalt erialale
    @Override
    public ArrayList<String> listid(ArrayList<Integer> vastused) {
        ArrayList<String> informaatika = new ArrayList<>();
        int vastuseidkokku = 0;
        if(vastused.get(vastuseidkokku) == 2)
            informaatika.add("2");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 3)
            informaatika.add("3");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 2)
            informaatika.add("2");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 2)
            informaatika.add("2");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 1)
            informaatika.add("1");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 3)
            informaatika.add("3");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 2)
            informaatika.add("2");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 2)
            informaatika.add("2");
        return informaatika;
    }

    @Override
    public String protsendid(ArrayList<String> üksList, ArrayList<String> teineList) {
        return super.protsendid(üksList, teineList) + "% vastuseid sobitavad sind kõige paremini informaatikasse.";
    }
}

