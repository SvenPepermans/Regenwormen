package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class DomeinController {

    private boolean eindeRonde;
    private boolean laatsteKeuze = false;
    private int resultaat;
    Speler speler;
    Tegel tegel;
    Tegel bovensteTegel;

    TegelRij tegelrij = new TegelRij();
    ArrayList<Speler> spelers = new ArrayList<>();
    ArrayList<Speler> winnaars = new ArrayList<>();

    public DomeinController() {

    }

    public void setTegel(Tegel tegel) {
        this.tegel = tegel;
    }

    public Tegel getTegel() {
        return tegel;
    }

    public Tegel getBovensteTegel() {
        return bovensteTegel;
    }

    public void setBovensteTegel(Tegel bovensteTegel) {
        this.bovensteTegel = bovensteTegel;
    }

    public void veranderVanSpeler(int index) {
        speler = spelers.get(index);
    }

    public void leegGekozenWaardenSpeler() {
        speler.clearGekozenWaarden();
    }

    public ArrayList<Speler> getSpelers() {
        return spelers;
    }

    public void setSpelers(ArrayList<Speler> spelers) {
        this.spelers = spelers;
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

    public boolean wilJeVerderSpelen(String antwoord) {
        if ('J' == (antwoord.charAt(0))) {
        } else if ('N' == (antwoord.charAt(0))) {
            setEindeRonde(true);
            return true;
        }

        return false;
    }

    public void zetWaarde(String waarde) {
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

    public ArrayList<String> geefDobbelsteenWaarden() {
        return speler.getDobbelsteenWaarden();
    }

    public void zetAantalDobbelstenen(int aantal) {
        speler.setAantalDobbelstenen(aantal);
    }

    public int geefAantalDobbelstenen() {
        return speler.getAantalDobbelstenen();
    }

    public boolean addChoice() {
        return speler.voegKeuzeToe();
    }

    public void leegDobbelsteenWaardenSpeler() {
        speler.leegDobbelsteenWaardenSpeler();
    }

    public ArrayList<String> geefGekozenWaarden() {
        return speler.getGekozenWaarden();
    }

    public Tegel geefTegel(int resultaat) {
        return tegelrij.getTegel(resultaat);
    }

    public void legTegelTerug(Tegel tegel) {
        if (speler.bijgehoudenTegelsLengte() == 0) {
            tegel = null;
        }
        tegelrij.legTegelTerug(speler, tegel);
    }

    public void verwijderTegel() {
        speler.verwijderTegel();
    }

    public Tegel neemTegel() {
        return tegelrij.neemTegel(tegel, spelers);
    }

    public boolean kanTegelStelen(Tegel tegel) {
        return tegelrij.kanTegelStelen(tegel, spelers, resultaat, speler);
    }

    public void voegTegelToe() {
        speler.voegTegelToe(tegel);
    }

    public boolean controle(Tegel tegel) {
        return tegelrij.controle(tegel);
    }

    public void vulTegelRij() {
        tegelrij.vulTegelRij();
    }

    public String geefSpelerNaam() {
        return speler.getSpelerNaam();
    }

    public ArrayList<Integer> geefBijgehoudenTegels() {
        return speler.geefBijgehoudenTegels(tegel);
    }

    public Tegel geefBovensteTegel() {
        Tegel tegel = speler.bovensteTegel();
        return tegel;
    }

    public boolean isEindeSpel() {
        boolean eindeSpel = tegelrij.isEindeSpel();
        return eindeSpel;
    }

    public int berekenEindResultaat() {
        int eindResultaat = speler.berekenEindResultaat();
        return eindResultaat;
    }

    public void geefDetails(String naam, Date geboorteDatum, int aantal) {
        spelers.get(aantal).setSpelerNaam(naam);
        spelers.get(aantal).setGeboorteDatum(geboorteDatum);
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        speler.setGeboorteDatum(geboorteDatum);
    }

    public Speler getspeler(int persoon) {
        return spelers.get(persoon);
    }

    public void aanmakendobbelsteen() {
        Dobbelsteen dobbelsteen = new Dobbelsteen();
        zetWaarde(dobbelsteen.rolDobbelsteen());
        voegDWToe();
    }

    public String bepaalWinnaar() {
        int score = 0;
        int hoogsteScore = 0;
        String winnaar = null;

        for (int i = 0; i < spelers.size(); i++) {
            Speler speler = spelers.get(i);
            score = speler.berekenEindResultaat();
            if (score == hoogsteScore) {
                winnaars.add(speler);
            } else if (score > hoogsteScore) {
                winnaars.clear();
                hoogsteScore = score;
                winnaar = spelers.get(i).getSpelerNaam();

            }
            if (!winnaars.isEmpty()) {
                int hoogsteTegelNummer = 0;
                for (int index = 0; index < winnaars.size(); index++) {
                    speler = winnaars.get(i);
                    int tegelNummer = speler.hoogsteTegelNummer();

                    if (tegelNummer > hoogsteTegelNummer) {
                        hoogsteTegelNummer = tegelNummer;
                        winnaar = winnaars.get(i).getSpelerNaam();
                    }
                }
            }
        }
        return winnaar;

    }

    public void voegAantalSpelersToe(int aantal) {
        for (int counter = 1; counter <= aantal; counter++) {
            Speler speler = new Speler();
            spelers.add(speler);
        }
    }

    public void sorteerJongNaarOud() {
        for (int i = 1; i < spelers.size(); i++) {
            Date date1 = spelers.get(i).geboorteDatum;
            Date date2 = spelers.get(i - 1).geboorteDatum;
            int compare = date2.compareTo(date1);
            int j = i;
            Speler speler1;
            do {
                speler1 = spelers.get(j);

                if (compare < 0) {
                    spelers.set(j, spelers.get(j - 1));
                    spelers.set(j - 1, speler1);

                }
                j = j - 1;

            } while (j > 0);
        }
    }
    
      public void addSpeler(String naam, LocalDate geboorteDatum) {
     
        Speler speler = new Speler(naam, geboorteDatum);
        spelers.add(speler);
        
    }
}
