package ui;

import domein.Dobbelsteen;
import domein.Speler;
import java.util.Scanner;

public class RegenwormenApplicatie {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean eindeSpel = false;
        boolean eindeRonde = false;
        boolean keuze = false;
        boolean beslissing;
        boolean isLeeg;
        String naam, antwoord;

        String getal;
        //Aantal Spelers
        System.out.println("Met hoeveel spelers wil je spelen?");
        int aantalSpelers = input.nextInt();
        for (int counter = 1; counter <= aantalSpelers; counter++) {
            eindeRonde = false;
            Speler speler = new Speler();
            System.out.println("Geef je naam in:");
            naam = input.next();
            speler.setSpelerNaam(naam);
            speler.voegNaamToe();

            speler.setAantalDobbelstenen(8);
            do {

                //Gooien
                for (int aantalIG = 0; aantalIG < speler.getAantalDobbelstenen(); aantalIG++) {
                    Dobbelsteen dobbelsteen = new Dobbelsteen();
                    speler.setWaarde(dobbelsteen.rolDobbelsteen());
                    speler.voegToe();
                }

                System.out.println(speler.getDobbelsteenWaarden());
                //Controle of je verder kan
                if ((speler.getDobbelsteenWaarden().isEmpty()))
                {
                    isLeeg = true;
                }else{
                    isLeeg = false;
                }
                if (speler.controlerenOfJeNogVerderKan() == false) {
                    System.out.printf("Jammer, je beurt was niet succesvol. Je verliest een tegel. %n");
                    speler.setEindeRonde(true);
                } else if (speler.isLaatsteKeuze() == true) {
                    System.out.printf("Kies een geldige tegel, %s%n", (counter != aantalSpelers) ? "daarna is het de beurt aan de volgende speler" : "de ronde is gedaan.");
                    speler.setEindeRonde(true);
                } else {
                    //Keuze maken                     
                    do {
                        System.out.println("Welk getal wil je bij je houden?");
                        keuze = speler.voegKeuzeToe();
                    } while (keuze == false);
                    System.out.println(speler.getGekozenWaarden());
                    System.out.printf("Je tijdelijk resultaat is: %d%n", speler.berekenResultaat());
                    if(isLeeg == false){  
                        if (speler.getResultaat() >= 21 && speler.getGekozenWaarden().contains("Worm")) {
                            System.out.println("Wil je nog verder spelen? J/N ");
                            antwoord = input.next().toUpperCase();
                            beslissing = speler.WilJeVerderSpelen(antwoord);
                            if (beslissing == true) {
                                System.out.println("Je beëindigt je beurt met een score van " + speler.getResultaat() + " en kan een tegel nemen.");
                            }
                        }
                    } else {
                        System.out.println("Je beëindigt je beurt met een score van " + speler.getResultaat() + "en kan een tegel nemen.");
                        speler.setEindeRonde(true);
                    }

                }

                eindeRonde = speler.isEindeRonde();
            } while (eindeRonde == false);
        }
    }
}
