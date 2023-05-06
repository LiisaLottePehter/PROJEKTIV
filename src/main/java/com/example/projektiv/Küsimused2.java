package com.example.projektiv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Küsimused2 {
    private ArrayList<String> küsimused;
    public Küsimused2(ArrayList<String> küsimused) {
        this.küsimused = küsimused;
    }

    //meetod, mis igal eriala klassil loeb punkte erinevalt
    public  ArrayList<String> listid(ArrayList<Integer> vastused){
        return null;
    }

    public String protsendid(ArrayList<String> üksList, ArrayList<String> teineList){
        int protsent = üksList.size() * 100/ teineList.size();
        String protsentString = Integer.toString(protsent);
        return protsentString;
    }

    //küsimuste printimine ja lisab vastuste listi, mida kasutame eriala klassides
    public ArrayList<Integer> teeTest(ArrayList<String> küsimused) {
        ArrayList<Integer> vastused= new ArrayList<>();
        int tulemus = 0;
        Scanner keyboardInput = new Scanner(System.in);

        //Läbib küsimuste listi ükshaaval
        for (int i = 0; i < küsimused.size(); i++) {
            //Prindib küsimuse
            System.out.println(küsimused.get(i));
            //Määrab vastuse inputiks, mida sisestatakse. Lisab tulemusele sisestuse kui on õiges vahemikus.
            int vastus = Integer.parseInt(keyboardInput.nextLine());
            if (vastus >= 1 && vastus <= 3) {
                vastused.add(vastus);
                tulemus += vastus;
                //Kui ei ole õiges vahemikus siis läheb selle küsimuse juurde tagasi
            }
            else {
                System.out.println("Sisesta õige number palun");
                i--;
            }
        }
        return vastused;
    }

}

