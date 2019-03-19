package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import domein.DomeinController;

public class Speler {


    private String spelerNaam;
    private ArrayList<String> dobbelsteenWaarden = new ArrayList<>();
    private ArrayList<String> gekozenWaarden = new ArrayList<>();
    private String getal;
    private int aantalDobbelstenen;
    private String waarde;
   
  
    
    public Speler(String spelerNaam) {
        this.spelerNaam = spelerNaam;
    }
    
    public Speler(){
        
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

     public void voegDobbelsteenWaardenToe(){          
         dobbelsteenWaarden.add(waarde);
     }
     
       public void setWaarde(String waarde) {
        this.waarde = waarde;
    }

    public String getWaarde() {
        return waarde;
    }

}
   
