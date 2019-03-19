package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TegelRij {

    private ArrayList<Tegel> tegels = new ArrayList<>();
    int resultaat;

    public void setResultaat(int resultaat) {
        this.resultaat = resultaat;
    }

    public Tegel getTegel() {
        int index = resultaat - 21;
        Tegel tegel = tegels.get(index);
        return tegel;
    }

    public void vulTegelRij() {
        for (int i = 21; i <= 36; i++) {
            Tegel tegel = new Tegel(i);
            tegels.add(tegel);
        }

    }

    public boolean controle(Tegel tegel) {
        boolean controle = false;
        do {
            int index = tegels.indexOf(tegel);

            if (tegels.contains(tegel) && tegel.getNummer() != 0) {
                controle = true;
                break;
            } else if (index < 0) {
                controle = false;
                break;
            } else {
                tegel = tegels.get(index - 1);
            }
        } while (tegel.getNummer() > 21);
        return controle;
    }

    public void legTegelTerug(Tegel tegel) { // Verwijder tegel????
        if (tegels.contains(Collections.max(tegels, Comparator.comparing(t -> t.getNummer())))) {
            int index = tegels.indexOf(Collections.max(tegels, Comparator.comparing(t -> t.getNummer())));
            tegels.set(index, tegel);
        } else {
            int index = tegels.indexOf(Collections.max(tegels, Comparator.comparing(t -> t.getNummer())));
            tegels.set(index, tegel);
            tegel.setNummer(-1);
        }
    }

    public void neemTegel(Tegel tegel) {
        do {
            int index = tegels.indexOf(tegel);

            if (tegel.getNummer() == 0 || tegel.getNummer() == -1) {
                tegel = tegels.get(index - 1);
            } else{
                tegels.set(index, tegel);
                tegel.setNummer(0);
            }
        } while (tegel.getNummer() > 21);
    }
}
// IS EINDE SPEL MOET HIER TOEGEVOEGD WORDEN