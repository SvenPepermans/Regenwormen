package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import domein.Speler;

public class DomeinController
{
     private boolean eindeRonde;
     private boolean laatsteKeuze = false;    
     private int resultaat;     
     Speler speler = new Speler();
    
    public DomeinController(Speler speler){
        
    }
     
    public boolean isEindeRonde() {
        return eindeRonde;
    }

    public void setEindeRonde(boolean eindeRonde) {
        this.eindeRonde = eindeRonde;
    }
     
    public int getResultaat() {
        return resultaat;
    }

    public void setResultaat(int resultaat) {
        this.resultaat = resultaat;
    }
    
    public void voegDWToe() {
        speler.voegDobbelsteenWaardenToe();
    }

    public int berekenResultaat() {
        resultaat = 0;
        for (int teller = 0; teller < speler.getGekozenWaarden().size(); teller++) {
            switch (speler.getGekozenWaarden().get(teller)) {
                case "1":
                    resultaat += 1;
                    break;
                case "2":
                    resultaat += 2;
                    break;
                case "3":
                    resultaat += 3;
                    break;
                case "4":
                    resultaat += 4;
                    break;
                default:
                    resultaat += 5;
            }
        }
        return resultaat;
    }
    
    public boolean WilJeVerderSpelen(String antwoord) {
        if ('J' == (antwoord.charAt(0))) {
        } else if ('N' == (antwoord.charAt(0))) {
            setEindeRonde(true);
            return true;
        }

        return false;
    }

    public void zetWaarde(String waarde){
      speler.setWaarde(waarde);
  }

    public boolean isLaatsteKeuze() {
        return laatsteKeuze;
    }
    
    public boolean controlerenOfJeNogVerderKan() {
        int aantalGelijk = 0;
        boolean controle = true;
      

        for (int index = 0; index < speler.getDobbelsteenWaarden().size(); index++) {
            String gelijkeWaarde = speler.getDobbelsteenWaarden().get(index);
            boolean gelijk = speler.getGekozenWaarden().contains(gelijkeWaarde);
            if (gelijk == true) {
                aantalGelijk++;
            } else {
                aantalGelijk = 0;
                break;
            }
        }
        if (speler.getDobbelsteenWaarden().isEmpty()) {
            if (resultaat < 21 || !speler.getGekozenWaarden().contains("Worm")) {
                controle = false;

            } else {
                laatsteKeuze = true;
            }

        } else if (aantalGelijk == speler.getDobbelsteenWaarden().size()) {
            controle = false;

        } else {
            resultaat = 0;

        }

        return controle;
    }
     
    public ArrayList<String> geefDobbelsteenWaarden(){
      return speler.getDobbelsteenWaarden();
   }
     
    public void zetAantalDobbelstenen(int aantal){
        speler.setAantalDobbelstenen(aantal);
    }
    
    public int geefAantalDobbelstenen(){
        return speler.getAantalDobbelstenen();
    }
    
    public boolean addChoice(){
        return speler.voegKeuzeToe();
    }
    
    public ArrayList<String> geefGekozenWaarden(){
        return speler.getGekozenWaarden();
    }
}

