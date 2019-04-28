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
    private Button btnDobbelsteen, btnTegel;
    private InputStream input;
    private int spelerIndex = 0;
    private String keuze;
    // private ArrayList<Button> Buttons;

    private Label lblJongste, lblGekozenWaardenTekst, lblGekozenWaardenImage;

    public DobbelScherm(HoofdScherm hoofdScherm, DomeinController dc) {
        this.hoofdScherm = hoofdScherm;
        this.dc = dc;
        dc.veranderVanSpeler(spelerIndex);

        buildGui();
    }

    public void buildGui() {
        this.setStyle("-fx-alignment: LEFT;"
                + "-fx-width: 100%;"
                + "-fx-height: 100%;");
        dc.setEindeRonde(false);
        this.setPadding(new Insets(10));
        dc.setTegel(null);
        HBox HbDobbel = new HBox(300);
        HbDobbel.setSpacing(10);

        GridPane leftGrid = new GridPane();
        GridPane centerGrid = new GridPane();

        HBox HbTegelRij = new HBox();
        HbTegelRij.setSpacing(10);

        HBox HbGekozenWaarden = new HBox();
        HbGekozenWaarden.setSpacing(10);
        
        for (int aantalIG = 0; aantalIG < dc.geefAantalDobbelstenen(); aantalIG++) {
            dc.aanmakenDobbelsteen();
        }

        for (int index = 0; index < dc.geefDobbelsteenWaarden().size(); index++) {
            String waarde = "";
            switch (dc.geefDobbelsteenWaarden().get(index)) {
                case "1":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice1.png");
                    waarde = "1";
                    break;
                case "2":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice2.png");
                    waarde = "2";
                    break;
                case "3":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice3.png");
                    waarde = "3";
                    break;
                case "4":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice4.png");
                    waarde = "4";
                    break;
                case "5":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice5.png");
                    waarde = "5";
                    break;
                case "Worm":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice6.png");
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

                    String keuze = ((Button) event.getSource()).getText();
                        
                    if (dc.controlerenOfJeNogVerderKan() == false) {
                        dc.setBovensteTegel(dc.geefBovensteTegel());
                        dc.legTegelTerug(dc.geefBovensteTegel());
                        dc.verwijderTegel();
                        dc.setEindeRonde(true);
                        Alert eindeV = new Alert(Alert.AlertType.ERROR);
                        eindeV.setContentText("Je beurt is gefaald, je verliest een tegel en het is aan de volgende speler");
                        eindeV.showAndWait();
                        update();
                    } else if (dc.isLaatsteKeuze() == true) {
                        dc.setResultaat(dc.getResultaat());
                        dc.setTegel(dc.geefTegel(dc.getResultaat()));
                        dc.voegTegelToe();
                        dc.setEindeRonde(true);
                        Alert eindeG = new Alert(Alert.AlertType.INFORMATION);
                        eindeG.setContentText("Je beurt is succesvol, je mag een tegel kiezen, daarna is het aan de volgende speler");
                        eindeG.showAndWait();
                        update();
                    } else {
                        if (dc.addChoiceGUI(keuze) == true) {
                            dc.addChoiceGUI(keuze);
                            update();
                        } else {
                            Alert boodschap = new Alert(Alert.AlertType.WARNING);
                            boodschap.setTitle("Er gaat iets fout.");
                            boodschap.setContentText("Je hebt dit getal al eens gekozen.");
                            boodschap.showAndWait();
                            btnDobbelsteen.requestFocus();
                        }
                    }

                }
            });

        }

        for (int teller = 0; teller < dc.getTegels().size(); teller++) {

            btnTegel = new Button();
            btnTegel.setText(String.valueOf(dc.getTegelValue(teller)));

            Image image2 = new Image(getClass().getResourceAsStream("/images/dice6.png"));
            ImageView imageView2 = new ImageView(image2);
            btnTegel.setGraphic(imageView2);
            btnTegel.setDisable(true);
            HbTegelRij.getChildren().add(btnTegel);

        }

        lblJongste = new Label("Het is aan Speler: " + String.format(dc.geefSpelerNaam()));
        lblGekozenWaardenTekst = new Label("Je gekozen waarden zijn: ");

        for (int index = 0; index < dc.geefGekozenWaarden().size(); index++) {
            String waarde = "";
            switch (dc.geefGekozenWaarden().get(index)) {
                case "1":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice1.png");
                    waarde = "1";
                    break;
                case "2":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice2.png");
                    waarde = "2";
                    break;
                case "3":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice3.png");
                    waarde = "3";
                    break;
                case "4":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice4.png");
                    waarde = "4";
                    break;
                case "5":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice5.png");
                    waarde = "5";
                    break;
                case "Worm":
                    input
                            = //
                            getClass().getResourceAsStream("/images/dice6.png");
                    waarde = "Worm";
                    break;
            }
        

            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            
            lblGekozenWaardenImage = new Label();
            lblGekozenWaardenImage.setGraphic(imageView);
            HbGekozenWaarden.getChildren().add(lblGekozenWaardenImage);
            
            
        }

            leftGrid.addRow(1, lblJongste);           
            leftGrid.setVgap(200);
            leftGrid.setGridLinesVisible(true);
            this.setLeft(leftGrid);

            centerGrid.add(HbDobbel, 0, 1);
            centerGrid.setVgap(75);
            centerGrid.setHgap(150);
            centerGrid.addRow(3, lblGekozenWaardenTekst);
            centerGrid.addRow(4, HbGekozenWaarden);
            centerGrid.setAlignment(Pos.BASELINE_CENTER);
            centerGrid.setGridLinesVisible(true);
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setMaxWidth(500);
            col1.setMinWidth(500);
            centerGrid.getColumnConstraints().add(col1);
            this.setCenter(centerGrid);

            this.setTop(HbTegelRij);
            HbTegelRij.setPadding(new Insets(10));
            HbTegelRij.setAlignment(Pos.CENTER);

        }

        void update
        
            () {
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
