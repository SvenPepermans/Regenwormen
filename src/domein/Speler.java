package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Speler {

    private String spelerNaam;
    private ArrayList<String> dobbelsteenWaarden = new ArrayList<>();
    private ArrayList<String> gekozenWaarden = new ArrayList<>();
    private ArrayList<Tegel> bijgehoudenTegels = new ArrayList<>();
    private ArrayList<Integer> tegelNummers = new ArrayList<>();

    private String getal;
    private int aantalDobbelstenen;
    private String waarde;
    private int eindResultaat;

    public Speler(String spelerNaam) {
        this.spelerNaam = spelerNaam;
    }

    public Speler() {

    }

    public String getSpelerNaam() {
        return spelerNaam;
    }

    public void setSpelerNaam(String spelerNaam) {
        this.spelerNaam = spelerNaam;
    }

    public ArrayList<String> getDobbelsteenWaarden() {
        return dobbelsteenWaarden;
    }

    public void setDobbelsteenWaarden(ArrayList<String> dobbelsteenWaarden) {
        this.dobbelsteenWaarden = dobbelsteenWaarden;
    }

    public ArrayList<String> getGekozenWaarden() {
        return gekozenWaarden;
    }

    public String getGetal() {
        return getal;
    }

    public void setGetal(String getal) {
        this.getal = getal;
    }

    public void setAantalDobbelstenen(int aantal) {
        this.aantalDobbelstenen = aantal;
    }

    public int getAantalDobbelstenen() {
        return aantalDobbelstenen;
    }

    public boolean voegKeuzeToe() {
        Scanner input = new Scanner(System.in);

        do {
            setGetal(input.next());
        } while (gekozenWaarden.contains(getal) || !dobbelsteenWaarden.contains(getal));
        int minAantal = Collections.frequency(dobbelsteenWaarden, getal);
        dobbelsteenWaarden.clear();
        setAantalDobbelstenen(getAantalDobbelstenen() - minAantal);

        for (int aantal3 = 0; aantal3 < minAantal; aantal3++) {
            gekozenWaarden.add(getal);
        }
        return true;

    }

    public void voegDobbelsteenWaardenToe() {
        dobbelsteenWaarden.add(waarde);
    }

    public void setWaarde(String waarde) {
        this.waarde = waarde;
    }

    public String getWaarde() {
        return waarde;
    }

    public void voegTegelToe(Tegel tegel) {
        bijgehoudenTegels.add(tegel);
    }

    public void verwijderTegel() {
        if (bijgehoudenTegels.isEmpty()) {

        } else {
            bijgehoudenTegels.remove(bijgehoudenTegels.size() - 1);
            tegelNummers.remove(tegelNummers.size()-1);
        }
    }

    public Tegel bovensteTegel() {
        Tegel tegel;
        if (bijgehoudenTegels.isEmpty()) {
            return null;
        } else {
            tegel = bijgehoudenTegels.get(bijgehoudenTegels.size() - 1);
        }
        return tegel;
    }

    public ArrayList<Integer> geefBijgehoudenTegels(Tegel tegel) {
        if (bijgehoudenTegels.isEmpty()) {

        } else {
            //int i = tegelNummers.size();
            //Tegel tegel = bijgehoudenTegels.get(i);
            // int tegelNummer = tegel.nummer;
            if (tegel == null) {

            } else {
                tegelNummers.add(tegel.getNummer());
            }
        }
        return tegelNummers;
    }

    public int bijgehoudenTegelsLengte() {
        int lengte = bijgehoudenTegels.size();
        return lengte;
    }
    
    public int berekenEindResultaat(){
        
        for(int index = 0;index<bijgehoudenTegels.size()-1;index++){
            eindResultaat = eindResultaat + bijgehoudenTegels.get(index).waarde;
        }
        return eindResultaat;
    }
    public int hoogsteTegelNummer(){
      int tegelNummer = Collections.max(tegelNummers);
      return tegelNummer;
    }
}
