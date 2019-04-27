package gui;

import domein.DomeinController;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AantalSpelersKeuzeScherm extends VBox {
    
    private HoofdScherm hoofdScherm;
    private DomeinController dc;
    private Label lblVraag;
    private TextField txfKeuze;
    private Button btnBevestig;

    public AantalSpelersKeuzeScherm(HoofdScherm hoofdScherm,DomeinController dc) {
        this.dc = dc;
        this.hoofdScherm = hoofdScherm;
        buildGui();

    }

    public void buildGui() {
        this.setPadding(new Insets(10));
        this.setSpacing(10);

        lblVraag = new Label();
        lblVraag.setText("Met hoeveel spelers wil je spelen?");
        lblVraag.setAlignment(Pos.CENTER);

        txfKeuze = new TextField();

        btnBevestig = new Button();
        btnBevestig.setText("Bevestig");

        this.getChildren().addAll(lblVraag, txfKeuze, btnBevestig);
        update();

        btnBevestig.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String tekstKeuze = txfKeuze.getText();
                    int KeuzeAantal = Integer.parseInt(tekstKeuze);
                    dc.voegAantalSpelersToe(KeuzeAantal);
                    btnBevestigOnAction(event);
                    update();

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

    void update() {
        txfKeuze.setText("");
    }

    private void btnBevestigOnAction(ActionEvent event) {
        hoofdScherm.toonSpelerInvoerScherm();
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
