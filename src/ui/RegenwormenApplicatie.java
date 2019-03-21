package ui;

import domein.Dobbelsteen;
import domein.DomeinController;
import domein.Speler;
import domein.Tegel;
import domein.TegelRij;
import java.util.Scanner;
import java.util.ArrayList;

public class RegenwormenApplicatie {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int index = 0;
        boolean eindeSpel = false;
        boolean eindeRonde = false;
        boolean keuze = false;
        boolean beslissing;
        boolean isLeeg;
        String naam, antwoord;
        String getal;
        ArrayList<Speler> spelers = new ArrayList<>();

        //Aantal Spelers
        System.out.println("Met hoeveel spelers wil je spelen?");
        int aantalSpelers = input.nextInt();
        for (int counter = 1; counter <= aantalSpelers; counter++) {
            eindeRonde = false;
            System.out.println("Geef je naam in:");
            naam = input.next();
            Speler speler = new Speler(naam);
            spelers.add(speler);
        }

        //tegelRij aanmaken
        DomeinController Dc = new DomeinController();
        TegelRij tegelrij = new TegelRij();
        tegelrij.vulTegelRij();
        do {
            int counter = spelers.size();
            // Speler kiezen
            Speler speler = spelers.get(index);
            String spelerNaam = speler.getSpelerNaam();
            System.out.println("Het is de beurt van " + spelerNaam);
            DomeinController DC = new DomeinController(speler);
            //Dobbelstenen naar 8
            DC.zetAantalDobbelstenen(8);

            do {
                //Gooien
                for (int aantalIG = 0; aantalIG < DC.geefAantalDobbelstenen(); aantalIG++) {
                    Dobbelsteen dobbelsteen = new Dobbelsteen();
                    DC.zetWaarde(dobbelsteen.rolDobbelsteen());
                    DC.voegDWToe();
                }

                System.out.println(DC.geefDobbelsteenWaarden());
                //Controle of je verder kan
                if ((DC.geefDobbelsteenWaarden().isEmpty())) {
                    isLeeg = true;
                } else {
                    isLeeg = false;
                }
                if (DC.controlerenOfJeNogVerderKan() == false) {
                    Tegel tegel = DC.geefTegel();                    
                    System.out.printf("Jammer, je beurt was niet succesvol. Je verliest een tegel. %n");
                    DC.legTegelTerug(speler);
                    DC.verwijderTegel();
                    DC.setEindeRonde(true);

                } else if (DC.isLaatsteKeuze() == true) {
                    System.out.printf("Kies een geldige tegel, %s%n", (counter != aantalSpelers) ? "daarna is het de beurt aan de volgende speler" : "de ronde is gedaan.");
                    DC.berekenResultaat();

                    DC.setResultaat(DC.getResultaat());
                    Tegel tegel = DC.geefTegel();
                    DC.neemTegel(tegel);
                    DC.voegTegelToe(tegel);
                    DC.setEindeRonde(true);
                } else {
                    //Keuze maken                     
                    do {
                        System.out.println("Welk getal wil je bij je houden?");
                        keuze = DC.addChoice();
                    } while (keuze == false);
                    System.out.println(DC.geefGekozenWaarden());
                    System.out.printf("Je tijdelijk resultaat is: %d%n", DC.berekenResultaat());

                    if (isLeeg == false) {
                        if (DC.getResultaat() >= 21 && DC.geefGekozenWaarden().contains("Worm")) {
                            do {
                                System.out.println("Wil je nog verder spelen? J/N ");
                                antwoord = input.next().toUpperCase();
                            } while ('J' != (antwoord.charAt(0)) && 'N' != (antwoord.charAt(0)));
                            beslissing = DC.wilJeVerderSpelen(antwoord);
                            if (beslissing == true) {
                                System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat() + " en kan een tegel nemen.");
                                DC.setResultaat(DC.getResultaat()); //Resultaten anders benoemen bij vervormen naar DC.
                                Tegel tegel = DC.geefTegel();
                                if (DC.controle(tegel) == true) {
                                    tegel = DC.neemTegel(tegel);
                                    DC.voegTegelToe(tegel);
                                    System.out.println(DC.geefBijgehoudenTegels());

                                } else {
                                    DC.legTegelTerug(speler);
                                    DC.verwijderTegel();
                                }

                            }
                        }
                    } else {
                        System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat() + "en kan een tegel nemen.");

                        DC.setResultaat(DC.getResultaat());
                        Tegel tegel = DC.geefTegel();
                        DC.neemTegel(tegel);
                        DC.voegTegelToe(tegel);
                        DC.setEindeRonde(true);
                    }

                }
                eindeRonde = DC.isEindeRonde();
            } while (eindeRonde == false);
            if (index + 1 < aantalSpelers) {
                index++;
            } else {
                index = 0;
            }

        } while (eindeSpel == false);

    }
}
