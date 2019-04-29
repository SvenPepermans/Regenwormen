package gui;

import domein.DomeinController;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class DobbelScherm extends BorderPane {

    private HoofdScherm hoofdScherm;
    private DomeinController dc;
    private Button btnDobbelsteen, btnTegel, btnJa, btnNee;
    private InputStream input;
    private int spelerIndex = 0;
    private String keuze, antwoord;
    private Boolean isLeeg;
    private char antwoordVerderDoen;
    // private ArrayList<Button> Buttons;

    private Label lblJongste, lblGekozenWaardenTekst, lblGekozenWaardenImage, lblVerderDoen, lblResultaat, lblExtra;

    public DobbelScherm(HoofdScherm hoofdScherm, DomeinController dc) {
        this.hoofdScherm = hoofdScherm;
        this.dc = dc;
        dc.veranderVanSpeler(spelerIndex);

        buildGui();
    }

    public void buildGui() {
     
        dc.setEindeRonde(false);
        this.setPadding(new Insets(10));
        dc.setTegel(null);
        //Grids en HBoxen aanmaken
        HBox HbDobbel = new HBox(300);
        HbDobbel.setSpacing(10);

        GridPane leftGrid = new GridPane();
        GridPane centerGrid = new GridPane();

        HBox HbTegelRij = new HBox();
        HbTegelRij.setSpacing(10);

        HBox HbGekozenWaarden = new HBox();
        HbGekozenWaarden.setSpacing(10);

        HBox HbVerderDoen = new HBox();
        HbVerderDoen.setSpacing(10);
        //labels aanmaken
        lblJongste = new Label("Het is aan Speler: " + String.format(dc.geefSpelerNaam()));
        lblGekozenWaardenTekst = new Label("Je gekozen waarden zijn: ");
        lblVerderDoen = new Label("Wil je verder doen?");
        lblVerderDoen.setVisible(false);
        lblResultaat = new Label();
        lblResultaat.setVisible(false);
        lblExtra = new Label();
       //Buttons Ja en Nee
        btnJa = new Button("Ja");
        btnJa.setVisible(false);
        btnNee = new Button("Nee");
        btnNee.setVisible(false);

        btnJa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                antwoord = btnJa.getText();
                if (antwoord.equals("Ja")) {
                    antwoordVerderDoen = 'J';
                }
                dc.wilJeVerderSpelenGUI(antwoordVerderDoen);
            }
        });

        btnNee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                antwoord = btnNee.getText();
                if (antwoord.equals("Nee")) {
                    antwoordVerderDoen = 'N';
                }
                dc.wilJeVerderSpelenGUI(antwoordVerderDoen);
            }
        });
        //Dobbelstenen aanmaken
        for (int aantalIG = 0; aantalIG < dc.geefAantalDobbelstenen(); aantalIG++) {
            dc.aanmakenDobbelsteen();
        }
        //Afbeeldingen die met waarde overeenkomen toekennen aan dobbelstenen
        for (int index = 0; index < dc.geefDobbelsteenWaarden().size(); index++) {
            String waarde = "";
            switch (dc.geefDobbelsteenWaarden().get(index)) {
                case "1":
                    input = getClass().getResourceAsStream("/images/dice1.png");
                    waarde = "1";
                    break;
                case "2":
                    input = getClass().getResourceAsStream("/images/dice2.png");
                    waarde = "2";
                    break;
                case "3":
                    input = getClass().getResourceAsStream("/images/dice3.png");
                    waarde = "3";
                    break;
                case "4":
                    input = getClass().getResourceAsStream("/images/dice4.png");
                    waarde = "4";
                    break;
                case "5":
                    input = getClass().getResourceAsStream("/images/dice5.png");
                    waarde = "5";
                    break;
                case "Worm":
                    input = getClass().getResourceAsStream("/images/Worm.png");
                    waarde = "Worm";
                    break;
            }

            Image image = new Image(input);
            ImageView imageView = new ImageView(image);

            btnDobbelsteen = new Button();
            btnDobbelsteen.setGraphic(imageView);
            btnDobbelsteen.setText(waarde);
            HbDobbel.getChildren().add(btnDobbelsteen);

            

            btnDobbelsteen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    keuze = ((Button) event.getSource()).getText();

                    if (dc.addChoiceGUI(keuze) == true) {
                        dc.addChoiceGUI(keuze);
                        dc.berekenResultaat();
                        update();
//                    } else {
//                        Alert boodschap = new Alert(Alert.AlertType.WARNING);
//                        boodschap.setTitle("Er gaat iets fout.");
//                        boodschap.setContentText("Je hebt dit getal al eens gekozen.");
//                        boodschap.showAndWait();
//                        btnDobbelsteen.requestFocus();
//                    }
//                    
//                    isLeeg = dc.geefDobbelsteenWaarden().isEmpty();
//                    if (isLeeg == false) {
//                      if (dc.getResultaat() >= 21 && dc.geefGekozenWaarden().contains("Worm")) {  
//                        btnJa.setVisible(true);
//                        btnNee.setVisible(true);
//                        lblVerderDoen.setVisible(true);
//
//                        if (dc.wilJeVerderSpelenGUI(antwoordVerderDoen) == true) {
//                            btnDobbelsteen.setVisible(false);
//                            lblResultaat.setVisible(true);
//                            lblResultaat.setText("Je beïndigt je beurt met een score van: " + String.valueOf(dc.getResultaat()));
//                            dc.setResultaat(dc.getResultaat()); //Resultaten anders benoemen bij vervormen naar DC.
//                            dc.setTegel(dc.geefTegel(dc.getResultaat()));
//
//                            if (dc.controle(dc.getTegel()) == true) {
//                                if (dc.kanTegelStelen(dc.getTegel()) == true) {
//                                    lblExtra.setText("Je kan een tegel stelen van een speler");
//                                } else {
//                                    dc.setTegel(dc.neemTegel());
//                                    if (dc.getTegel() == null) {
//                                        lblExtra.setText("Er zijn geen tegels meer, je moet een tegel terugleggen");
//                                        dc.setBovensteTegel(dc.geefBovensteTegel());
//                                        dc.legTegelTerug(dc.getBovensteTegel());
//                                        dc.verwijderTegel();
//                                    } else {
//                                        lblExtra.setText("Er is nog een mogelijke tegel aanwezig.");
//                                        dc.voegTegelToe();
//                                    }
//                                }
//                            }
//                        } else {
//                            dc.setBovensteTegel(dc.geefBovensteTegel());
//                            dc.legTegelTerug(dc.getBovensteTegel());
//                            dc.verwijderTegel();
//
//                        }
//                      }
                    }
//
                }
            });
//            

        }
        //Controle over de beurt?
//        if (dc.controlerenOfJeNogVerderKan() == false) {
//                dc.setBovensteTegel(dc.geefBovensteTegel());
//                dc.legTegelTerug(dc.geefBovensteTegel());
//                dc.verwijderTegel();
//                dc.setEindeRonde(true);
//                Alert eindeV = new Alert(Alert.AlertType.ERROR);
//                eindeV.setContentText("Je beurt is gefaald, je verliest een tegel en het is aan de volgende speler");
//                eindeV.showAndWait();
//                update();
//            } else if (dc.isLaatsteKeuze() == true) {
//                dc.setResultaat(dc.getResultaat());
//                dc.setTegel(dc.geefTegel(dc.getResultaat()));
//                dc.voegTegelToe();
//                dc.setEindeRonde(true);
//                Alert eindeG = new Alert(Alert.AlertType.INFORMATION);
//                eindeG.setContentText("Je beurt is succesvol, je mag een tegel kiezen, daarna is het aan de volgende speler");
//                btnTegel.setDisable(false);
//                eindeG.showAndWait();
//                update();
//            }
        
       
        HbVerderDoen.getChildren().addAll(btnJa, btnNee);
        //TegelRij
        for (int teller = 0; teller < dc.getTegels().size(); teller++) {

            btnTegel = new Button();
            btnTegel.setText(String.valueOf(dc.getTegelValue(teller)));

            switch (dc.getTegelValue(teller)) {
                case 21:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 22:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 23:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 24:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 25:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 26:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 27:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 28:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 29:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 30:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 31:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 32:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 33:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 34:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 35:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
                case 36:
                    input = getClass().getResourceAsStream("/images/dice6.png");
                    break;
            }

            Image image2 = new Image(input);
            ImageView imageView2 = new ImageView(image2);
            btnTegel.setGraphic(imageView2);
            btnTegel.setDisable(true);
            HbTegelRij.getChildren().add(btnTegel);

        }
       
//gekozen waarden
        for (int index = 0; index < dc.geefGekozenWaarden().size(); index++) {
            String waarde = "";
            switch (dc.geefGekozenWaarden().get(index)) {
                case "1":
                    input = getClass().getResourceAsStream("/images/dice1.png");
                    waarde = "1";
                    break;
                case "2":
                    input = getClass().getResourceAsStream("/images/dice2.png");
                    waarde = "2";
                    break;
                case "3":
                    input = getClass().getResourceAsStream("/images/dice3.png");
                    waarde = "3";
                    break;
                case "4":
                    input = getClass().getResourceAsStream("/images/dice4.png");
                    waarde = "4";
                    break;
                case "5":
                    input = getClass().getResourceAsStream("/images/dice5.png");
                    waarde = "5";
                    break;
                case "Worm":
                    input = getClass().getResourceAsStream("/images/Worm.png");
                    waarde = "Worm";
                    break;
            }

            Image image = new Image(input);
            ImageView imageView = new ImageView(image);

            lblGekozenWaardenImage = new Label();
            lblGekozenWaardenImage.setGraphic(imageView);
            HbGekozenWaarden.getChildren().add(lblGekozenWaardenImage);

        }

//Linker vak Borderpane
        leftGrid.addRow(1, lblJongste);
        leftGrid.setVgap(200);
        leftGrid.setGridLinesVisible(false);
        this.setLeft(leftGrid);
//Middenvak BorderPane
        centerGrid.add(HbDobbel, 0, 1);
        centerGrid.setVgap(75);
        centerGrid.setHgap(150);
        centerGrid.addRow(3, lblGekozenWaardenTekst);
        centerGrid.addRow(4, HbGekozenWaarden);
        centerGrid.setAlignment(Pos.BASELINE_CENTER);
        centerGrid.setGridLinesVisible(false);
//        centerGrid.addRow(2, lblVerderDoen);
//        centerGrid.addRow(3, HbVerderDoen);
//        centerGrid.addRow(1, lblResultaat);
//        centerGrid.setHalignment(lblVerderDoen, HPos.LEFT);
//        centerGrid.setHalignment(HbVerderDoen, HPos.LEFT);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMaxWidth(700);
        col1.setMinWidth(700);
        HbDobbel.setAlignment(Pos.CENTER);
        centerGrid.setHalignment(lblGekozenWaardenTekst, HPos.CENTER);
        HbGekozenWaarden.setAlignment(Pos.CENTER);
        centerGrid.getColumnConstraints().add(col1);
        this.setCenter(centerGrid);
//Topvak BorderPane
        this.setTop(HbTegelRij);
        HbTegelRij.setPadding(new Insets(10));
        HbTegelRij.setAlignment(Pos.CENTER);

    }
            
    void update() {
        this.getChildren().clear();
        if (dc.isEindeRonde() == true) {
            spelerIndex = spelerIndex + 1;
            dc.veranderVanSpeler(spelerIndex);
        }
        buildGui();

    }

    private void btnDobbelsteenOnAction(ActionEvent event) {

    }
}
