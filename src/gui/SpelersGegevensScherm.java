package gui;

import domein.DomeinController;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SpelersGegevensScherm extends GridPane {

    private HoofdScherm hoofdScherm;
    private DomeinController dc;
    private Label lblVraag;
    private TextField txfKeuze;
    private Button btnBevestig, btnGaVerder, btnTerug;
    private DatePicker dpGeboorteDatum;
    private TextField txfSpelerNaam;
    private int keuzeAantal;
    private GridPane GP;

    public SpelersGegevensScherm(HoofdScherm hoofdScherm, DomeinController dc) {
        this.dc = dc;
        this.hoofdScherm = hoofdScherm;
        GP = new GridPane();
        buildGui();

    }

    public void buildGui() {
        this.setPadding(new Insets(10));
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);

        HBox Buttons = new HBox();
        GP.getStyleClass().add("SpelerAantalInvoer");
        GP.setMinHeight(USE_PREF_SIZE);
        GP.setVgap(10);

        lblVraag = new Label();
        lblVraag.setText("Met hoeveel spelers wil je spelen?");
        lblVraag.setAlignment(Pos.CENTER);
        lblVraag.getStyleClass().add("Tekst");
        txfKeuze = new TextField();
        txfKeuze.getStyleClass().add("input");

        InputStream inputBevestig = getClass().getResourceAsStream("/images/confirmButton.png");
        Image imageBevestig = new Image(inputBevestig);
        ImageView imageView1 = new ImageView(imageBevestig);
        btnBevestig = new Button("Bevestig", imageView1);
        btnBevestig.setDefaultButton(true);

        InputStream inputTerug = getClass().getResourceAsStream("/images/backButton.png");
        Image imageTerug = new Image(inputTerug);
        ImageView imageView2 = new ImageView(imageTerug);
        btnTerug = new Button("Terug", imageView2);
        btnTerug.setVisible(false);

        btnTerug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {

                btnTerugOnAction(arg0);

            }
        });

        InputStream inputGaVerder = getClass().getResourceAsStream("/images/gaVerderButton.png");
        Image imageGaVerder = new Image(inputGaVerder);
        ImageView imageView3 = new ImageView(imageGaVerder);
        btnGaVerder = new Button("Ga Verder", imageView3);
        
        btnGaVerder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnGaVerderOnAction(arg0);
            }
        });

        GP.add(lblVraag, 0, 0);
        GP.add(txfKeuze, 0, 1);
        Buttons.getChildren().addAll(btnBevestig);
        btnBevestig.setAlignment(Pos.CENTER);
        GP.add(Buttons, 0, 2);
        Buttons.setAlignment(Pos.CENTER);
        Buttons.setSpacing(5);
        this.getChildren().add(GP);
        GP.setAlignment(Pos.CENTER);

        txfKeuze.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnBevestig.requestFocus();
            }
        });

        btnBevestig.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String tekstKeuze = txfKeuze.getText();
                    keuzeAantal = Integer.parseInt(tekstKeuze);
                    if (keuzeAantal < 2 || keuzeAantal > 8) {
                        Alert boodschap = new Alert(Alert.AlertType.WARNING);
                        boodschap.setTitle("Er gaat iets fout.");
                        boodschap.setContentText("Aantal spelers moet tussen de 2 en 8 liggen");
                        boodschap.showAndWait();
                        txfKeuze.requestFocus();

                    }
                } catch (NumberFormatException e) {
                    Alert boodschap = new Alert(Alert.AlertType.WARNING);
                    boodschap.setTitle("Er gaat iets fout.");
                    boodschap.setContentText("Aantal spelers moet als geheel getal ingegeven worden");
                    boodschap.showAndWait();
                    txfKeuze.requestFocus();
                }
                if (keuzeAantal >= 2 && keuzeAantal <= 8) {
                    dc.voegAantalSpelersToe(keuzeAantal);
                    lblVraag.setVisible(false);
                    txfKeuze.setVisible(false);
                    btnBevestig.setDefaultButton(false);
                    Buttons.getChildren().remove(btnBevestig);
                    Buttons.getChildren().addAll(btnTerug, btnGaVerder);
                    btnGaVerder.setDefaultButton(true);
                    btnTerug.setVisible(true);
                    update();

                    genereerSpelers();
                }
            }

        });
        btnBevestig.getStyleClass().add("buttons");
        btnGaVerder.getStyleClass().add("buttons");
        btnTerug.getStyleClass().add("buttons");

    }

    public void genereerSpelers() {
        VBox VbSpelers = new VBox();
        VbSpelers.setPadding(new Insets(10));
        for (int counter = 1; counter <= dc.getSpelers().size(); counter++) {

            SpelerNaamInputDetailScherm Snids = new SpelerNaamInputDetailScherm(dc,this, counter);
            VbSpelers.getChildren().add(Snids);
            Snids.setId("" + counter);
            if(counter == 1){
                 Platform.runLater(() ->  Snids.getTxfSpelerNaam().requestFocus());
               
            }
           
        }
       
        GP.add(VbSpelers, 0, 0);
        GP.setAlignment(Pos.CENTER);

       

    }

    void detailSchermUpdate() {
        txfSpelerNaam.setEditable(false);
        dpGeboorteDatum.setEditable(false);
    }

    void update() {
        txfKeuze.setText("");
    }

    private void btnGaVerderOnAction(ActionEvent event) {
        dc.vulTegelRij();
        dc.sorteerJongNaarOud();
        hoofdScherm.toonDobbelScherm();
    }

    private void btnTerugOnAction(ActionEvent event) {
        GP.getChildren().clear();
        this.getChildren().clear();

        dc.emptySpelers();

        buildGui();
    }

    public Button getBtnGaVerder(){
        return btnGaVerder;
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
