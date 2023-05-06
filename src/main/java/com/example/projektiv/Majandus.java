package com.example.projektiv;
import java.util.ArrayList;

public class Majandus extends Küsimused2{
    public Majandus(ArrayList<String> küsimused) {
        super(küsimused);
    }

    //vaatab kas vastus sobib vastavalt erialale
    @Override
    public ArrayList<String> listid(ArrayList<Integer> vastused) {
        ArrayList<String> majandus = new ArrayList<>();
        int vastuseidkokku = 0;
        if(vastused.get(vastuseidkokku) == 1)
            majandus.add("1");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 1)
            majandus.add("1");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 3)
            majandus.add("3");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 3)
            majandus.add("3");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 1)
            majandus.add("1");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 1)
            majandus.add("1");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 3)
            majandus.add("3");
        vastuseidkokku++;
        if(vastused.get(vastuseidkokku) == 3)
            majandus.add("3");
        return majandus;
    }

    @Override
    public String protsendid(ArrayList<String> üksList, ArrayList<String> teineList) {
        return super.protsendid(üksList, teineList) + "% vastuseid sobitavad sind kõige paremini majandusse.";
    }
}
