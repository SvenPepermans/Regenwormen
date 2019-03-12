package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Speler {

    private boolean eindeRonde;
    private int aantalDobbelstenen;
    private int resultaat;
    private String getal;
    private String waarde;
    private String spelerNaam;
    private ArrayList<String> dobbelsteenWaarden = new ArrayList<>();
    private ArrayList<String> gekozenWaarden = new ArrayList<>();
    private boolean laatsteKeuze = false;
    private ArrayList<Tegel> tegels = new ArrayList<>();
    
    public Speler(String spelerNaam) {
        this.spelerNaam = spelerNaam;
    }

    public int getResultaat() {
        return resultaat;
    }

    public void setResultaat(int resultaat) {
        this.resultaat = resultaat;
    }

    public String getSpelerNaam() {
        return spelerNaam;
    }

    public void setSpelerNaam(String spelerNaam) {
        this.spelerNaam = spelerNaam;
    }

    public boolean isLaatsteKeuze() {
        return laatsteKeuze;
    }

    public void setAantalDobbelstenen(int aantal) {
        this.aantalDobbelstenen = aantal;
    }

    public int getAantalDobbelstenen() {
        return aantalDobbelstenen;
    }

    public void setWaarde(String waarde) {
        this.waarde = waarde;
    }

    public String getWaarde() {
        return waarde;
    }

    public ArrayList<String> getDobbelsteenWaarden() {
        return dobbelsteenWaarden;
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

    public boolean isEindeRonde() {
        return eindeRonde;
    }

    public void setEindeRonde(boolean eindeRonde) {
        this.eindeRonde = eindeRonde;
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

    public void voegToe() {
        dobbelsteenWaarden.add(waarde);
    }

    public int berekenResultaat() {
        resultaat = 0;
        for (int teller = 0; teller < gekozenWaarden.size(); teller++) {
            switch (gekozenWaarden.get(teller)) {
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

    public boolean controlerenOfJeNogVerderKan() {
        int aantalGelijk = 0;
        boolean controle = true;

        for (int index = 0; index < dobbelsteenWaarden.size(); index++) {
            String gelijkeWaarde = dobbelsteenWaarden.get(index);
            boolean gelijk = gekozenWaarden.contains(gelijkeWaarde);
            if (gelijk == true) {
                aantalGelijk++;
            } else {
                aantalGelijk = 0;
                break;
            }
        }
        if (dobbelsteenWaarden.isEmpty()) {
            if (resultaat < 21 || !gekozenWaarden.contains("Worm")) {
                controle = false;

            } else {
                laatsteKeuze = true;
            }

        } else if (aantalGelijk == dobbelsteenWaarden.size()) {
            controle = false;

        } else {
            resultaat = 0;

        }

        return controle;
    }

    public boolean WilJeVerderSpelen(String antwoord) {
        do {
            if ("J".equals(antwoord)) {
            } else if ("N".equals(antwoord)) {
                setEindeRonde(true);
                return true;

            }
        } while (!"J".equals(antwoord) && !"N".equals(antwoord));

        return false;
    }
}
