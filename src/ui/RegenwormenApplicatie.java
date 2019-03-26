package ui;

import domein.Dobbelsteen;
import domein.DomeinController;
import domein.Speler;
import domein.Tegel;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class RegenwormenApplicatie {

    public void start() {

        Scanner input = new Scanner(System.in);
        int index = 0;
        int ronde = 1;
        boolean eindeSpel = false;
        boolean eindeRonde = false;
        boolean keuze = false;
        boolean beslissing;
        boolean isLeeg;
        String naam, antwoord;
        String getal;
        ArrayList<Speler> spelers = new ArrayList<>();
        Tegel tegel = null;

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
        Dc.vulTegelRij();
        System.out.println("We beginnen met ronde 1:");
        do {
            // Speler kiezen
            Speler speler = spelers.get(index);
            DomeinController DC = new DomeinController(speler);
            String spelerNaam = DC.geefSpelerNaam(speler);
            System.out.println("Het is de beurt van " + spelerNaam);
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
                isLeeg = (DC.geefDobbelsteenWaarden().isEmpty());
                if (DC.controlerenOfJeNogVerderKan() == false) {
                    Tegel bovensteTegel = Dc.geefBovensteTegel();
                    System.out.println("Jammer, je beurt was niet succesvol. Je verliest een tegel.");
                    Dc.legTegelTerug(speler, bovensteTegel);
                    DC.verwijderTegel(speler);
                    DC.setEindeRonde(true);

                } else if (DC.isLaatsteKeuze() == true) {
                    System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat() + " en kan een tegel nemen.");
                    DC.setResultaat(DC.getResultaat());
                    tegel = Dc.geefTegel(DC.getResultaat());
                    Dc.neemTegel(tegel);
                    DC.voegTegelToe(tegel, speler);
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
                                System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat());
                                DC.setResultaat(DC.getResultaat()); //Resultaten anders benoemen bij vervormen naar DC.
                                tegel = Dc.geefTegel(DC.getResultaat());
                                if (Dc.controle(tegel) == true) {
                                    tegel = Dc.neemTegel(tegel);
                                    if (tegel == null) {
                                        System.out.println("Er zijn geen tegels meer, je moet een tegel terugleggen");
                                        Tegel bovensteTegel = DC.geefBovensteTegel();
                                        Dc.legTegelTerug(speler, bovensteTegel);
                                        DC.verwijderTegel(speler);
                                    } else {
                                        System.out.println("Er is nog een mogelijke tegel aanwezig.");
                                        DC.voegTegelToe(tegel, speler);
                                    }

                                } else {
                                    Tegel bovensteTegel = DC.geefBovensteTegel();
                                    Dc.legTegelTerug(speler, bovensteTegel);
                                    DC.verwijderTegel(speler);

                                }

                            }
                        }
                    } else {
                        System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat() + "en kan een tegel nemen.");

                        DC.setResultaat(DC.getResultaat());
                        tegel = Dc.geefTegel(DC.getResultaat());
                        Dc.neemTegel(tegel);
                        DC.voegTegelToe(tegel, speler);
                        DC.setEindeRonde(true);

                    }

                }
                eindeRonde = DC.isEindeRonde();
                eindeSpel = Dc.isEindeSpel();
            } while (eindeRonde == false);
            System.out.println(DC.geefBijgehoudenTegels(speler, tegel));
            if (index + 1 < aantalSpelers) {
                index++;
            } else {
                ronde++;
                System.out.println("We beginnen nu met ronde " + ronde + ": ");
                index = 0;
            }
        } while (eindeSpel == false);
        //PUNTENBEREKENING
        int score = 0;
        int hoogsteScore = 0;
        String winnaar = null;
        for(index = 0; index < spelers.size()-1;index++){   
            Speler speler = spelers.get(index);
            score = speler.berekenEindResultaat();
            if(score > hoogsteScore){
                hoogsteScore = score;
                winnaar = spelers.get(index).getSpelerNaam();                
            }
        }
        
        
        System.out.println("Het spel is voorbij! De winnaar is : " + winnaar + " Met een score van: " + hoogsteScore );
    }
}
