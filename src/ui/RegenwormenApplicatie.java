package ui;

import domein.DomeinController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
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

        //Aantal Spelers
        boolean juistAntwoord = false;
        boolean juistFormaat = false;
        Date date = null;

        int aantalSpelers = 0;
        do {
            System.out.println("Met hoeveel spelers wil je spelen? (2-8 Spelers)");
            try {
                aantalSpelers = input.nextInt();
                juistAntwoord = true;
                if (aantalSpelers < 2 || aantalSpelers > 8) {
                    System.out.println("Invalid value");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid value");
                input.next();
            }
        } while (juistAntwoord == false || aantalSpelers < 2 || aantalSpelers > 8);

        DomeinController DC = new DomeinController();
        DC.voegAantalSpelersToe(aantalSpelers);

        for (int i = 0; i < aantalSpelers; i++) {
            System.out.println("Geef je naam in:");
            naam = input.next();
            System.out.println("Geef je geboorteDatum in (YYYY.MM.DD): ");
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
                       SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

           
            do {
                String dateString = input.next();
                juistFormaat = false;
                try {
                    date = format.parse(dateString);
                    juistFormaat = true;
                } catch (ParseException e) {
                    System.out.println("Invalid format");
                }
            } while (juistFormaat == false);
            DC.geefDetails(naam, date, i);
            
        }
        
        DC.sorteerJongNaarOud();
        
        //tegelRij aanmaken
        DC.vulTegelRij();

        System.out.println("We beginnen met ronde 1:");
        do {
            // Speler kiezen

            DC.veranderVanSpeler(index);
            DC.leegGekozenWaardenSpeler();
            DC.setTegel(null);
            DC.setEindeRonde(false);
            String spelerNaam = DC.geefSpelerNaam();
            System.out.println("Het is de beurt van " + spelerNaam);
            //Dobbelstenen naar 8
            DC.zetAantalDobbelstenen(8);

            do {
                //Gooien
                for (int aantalIG = 0; aantalIG < DC.geefAantalDobbelstenen(); aantalIG++) {
                    DC.aanmakenDobbelsteen();
                }

                System.out.println(DC.geefDobbelsteenWaarden());
                //Controle of je verder kan
                isLeeg = (DC.geefDobbelsteenWaarden().isEmpty());
                if (DC.controlerenOfJeNogVerderKan() == false) {
                    DC.setBovensteTegel(DC.geefBovensteTegel());
                    System.out.println("Jammer, je beurt was niet succesvol. Je verliest een tegel.");
                    DC.legTegelTerug(DC.getBovensteTegel());
                    DC.verwijderTegel();
                    DC.setEindeRonde(true);

                } else if (DC.isLaatsteKeuze() == true) {
                    System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat() + " en kan een tegel nemen.");
                    DC.setResultaat(DC.getResultaat());
                    DC.setTegel(DC.geefTegel(DC.getResultaat()));
                    DC.neemTegel();
                    DC.voegTegelToe();
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
                                DC.setTegel(DC.geefTegel(DC.getResultaat()));
                                if (DC.controle(DC.getTegel()) == true) {
                                    if (DC.kanTegelStelen(DC.getTegel()) == true) {
                                        System.out.println("Je kan een tegel stelen van een speler");
                                        break;
                                    } else {
                                        DC.setTegel(DC.neemTegel());
                                        if (DC.getTegel() == null) {
                                            System.out.println("Er zijn geen tegels meer, je moet een tegel terugleggen");
                                            DC.setBovensteTegel(DC.geefBovensteTegel());
                                            DC.legTegelTerug(DC.getBovensteTegel());
                                            DC.verwijderTegel();
                                        } else {
                                            System.out.println("Er is nog een mogelijke tegel aanwezig.");
                                            DC.voegTegelToe();
                                        }
                                    }
                                } else {
                                    DC.setBovensteTegel(DC.geefBovensteTegel());
                                    DC.legTegelTerug(DC.getBovensteTegel());
                                    DC.verwijderTegel();

                                }

                            }
                        }

                    } else {
                        System.out.println("Je beëindigt je beurt met een score van " + DC.getResultaat() + "en kan een tegel nemen.");

                        DC.setResultaat(DC.getResultaat());
                        DC.setTegel(DC.geefTegel(DC.getResultaat()));
                        DC.neemTegel();
                        DC.voegTegelToe();
                        DC.setEindeRonde(true);

                    }

                }
                DC.leegDobbelsteenWaardenSpeler();
                eindeRonde = DC.isEindeRonde();
                eindeSpel = DC.isEindeSpel();
            } while (eindeRonde == false);
            if (DC.getResultaat() >= 0) {
                System.out.println(DC.geefBijgehoudenTegels());
            }

            if (index + 1 < aantalSpelers) {
                index++;
            } else {
                ronde++;
                System.out.println("We beginnen nu met ronde " + ronde + ": ");
                index = 0;
            }
        } while (eindeSpel == false);
        //Bepaal winnaar
        String winnaar = DC.bepaalWinnaar();

        System.out.println(
                "Het spel is voorbij! De winnaar is : " + winnaar);
    }
}
