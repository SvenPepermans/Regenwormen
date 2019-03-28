package ui;

import domein.DomeinController;
import domein.Tegel;
//import domein.Tegel;
//import domein.Tegel;
import java.util.Scanner;
/*
import domein.Speler;*/
//import domein.Dobbelsteen;
//import domein.Tegel;


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
        Tegel tegel = null;
        

        //Aantal Spelers
        System.out.println("Met hoeveel spelers wil je spelen?");
        int aantalSpelers = input.nextInt();
        
        DomeinController Dc = new DomeinController(aantalSpelers);
        for (int i = 0; i < aantalSpelers; i++)
        {
            System.out.println("Geef je naam in:");
            naam = input.next();
            Dc.geefnaam(naam, i);
        }
        
        //tegelRij aanmaken

        Dc.vulTegelRij();
        System.out.println("We beginnen met ronde 1:");
        do {
            // Speler kiezen
            DomeinController DC = new DomeinController(Dc.getspeler(index));
            String spelerNaam = DC.geefSpelerNaam(Dc.getspeler(index));
            System.out.println("Het is de beurt van " + spelerNaam);
            //Dobbelstenen naar 8
            DC.zetAantalDobbelstenen(8);

            do {
                //Gooien
                for (int aantalIG = 0; aantalIG < DC.geefAantalDobbelstenen(); aantalIG++) {
                    DC.aanmakendobbelsteen();
                }

                System.out.println(DC.geefDobbelsteenWaarden());
                //Controle of je verder kan
                isLeeg = (DC.geefDobbelsteenWaarden().isEmpty());
                if (DC.controlerenOfJeNogVerderKan() == false) {
                    Tegel bovensteTegel = DC.geefBovensteTegel(Dc.getspeler(index));
                    System.out.println("Jammer, je beurt was niet succesvol. Je verliest een tegel.");
                    Dc.legTegelTerug(Dc.getspeler(index), bovensteTegel);
                    DC.verwijderTegel(Dc.getspeler(index));
                    DC.setEindeRonde(true);

                } else if (DC.isLaatsteKeuze() == true) {
                    System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat() + " en kan een tegel nemen.");
                    DC.setResultaat(DC.getResultaat());
                    tegel = Dc.geefTegel(DC.getResultaat());
                    Dc.neemTegel(tegel);
                    DC.voegTegelToe(tegel, Dc.getspeler(index));
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
                                        Tegel bovensteTegel = DC.geefBovensteTegel(Dc.getspeler(index));
                                        Dc.legTegelTerug(Dc.getspeler(index), bovensteTegel);
                                        DC.verwijderTegel(Dc.getspeler(index));
                                    } else {
                                        System.out.println("Er is nog een mogelijke tegel aanwezig.");
                                        DC.voegTegelToe(tegel, Dc.getspeler(index));
                                    }

                                } else {
                                    Tegel bovensteTegel = DC.geefBovensteTegel(Dc.getspeler(index));
                                    Dc.legTegelTerug(Dc.getspeler(index), bovensteTegel);
                                    DC.verwijderTegel(Dc.getspeler(index));

                                }

                            }
                        }
                    } else {
                        System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat() + "en kan een tegel nemen.");

                        DC.setResultaat(DC.getResultaat());
                        tegel = Dc.geefTegel(DC.getResultaat()); 
                        Dc.neemTegel(tegel);
                        DC.voegTegelToe(tegel, Dc.getspeler(index));
                        DC.setEindeRonde(true);

                    }

                }
                eindeRonde = DC.isEindeRonde();
                eindeSpel = Dc.isEindeSpel();
            } while (eindeRonde == false);
            if (DC.getResultaat() >= 0) 
            {
                System.out.println(DC.geefBijgehoudenTegels(Dc.getspeler(index), tegel));
            }

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
        for(index = 0; index < aantalSpelers; index++){   
            
            score = Dc.getspeler(index).berekenEindResultaat();
            if(score > hoogsteScore){
                hoogsteScore = score;
                winnaar = Dc.getspeler(index).getSpelerNaam();                
            }
        }
        
        
        System.out.println("Het spel is voorbij! De winnaar is : " + winnaar + " Met een score van: " + hoogsteScore );
    }
}
