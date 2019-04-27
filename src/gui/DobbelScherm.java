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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;



public class DobbelScherm extends GridPane {

    private DomeinController dc;
    private Button btnDobbelsteen;
    private InputStream input;
    private int spelerIndex = 0;
    private String keuze;
    // private ArrayList<Button> Buttons;

    private Label lblJongste, lblGekozenWaarden;

    public DobbelScherm(DomeinController dc) {

        this.dc = dc;
        dc.veranderVanSpeler(spelerIndex);
        
        buildGui();
    }

    public void buildGui() { 
        this.setStyle("-fx-alignment: CENTER;"
                + "-fx-width: 100%;"
                + "-fx-height: 100%;");
        setGridLinesVisible(true);
        dc.setEindeRonde(false);
        this.setHgap(10);
        this.setVgap(10);     
        this.setPadding(new Insets(10));
        dc.setTegel(null);
       

//        ColumnConstraints column1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
//        column1.setHgrow(Priority.ALWAYS);
//        ColumnConstraints column2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
//        column2.setHgrow(Priority.ALWAYS);
//        ColumnConstraints column3 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
//        column3.setHgrow(Priority.ALWAYS);
//
//        this.getColumnConstraints().addAll(column1, column2, column3);

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
           
            HBox hbox = new HBox(300);
            hbox.setSpacing(10);
            HBox.setHgrow(hbox, Priority.ALWAYS);
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);

            btnDobbelsteen = new Button();
            btnDobbelsteen.setGraphic(imageView);
            btnDobbelsteen.setText(waarde);

            
                    
            hbox.getChildren().add(btnDobbelsteen);
                  this.addRow(3, hbox);
            hbox.setAlignment(Pos.CENTER);
            btnDobbelsteen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    String keuze = ((Button) event.getSource()).getText();

                    if (dc.controlerenOfJeNogVerderKan() == false) {
                        dc.setBovensteTegel(dc.geefBovensteTegel());
                        dc.legTegelTerug(dc.geefBovensteTegel());
                        dc.verwijderTegel();
                        dc.setEindeRonde(true);
                        Alert eindeV = new Alert(Alert.AlertType.CONFIRMATION);
                        eindeV.setContentText("Je beurt is gefaald, je verliest een tegel en het is aan de volgende speler");
                        eindeV.showAndWait();
                        update();
                    } else if (dc.isLaatsteKeuze() == true) {
                        dc.setResultaat(dc.getResultaat());
                        dc.setTegel(dc.geefTegel(dc.getResultaat()));
                        dc.voegTegelToe();
                        dc.setEindeRonde(true);
                        Alert eindeG = new Alert(Alert.AlertType.CONFIRMATION);
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
        lblJongste = new Label(String.format(dc.geefSpelerNaam()));
        this.addRow(1, lblJongste);

        lblGekozenWaarden = new Label(
                String.join(", ", dc.geefGekozenWaarden())
        );
        this.addRow(4, lblGekozenWaarden);
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
