package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Speler {

    private String spelerNaam;
    public Date geboorteDatum;
    private ArrayList<String> dobbelsteenWaarden = new ArrayList<>();
    private ArrayList<String> gekozenWaarden = new ArrayList<>();
    private ArrayList<Tegel> bijgehoudenTegels = new ArrayList<>();
    private ArrayList<Integer> tegelNummers = new ArrayList<>();
    private int aantalDobbelstenen = 8;
    private String waarde;
    private int eindResultaat;

    public Speler(String spelerNaam, LocalDate geboorteDatum) {
        this.spelerNaam = spelerNaam;
    }

    public Speler() {

    }

    public ArrayList<Integer> getTegelNummers() {
        return tegelNummers;
    }

    private String getal;

    public ArrayList<Tegel> getBijgehoudenTegels() {
        return bijgehoudenTegels;
    }

    public void setGekozenWaarden(ArrayList<String> gekozenWaarden) {
        this.gekozenWaarden = gekozenWaarden;
    }

    public void setBijgehoudenTegels(ArrayList<Tegel> bijgehoudenTegels) {
        this.bijgehoudenTegels = bijgehoudenTegels;
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
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

    public void leegDobbelsteenWaardenSpeler() {
        dobbelsteenWaarden.clear();
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

    public boolean addChoiceGUI(String keuze) {
//      do {
//           setGetal(keuze);
//       } while (gekozenWaarden.contains(getal) || !dobbelsteenWaarden.contains(getal));
        setGetal(keuze);
        if (!gekozenWaarden.contains(getal)) {
            int minAantal = Collections.frequency(dobbelsteenWaarden, getal);
            dobbelsteenWaarden.clear();
            setAantalDobbelstenen(getAantalDobbelstenen() - minAantal);

            for (int aantal3 = 0; aantal3 < minAantal; aantal3++) {
                gekozenWaarden.add(getal);
            }
            return true;
        } else {
            return false;
        }

    }

    public void clearGekozenWaarden() {
        gekozenWaarden.clear();
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
            tegelNummers.remove(tegelNummers.size() - 1);
        }
    }

    public void verwijderTegelGUI() {
        if (bijgehoudenTegels.isEmpty()) {

        } else {
            bijgehoudenTegels.remove(bijgehoudenTegels.size() - 1);
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

            } else if (tegelNummers.contains(tegel.getNummer()) || tegel.getNummer() == 0 || tegel.getNummer() == -1) {

            } else {
                tegelNummers.add(tegel.getNummer());
            }
        }
        return tegelNummers;
    }

    public void voegTegelNummerToe(Tegel tegel) {
        if (bijgehoudenTegels.isEmpty()) {

        } else {

            if (tegel == null) {

            } else if (tegelNummers.contains(tegel.getNummer()) || tegel.getNummer() == 0 || tegel.getNummer() == -1) {

            } else {
                tegelNummers.add(tegel.getNummer());
            }
        }
    }

    public int bijgehoudenTegelsLengte() {
        int lengte = bijgehoudenTegels.size();
        return lengte;
    }

    public int berekenEindResultaat() {

        for (int index = 0; index < bijgehoudenTegels.size(); index++) {
            eindResultaat = eindResultaat + bijgehoudenTegels.get(index).getWaarde();
        }
        return eindResultaat;
    }

    public int hoogsteTegelNummer() {
        int tegelNummer = Collections.max(tegelNummers);
        return tegelNummer;
    }

    public void voegTegelNummerToe(int nummer) {
        tegelNummers.add(nummer);
    }

    public int getNummerIndex(int teller) {
        return bijgehoudenTegels.get(teller).getNummer();

    }
}
