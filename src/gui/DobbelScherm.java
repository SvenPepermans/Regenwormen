package gui;

import domein.DomeinController;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DobbelScherm extends BorderPane {

    private HoofdScherm hoofdScherm;
    private DomeinController dc;
    private Button btnDobbelsteen, btnStop, btnDobbel, btnEindig, btnSave, btnhighscore;
    private InputStream input;
    private int ronde = 0;
    private int aantalSpelers;
    private String keuze;
    private Boolean isLeeg;
    private Label lblTegel;

    // private ArrayList<Button> Buttons;
    private Label lblJongste, lblGekozenWaardenTekst, lblGekozenWaardenImage, lblVerderDoen, lblGekozenTegel, lblSpelerTegels;

    public DobbelScherm(HoofdScherm hoofdScherm, DomeinController dc) {
        this.hoofdScherm = hoofdScherm;
        this.dc = dc;
        dc.veranderVanSpeler(dc.getSpelerIndex());

        buildGui();
    }

    public void buildGui() {
                
        
        dc.setEindeRonde(false);
        this.setPadding(new Insets(10));
        this.getStyleClass().add("dobbelschermAchtergrond");
        dc.setTegel(null);
        //Grids en HBoxen aanmaken
        HBox hbDobbel = new HBox(300);
        hbDobbel.setSpacing(10);

        GridPane leftGrid = new GridPane();
        GridPane centerGrid = new GridPane();
        GridPane rightGrid = new GridPane();

        HBox hbTegelRij = new HBox();
        hbTegelRij.setSpacing(10);

        HBox hbGekozenWaarden = new HBox();
        hbGekozenWaarden.setSpacing(10);

        VBox vbVerderDoen = new VBox();
        vbVerderDoen.setSpacing(10);

        VBox vbSpelerTegels = new VBox();
        vbSpelerTegels.setSpacing(10);
        //labels aanmaken
        lblJongste = new Label("Het is aan " + String.format(dc.geefSpelerNaam()));
        lblGekozenWaardenTekst = new Label("Je gekozen waarden zijn: ");
        lblJongste.getStyleClass().add("Tekst");
        lblGekozenWaardenTekst.getStyleClass().add("Tekst");
        //Button Stop
        btnStop = new Button("Stop");
        btnStop.setVisible(false);
        btnStop.getStyleClass().add("buttons");
        btnStop.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        //Button Dobbel
        btnDobbel = new Button("Dobbel");
        btnDobbel.setVisible(true);
        btnDobbel.getStyleClass().add("buttons");
        btnDobbel.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        //Button EindigSpel
        btnEindig = new Button("Beëindig");
        btnEindig.setVisible(true);
        btnEindig.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        btnEindig.getStyleClass().add("buttons");

        btnSave = new Button("Save");
        btnSave.setVisible(true);
        btnSave.getStyleClass().add("buttons");
        
        btnhighscore = new Button("Highscore");
        btnhighscore.setVisible(true);
        btnhighscore.getStyleClass().add("buttons");
        

        if (dc.isSpelgeladen() == true) {
            btnDobbel.setVisible(false);

            for (int index = 0; index < dc.geefDobbelsteenWaarden().size(); index++) {
                String waarde = dc.geefDobbelsteenWaarden().get(index);
                switch (dc.geefDobbelsteenWaarden().get(index)) {
                    case "1":
                        input = getClass().getResourceAsStream("/images/dice1.png");
                        //   waarde = "1";
                        break;
                    case "2":
                        input = getClass().getResourceAsStream("/images/dice2.png");
                        //   waarde = "2";
                        break;
                    case "3":
                        input = getClass().getResourceAsStream("/images/dice3.png");
                        //  waarde = "3";
                        break;
                    case "4":
                        input = getClass().getResourceAsStream("/images/dice4.png");
                        //  waarde = "4";
                        break;
                    case "5":
                        input = getClass().getResourceAsStream("/images/dice5.png");
                        //   waarde = "5";
                        break;
                    case "Worm":
                        input = getClass().getResourceAsStream("/images/Worm.png");
                        //   waarde = "Worm";
                        break;
                }
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);

                btnDobbelsteen = new Button();
                btnDobbelsteen.setGraphic(imageView);

                hbDobbel.getChildren().add(btnDobbelsteen);

                {

                    btnDobbelsteen.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            keuze = waarde;

                            if (dc.addChoiceGUI(keuze) == true) {
                                dc.addChoiceGUI(keuze);
                                btnDobbel.setVisible(true);
                                update();
                            } else {
                                Alert boodschap = new Alert(Alert.AlertType.WARNING);
                                boodschap.setTitle("Er gaat iets fout.");
                                boodschap.setContentText("Je hebt dit getal al eens gekozen.");
                                boodschap.showAndWait();
                                btnDobbelsteen.requestFocus();
                            }

                            isLeeg = dc.geefDobbelsteenWaarden().isEmpty();
                            if (isLeeg == false) {
                                dc.setResultaat(0);
                                if (dc.berekenResultaat() >= 21 && dc.geefGekozenWaarden().contains("Worm")) {
                                    btnStop.setVisible(true);

                                }
                            } else {
                                dc.setResultaat(0);
                                if (dc.berekenResultaat() >= 21 && dc.geefGekozenWaarden().contains("Worm")) {
                                    btnStop.setVisible(true);
                                    btnDobbel.setVisible(false);

                                } else {
                                    dc.setBovensteTegel(dc.geefBovensteTegel());
                                    dc.legTegelTerug(dc.geefBovensteTegel());
                                    dc.verwijderTegelGUI();
                                    dc.setEindeRonde(true);
                                    Alert eindeV = new Alert(Alert.AlertType.ERROR);
                                    eindeV.setContentText("Je beurt is gefaald, je verliest een tegel en het is aan de volgende speler");
                                    eindeV.showAndWait();
                                    update();
                                }
                            }
                        }

                    });

                }
            }
            //dc.setSpelgeladen(false);
        }

        btnEindig.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dc.bepaalWinnaar();
                hoofdScherm.toonWinnaarScherm();
            }
        });
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.setEindeRonde(true);
                btnDobbelsteen.setVisible(false);
//                                    lblResultaat.setVisible(true);
//                                    lblResultaat.setText("Je beïndigt je beurt met een score van: " + String.valueOf(dc.getResultaat()));
                dc.setResultaat(dc.getResultaat()); //Resultaten anders benoemen bij vervormen naar DC.
                dc.setTegel(dc.geefTegel(dc.getResultaat()));
                Alert boodschap = new Alert(Alert.AlertType.INFORMATION);
                boodschap.setTitle("Volgende Speler!");
                boodschap.setContentText("Je beïndigt je beurt met een score van: " + String.valueOf(dc.getResultaat()));
                boodschap.showAndWait();

                if (dc.controle(dc.getTegel()) == true) {
                    if (dc.kanTegelStelenGUI(dc.getTegel()) == true) {
                        boodschap.setTitle("Volgende Speler!");
                        boodschap.setContentText("Je kan een tegel stelen van een speler");
                        boodschap.showAndWait();
//                                            lblExtra.setText("Je kan een tegel stelen van een speler");

                    } else {
                        dc.setTegel(dc.neemTegel());
                        if (dc.getTegel() == null) {
                            boodschap.setContentText("Er zijn geen tegels meer, je moet een tegel terugleggen");
                            boodschap.showAndWait();

//                                                lblExtra.setText("Er zijn geen tegels meer, je moet een tegel terugleggen");
                            dc.setBovensteTegel(dc.geefBovensteTegel());
                            dc.legTegelTerug(dc.getBovensteTegel());
                            dc.verwijderTegelGUI();
                        } else {
                            boodschap.setContentText("Er is nog een mogelijke tegel aanwezig.");
                            boodschap.showAndWait();

//                                                lblExtra.setText("Er is nog een mogelijke tegel aanwezig.");
                            dc.voegTegelToe();
                        }
                    }
                }
                update();

            }
        });

        btnDobbel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnDobbel.setVisible(false);
                for (int index = 0; index < dc.geefDobbelsteenWaarden().size(); index++) {
                    String waarde = dc.geefDobbelsteenWaarden().get(index);
                    btnDobbelsteen = new Button();
                    switch (dc.geefDobbelsteenWaarden().get(index)) {
                        case "1":
                            btnDobbelsteen.setStyle("-fx-background-image: url(/images/dice1.png);"
                                    + "-fx-background-size: 50px 50px;");
                            break;
                        case "2":
                            btnDobbelsteen.setStyle("-fx-background-image: url(/images/dice2.png);"
                                    + "-fx-background-size: 50px 50px;");
                            break;
                        case "3":
                            btnDobbelsteen.setStyle("-fx-background-image: url(/images/dice3.png);"
                                    + "-fx-background-size: 50px 50px;");
                            break;
                        case "4":
                            btnDobbelsteen.setStyle("-fx-background-image: url(/images/dice4.png);"
                                    + "-fx-background-size: 50px 50px;");
                            break;
                        case "5":
                            btnDobbelsteen.setStyle("-fx-background-image: url(/images/dice5.png);"
                                    + "-fx-background-size: 50px 50px;");
                            break;
                        case "Worm":
                            btnDobbelsteen.setStyle("-fx-background-image: url(/images/Worm.png);"
                                    + "-fx-background-size: 50px 50px;");
                            break;
                    }

                    btnDobbelsteen.setMinSize(50, 50);
                    btnDobbelsteen.setMaxSize(50, 50);
                    btnDobbelsteen.setId("btnDobbelsteen");
                    hbDobbel.getStyleClass().add("dobbelstenenEnTegels");
                    hbDobbel.getChildren().add(btnDobbelsteen);

                    //Controle over de beurt?
                    if (dc.controlerenOfJeNogVerderKan() == false) {
                        dc.setBovensteTegel(dc.geefBovensteTegel());
                        dc.legTegelTerug(dc.geefBovensteTegel());
                        dc.verwijderTegelGUI();
                        dc.setEindeRonde(true);
                        Alert eindeV = new Alert(Alert.AlertType.ERROR);
                        eindeV.setContentText("Je beurt is gefaald, je verliest een tegel en het is aan de volgende speler");
                        eindeV.showAndWait();
                        update();
                        return;
                    } else if (dc.isLaatsteKeuze() == true) {
                        dc.setResultaat(dc.getResultaat());
                        dc.setTegel(dc.geefTegel(dc.getResultaat()));
                        dc.voegTegelToe();
                        dc.setEindeRonde(true);
                        Alert eindeG = new Alert(Alert.AlertType.INFORMATION);
                        eindeG.setContentText("Je beurt is succesvol, je mag een tegel kiezen, daarna is het aan de volgende speler");
                        lblTegel.setDisable(false);
                        eindeG.showAndWait();
                        update();
                        break;
                    } else {

                        btnDobbelsteen.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                keuze = waarde;

                                if (dc.addChoiceGUI(keuze) == true) {
                                    dc.addChoiceGUI(keuze);
                                    btnDobbel.setVisible(true);
                                    update();
                                } else {
                                    Alert boodschap = new Alert(Alert.AlertType.WARNING);
                                    boodschap.setTitle("Er gaat iets fout.");
                                    boodschap.setContentText("Je hebt dit getal al eens gekozen.");
                                    boodschap.showAndWait();
                                    btnDobbelsteen.requestFocus();
                                }

                                isLeeg = dc.geefDobbelsteenWaarden().isEmpty();
                                if (isLeeg == false) {
                                    dc.setResultaat(0);
                                    if (dc.berekenResultaat() >= 21 && dc.geefGekozenWaarden().contains("Worm")) {
                                        btnStop.setVisible(true);

                                    }
                                } else {
                                    dc.setResultaat(0);
                                    if (dc.berekenResultaat() >= 21 && dc.geefGekozenWaarden().contains("Worm")) {
                                        btnStop.setVisible(true);
                                        btnDobbel.setVisible(false);

                                    } else {
                                        dc.setBovensteTegel(dc.geefBovensteTegel());
                                        dc.legTegelTerug(dc.geefBovensteTegel());
                                        dc.verwijderTegelGUI();
                                        dc.setEindeRonde(true);
                                        Alert eindeV = new Alert(Alert.AlertType.ERROR);
                                        eindeV.setContentText("Je beurt is gefaald, je verliest een tegel en het is aan de volgende speler");
                                        eindeV.showAndWait();
                                        update();
                                    }
                                }

                            }
                        });
                    }

                }
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                    dc.opslaan();
              

            }
        });
                btnhighscore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                hoofdScherm.toonHighscoreScherm();

            }
        });

        //Dobbelstenen aanmaken
        if (dc.isSpelgeladen() == false) {
            for (int aantalIG = 0; aantalIG < dc.geefAantalDobbelstenen(); aantalIG++) {
                dc.aanmakenDobbelsteen();
            }
        }
        dc.setSpelgeladen(false);

        vbVerderDoen.getChildren().addAll(btnStop, btnDobbel, btnEindig);
        //TegelRij
        for (int teller = 0; teller < dc.getTegels().size(); teller++) {

            lblTegel = new Label();
            switch (dc.getTegelValue(teller)) {
                case 21:
                    input = getClass().getResourceAsStream("/images/Tegel21.png");
                    break;
                case 22:
                    input = getClass().getResourceAsStream("/images/Tegel22.png");
                    break;
                case 23:
                    input = getClass().getResourceAsStream("/images/Tegel23.png");
                    break;
                case 24:
                    input = getClass().getResourceAsStream("/images/Tegel24.png");
                    break;
                case 25:
                    input = getClass().getResourceAsStream("/images/Tegel25.png");
                    break;
                case 26:
                    input = getClass().getResourceAsStream("/images/Tegel26.png");
                    break;
                case 27:
                    input = getClass().getResourceAsStream("/images/Tegel27.png");
                    break;
                case 28:
                    input = getClass().getResourceAsStream("/images/Tegel28.png");
                    break;
                case 29:
                    input = getClass().getResourceAsStream("/images/Tegel29.png");
                    break;
                case 30:
                    input = getClass().getResourceAsStream("/images/Tegel30.png");
                    break;
                case 31:
                    input = getClass().getResourceAsStream("/images/Tegel31.png");
                    break;
                case 32:
                    input = getClass().getResourceAsStream("/images/Tegel32.png");
                    break;
                case 33:
                    input = getClass().getResourceAsStream("/images/Tegel33.png");
                    break;
                case 34:
                    input = getClass().getResourceAsStream("/images/Tegel34.png");
                    break;
                case 35:
                    input = getClass().getResourceAsStream("/images/Tegel35.png");
                    break;
                case 36:
                    input = getClass().getResourceAsStream("/images/Tegel36.png");
                    break;
            }

            Image image2 = new Image(input);
            ImageView imageView2 = new ImageView(image2);
            lblTegel.setGraphic(imageView2);
            lblTegel.setDisable(false);
            hbTegelRij.getChildren().add(lblTegel);
            hbTegelRij.getStyleClass().add("dobbelstenenEnTegels");
            hbTegelRij.setMaxHeight(USE_PREF_SIZE);

        }

        //gekozen Tegels
        lblSpelerTegels = new Label("Jouw tegels");
        lblSpelerTegels.getStyleClass().add("Tekst");
        for(int teller=0; teller < dc.getBijgehoudenTegels().size(); teller++){
            switch (dc.getBijgehoudenTegelsValueGUI(teller)) {
                case 21:
                    input = getClass().getResourceAsStream("/images/Tegel21.png");
                    break;
                case 22:
                    input = getClass().getResourceAsStream("/images/Tegel22.png");
                    break;
                case 23:
                    input = getClass().getResourceAsStream("/images/Tegel23.png");
                    break;
                case 24:
                    input = getClass().getResourceAsStream("/images/Tegel24.png");
                    break;
                case 25:
                    input = getClass().getResourceAsStream("/images/Tegel25.png");
                    break;
                case 26:
                    input = getClass().getResourceAsStream("/images/Tegel26.png");
                    break;
                case 27:
                    input = getClass().getResourceAsStream("/images/Tegel27.png");
                    break;
                case 28:
                    input = getClass().getResourceAsStream("/images/Tegel28.png");
                    break;
                case 29:
                    input = getClass().getResourceAsStream("/images/Tegel29.png");
                    break;
                case 30:
                    input = getClass().getResourceAsStream("/images/Tegel30.png");
                    break;
                case 31:
                    input = getClass().getResourceAsStream("/images/Tegel31.png");
                    break;
                case 32:
                    input = getClass().getResourceAsStream("/images/Tegel32.png");
                    break;
                case 33:
                    input = getClass().getResourceAsStream("/images/Tegel33.png");
                    break;
                case 34:
                    input = getClass().getResourceAsStream("/images/Tegel34.png");
                    break;
                case 35:
                    input = getClass().getResourceAsStream("/images/Tegel35.png");
                    break;
                case 36:
                    input = getClass().getResourceAsStream("/images/Tegel36.png");
                    break;
            }
            lblGekozenTegel = new Label();
            Image imageGTegel = new Image(input);
            ImageView imageViewGTegel = new ImageView(imageGTegel);
            lblGekozenTegel.setGraphic(imageViewGTegel);
            vbSpelerTegels.getChildren().add(lblGekozenTegel);
            vbSpelerTegels.getStyleClass().add("dobbelstenenEnTegels");
            
            
            
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
            hbGekozenWaarden.getChildren().add(lblGekozenWaardenImage);

        }

//Linker vak Borderpane
       leftGrid.addRow(1, lblJongste);
        lblJongste.setAlignment(Pos.CENTER);
        leftGrid.setGridLinesVisible(true);
        leftGrid.setAlignment(Pos.TOP_CENTER);
        leftGrid.setHgap(75);
        leftGrid.setVgap(75);
        leftGrid.setMinWidth(250);
//Middenvak BorderPane
        centerGrid.add(hbDobbel, 0, 1);
        centerGrid.setVgap(75);
        centerGrid.setHgap(5);
        centerGrid.addRow(2, lblGekozenWaardenTekst);
        centerGrid.addRow(3, hbGekozenWaarden);
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setGridLinesVisible(true);
        centerGrid.addRow(2, vbVerderDoen);
        centerGrid.setHalignment(vbVerderDoen, HPos.LEFT);
        centerGrid.addRow(4, btnSave);
        centerGrid.addRow(4, btnhighscore);

         centerGrid.getRowConstraints().add(new RowConstraints(USE_COMPUTED_SIZE));
        centerGrid.getRowConstraints().add(new RowConstraints(100)); 
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMaxWidth(700);
        col1.setMinWidth(700);
        hbDobbel.setAlignment(Pos.CENTER);
        centerGrid.setHalignment(lblGekozenWaardenTekst, HPos.CENTER);
        hbGekozenWaarden.setAlignment(Pos.CENTER);
        centerGrid.getColumnConstraints().add(col1);
   

//Topvak BorderPane
        hbTegelRij.setPadding(new Insets(10));
        hbTegelRij.setAlignment(Pos.TOP_CENTER);
//Rechtervak
        rightGrid.addRow(2,vbSpelerTegels);
        rightGrid.setAlignment(Pos.TOP_CENTER);
        rightGrid.addRow(1, lblSpelerTegels);
        lblSpelerTegels.setAlignment(Pos.CENTER);
        vbSpelerTegels.setAlignment(Pos.CENTER);
        vbSpelerTegels.setSpacing(10);
        rightGrid.setVgap(75);
        rightGrid.setHgap(75);
        rightGrid.setMinWidth(250);
        rightGrid.setGridLinesVisible(true);
        
        setLeft(leftGrid);
        setCenter(centerGrid);
        setTop(hbTegelRij);
        setRight(rightGrid);

    }

    void update() {
        this.getChildren().clear();
        if (dc.isEindeRonde() == true) {
            aantalSpelers = dc.geefAantalSpelers();
            if (dc.getSpelerIndex() + 1 < aantalSpelers) {
                dc.setSpelerIndex(dc.getSpelerIndex() + 1);
                dc.setSpelerIndex(dc.getSpelerIndex());
                dc.setRonde(ronde);
            } else {
                ronde++;
                dc.setSpelerIndex(0);
                dc.setSpelerIndex(dc.getSpelerIndex());
                dc.setRonde(ronde);
//                System.out.println("We beginnen nu met ronde " + ronde + ": ");

            }
            dc.voegTegelNummerToe();
            dc.veranderVanSpeler(dc.getSpelerIndex());
            dc.leegGekozenWaardenSpeler();
            dc.setTegel(null);
            dc.setEindeRonde(false);
            dc.zetAantalDobbelstenen(8);
            dc.leegDobbelsteenWaardenSpeler();
            if (dc.isEindeSpel() == true) {
                dc.bepaalWinnaar();
                hoofdScherm.toonWinnaarScherm();
                return;
//            System.out.println("Het spel is voorbij! De winnaar is : " + winnaar);
            }
        }
        buildGui();
    }

//    private void test() {
//        btnDobbelsteen.setVisible(false);
//        Alert boodschap = new Alert(Alert.AlertType.INFORMATION);
//        boodschap.setTitle("Volgend ronde!");
//        boodschap.setContentText("Je beïndigt je beurt met een score van: " + String.valueOf(dc.getResultaat()));
//        boodschap.showAndWait();
////                                    lblResultaat.setVisible(true);
////                                    lblResultaat.setText("Je beïndigt je beurt met een score van: " + String.valueOf(dc.getResultaat()));
//        dc.setResultaat(dc.getResultaat()); //Resultaten anders benoemen bij vervormen naar DC.
//        dc.setTegel(dc.geefTegel(dc.getResultaat()));
//
//        if (dc.controle(dc.getTegel()) == true) {
//            if (dc.kanTegelStelenGUI(dc.getTegel()) == true) {
//                boodschap.setTitle("Volgende ronde!");
//                boodschap.setContentText("Je kan een tegel stelen van een speler");
//                boodschap.showAndWait();
////                                            lblExtra.setText("Je kan een tegel stelen van een speler");
//
//            } else {
//                dc.setTegel(dc.neemTegel());
//                if (dc.getTegel() == null) {
//                    boodschap.setContentText("Er zijn geen tegels meer, je moet een tegel terugleggen");
////                                                lblExtra.setText("Er zijn geen tegels meer, je moet een tegel terugleggen");
//                    dc.setBovensteTegel(dc.geefBovensteTegel());
//                    dc.legTegelTerug(dc.getBovensteTegel());
//                    dc.verwijderTegelGUI();
//                } else {
//                    boodschap.setContentText("Er is nog een mogelijke tegel aanwezig.");
////                                                lblExtra.setText("Er is nog een mogelijke tegel aanwezig.");
//                    dc.voegTegelToe();
//                }
//            }
//        }
//    }
    private void btnDobbelsteenOnAction(ActionEvent event) {

    }

    private void btnStopOnAction(ActionEvent event) {

    }
}
