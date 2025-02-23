package domein;

import java.util.ArrayList;

public class TegelRij {

    private ArrayList<Tegel> tegels = new ArrayList<>();
    int spelerIndex = 0;
    int ronde = 0;

    public void setTegels(ArrayList<Tegel> tegels) {
        this.tegels = tegels;
    }

    public int getSpelerIndex() {
        return spelerIndex;
    }

    public void setSpelerIndex(int spelerIndex) {
        this.spelerIndex = spelerIndex;
    }

    public int getRonde() {
        return ronde;
    }

    public void setRonde(int ronde) {
        this.ronde = ronde;
    }

    public Tegel geefHoogsteTegel() {
        Tegel hoogsteTegel = new Tegel(0);
        for (int index = 0; index < tegels.size(); index++) {
            if (tegels.get(index).getNummer() > hoogsteTegel.getNummer()) {
                hoogsteTegel = tegels.get(index);
            }
        }

        return hoogsteTegel;
    }

    public ArrayList<Tegel> getTegels() {
        return tegels;
    }
    
    public int getNummerIndex(int teller){
      return  tegels.get(teller).getNummer();
        
    }

    public void vulTegelRij() {
        for (int i = 21; i <= 36; i++) {
            Tegel tegel = new Tegel(i);
            tegels.add(tegel);
        }

    }
    public void vulTegelRij(ArrayList<String> tegelArrayList)
    {
        for(int i = 0; i < tegelArrayList.size(); i++)
                {
                    Tegel tegel = new Tegel(Integer.valueOf(tegelArrayList.get(i)));
                    tegels.add(tegel);
                }
    }

    public boolean controle(Tegel tegel) {
        boolean controle = false;
        int index = tegels.indexOf(tegel);

        do {

            if (tegels.contains(tegel)) {
                controle = true;
                break;
            } else if (index < 0) {
                controle = false;
                break;
            } else {
                index = index - 1;
                tegel = tegels.get(index);
            }
        } while (tegel.getNummer() == 0);
        return controle;
    }

    public void legTegelTerug(Speler speler, Tegel tegel) { // Verwijder tegel????
        if (tegel == null) {
            int index = tegels.indexOf(geefHoogsteTegel());
            Tegel omgedraaideTegel = new Tegel(-1);
            tegels.set(index, omgedraaideTegel);
        } else if (geefHoogsteTegel() == tegel) {
            int index = tegels.indexOf(geefHoogsteTegel());
            tegels.set(index, tegel);
        } else {
            int maxIndex = tegels.indexOf(geefHoogsteTegel());
            int tegelIndex = tegel.getNummer() - 21;
            Tegel omgedraaideTegel = new Tegel(-1);
            tegels.set(maxIndex, omgedraaideTegel);
            tegels.set(tegelIndex, tegel);
        }
    }

    public Tegel neemTegel(Tegel tegel, ArrayList<Speler> spelers) {
        do {
            int index = tegels.indexOf(tegel);
            if (index < 0) {
                tegel = null;
                break;
            } else if (tegel.getNummer() == -1 || tegel.getNummer() == 0) {
                if (index == 0) {
                    tegel = null;
                    break;
                } else {
                    tegel = tegels.get(index - 1);
                }
            } else {
                Tegel legeTegel = new Tegel(0);
                tegel = tegels.get(index);
                tegels.set(index, legeTegel);
                break;
            }
        } while (tegel.getNummer() >= 21 || tegel.getNummer() == 0 || tegel.getNummer() == -1);
        return tegel;
    }

    public Tegel getTegel(int resultaat) {

            int index = resultaat - 21;
        Tegel tegel = tegels.get(index);
        return tegel;
    }

    public boolean isEindeSpel() {
        boolean eindeSpel = false;
        for (int index = 0; index < tegels.size(); index++) {
            if (tegels.get(index).getNummer() >= 21) {
                eindeSpel = false;
                break;
            } else {
                eindeSpel = true;
            }
        }
        return eindeSpel;
    }

    public boolean kanTegelStelen(Tegel tegel, ArrayList<Speler> spelers, int resultaat, Speler speler) {
        boolean steel = false;
        Tegel controleTegel = new Tegel(resultaat);
        for (int i = 0; i < spelers.size(); i++) {
            Speler target = spelers.get(i);
            Tegel targetTegel = target.bovensteTegel();
            if (targetTegel == null) {
                steel = false;
            } else if (targetTegel.getNummer() == controleTegel.getNummer()) {
                target.verwijderTegel();
                speler.voegTegelToe(controleTegel);
                speler.voegTegelNummerToe(controleTegel.getNummer());
                steel = true;
                break;
            } else {
                steel = false;
            }

        }
        return steel;

    }
    
    public boolean kanTegelStelenGUI(Tegel tegel, ArrayList<Speler> spelers, int resultaat, Speler speler) {
        boolean steel = false;
        Tegel controleTegel = new Tegel(resultaat);
        for (int i = 0; i < spelers.size(); i++) {
            Speler target = spelers.get(i);
            Tegel targetTegel = target.bovensteTegel();
            if (targetTegel == null) {
                steel = false;
            } else if (targetTegel.getNummer() == controleTegel.getNummer()) {
                target.verwijderTegelGUI();
                speler.voegTegelToe(controleTegel);
                steel = true;
                break;
            } else {
                steel = false;
            }

        }
        return steel;

    }


}
