package gui;

import domein.DomeinController;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SpelersGegevensScherm extends GridPane {
    
    private HoofdScherm hoofdScherm;
    private DomeinController dc;
    private Label lblVraag;
    private TextField txfKeuze;
    private Button btnBevestig;
     private Button btnGaVerder;
    private Label lblVoegSpelerToe;
    private TextField txfSpelerNaam;

    public SpelersGegevensScherm(HoofdScherm hoofdScherm,DomeinController dc) {
        this.dc = dc;
        this.hoofdScherm = hoofdScherm;
        buildGui();

    }

    public void buildGui() {
        this.setPadding(new Insets(10));
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        

        lblVraag = new Label();
        lblVraag.setText("Met hoeveel spelers wil je spelen?");
        lblVraag.setAlignment(Pos.CENTER);

        txfKeuze = new TextField();

        btnBevestig = new Button();
        btnBevestig.setText("Bevestig");
        this.add(lblVraag,0,0);
        this.add(txfKeuze, 0, 1);
        this.add(btnBevestig, 0, 2);
        this.setHalignment(btnBevestig, HPos.CENTER);


        btnBevestig.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String tekstKeuze = txfKeuze.getText();
                    int KeuzeAantal = Integer.parseInt(tekstKeuze);
                    dc.voegAantalSpelersToe(KeuzeAantal);
                    lblVraag.setVisible(false);
                    txfKeuze.setVisible(false);
                    btnBevestig.setVisible(false);
                    //btnBevestigOnAction(event);
                    update();
                    buildGui2();

                } catch (NumberFormatException e) {
                    Alert boodschap = new Alert(Alert.AlertType.WARNING);
                    boodschap.setTitle("Er gaat iets fout.");
                    boodschap.setContentText("Aantal spelers moet als geheel getal ingegeven worden");
                    boodschap.showAndWait();
                    txfKeuze.requestFocus();
                }

            }

        });

    }
    public void buildGui2() {
VBox VbSpelers = new VBox();
        this.setPadding(new Insets(10));
        for (int counter = 1; counter <= dc.getSpelers().size(); counter++) {
            SpelerNaamInputDetailScherm Snids = new SpelerNaamInputDetailScherm(dc, counter);
            VbSpelers.getChildren().add(Snids);
        }
        btnGaVerder = new Button();
        btnGaVerder.setText("Ga verder");
        
        this.addRow(3, VbSpelers);
        this.addRow(6,btnGaVerder);
        this.setHalignment(btnGaVerder, HPos.CENTER);

        btnGaVerder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnGaVerderOnAction(arg0);
            }
        });

    }

    void update() {
        txfKeuze.setText("");
    }

    
    private void btnGaVerderOnAction(ActionEvent event) {
        dc.vulTegelRij();
        dc.sorteerJongNaarOud();
        hoofdScherm.toonDobbelScherm();
    }

    /* public void bevestigEnGaVerder() {
        Stage stage = (Stage) this.getScene().getWindow();

        double breedte = this.getScene().getWidth();
        double hoogte = this.getScene().getHeight();
        stage.setTitle("Spelersnamen invoeren");
        Scene scene = new Scene(new SpelernaamInvoerScherm(this.dc), breedte, hoogte);
        stage.setScene(scene);
    }*/
} 
