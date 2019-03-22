package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TegelRij {

    private ArrayList<Tegel> tegels = new ArrayList<>();
    int resultaat;
    Speler speler = new Speler();

    public int getResultaat() {
        return resultaat;
    }

    public void vulTegelRij() {
        for (int i = 21; i <= 36; i++) {
            Tegel tegel = new Tegel(i);
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
            int index = tegels.indexOf(Collections.max(tegels, Comparator.comparing(t -> t.getNummer())));
            Tegel omgedraaideTegel = new Tegel(-1);
            tegels.set(index, omgedraaideTegel);            
        } else if (tegels.contains(Collections.max(tegels, Comparator.comparing(t -> t.getNummer())))) {
            int index = tegels.indexOf(Collections.max(tegels, Comparator.comparing(t -> t.getNummer())));
            tegels.set(index, tegel);
        } else {
            int maxIndex = tegels.indexOf(Collections.max(tegels, Comparator.comparing(t -> t.getNummer())));
            int tegelIndex = tegel.getNummer() - 21 ;
            Tegel omgedraaideTegel = new Tegel(-1);
            tegels.set(maxIndex, omgedraaideTegel);
            tegels.set(tegelIndex, tegel);
        }
    }

    public Tegel neemTegel(Tegel tegel) {
        do {
            int index = tegels.indexOf(tegel);
            if (tegel.getNummer() == 0 || tegel.getNummer() == -1) {
                if(index <= 0){
                    tegel = null;
                    break;
                } else{
                tegel = tegels.get(index - 1);                    
                }
            } else {
                Tegel legeTegel = new Tegel(0);
                tegel = tegels.get(index);
                tegels.set(index, legeTegel);
                break;
            }
        } while (tegel.getNummer() >= 21|| tegel.getNummer() == 0 || tegel.getNummer() == -1);
        return tegel;
    }

    public Tegel getTegel(int resultaat) {

        int index = resultaat - 21;
        Tegel tegel = tegels.get(index);
        return tegel;
    }
    public boolean isEindeSpel(){
        boolean eindeSpel = false;
        for(int index = 0; index < tegels.size();index++)
            if(tegels.get(index).nummer >= 21){
                eindeSpel = false;
                break;
            } else{
                eindeSpel = true;
            }
        return eindeSpel;
    }
}

// IS EINDE SPEL MOET HIER TOEGEVOEGD WORDEN
