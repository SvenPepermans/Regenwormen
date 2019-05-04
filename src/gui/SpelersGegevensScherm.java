package gui;

import domein.DomeinController;
import java.io.InputStream;
import java.lang.reflect.Array;
import javafx.event.ActionEvent;
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
    private Button btnVoegSpelerToe;
    private Label lblVoegSpelerToe;
    private DatePicker dpGeboorteDatum;
    private TextField txfSpelerNaam;
    private int keuzeAantal;

    public SpelersGegevensScherm(HoofdScherm hoofdScherm, DomeinController dc) {
        this.dc = dc;
        this.hoofdScherm = hoofdScherm;
        getStyleClass().add("SNIDS");
        buildGui();

    }

    public void buildGui() {
        this.setPadding(new Insets(10));
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);

        HBox Buttons = new HBox();

        lblVraag = new Label();
        lblVraag.setText("Met hoeveel spelers wil je spelen?");
        lblVraag.setAlignment(Pos.CENTER);

        txfKeuze = new TextField();

        InputStream inputBevestig = getClass().getResourceAsStream("/images/confirmButton.png");
        Image imageBevestig = new Image(inputBevestig);
        ImageView imageView1 = new ImageView(imageBevestig);
        btnBevestig = new Button("Bevestig", imageView1);

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

        this.add(lblVraag, 0, 0);
        this.add(txfKeuze, 0, 1);
        Buttons.getChildren().addAll(btnTerug, btnBevestig);
        this.add(Buttons, 0, 2);
        Buttons.setAlignment(Pos.CENTER);
        Buttons.setSpacing(5);

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
                    Buttons.getChildren().remove(btnBevestig);
                    Buttons.getChildren().add(btnGaVerder);
                    update();
                    btnTerug.setVisible(true);
                    genereerSpelers();
                }
            }

        });

    }

    public void genereerSpelers() {
        VBox VbSpelers = new VBox();
        VbSpelers.setPadding(new Insets(10));
        for (int counter = 1; counter <= dc.getSpelers().size(); counter++) {
            SpelerNaamInputDetailScherm Snids = new SpelerNaamInputDetailScherm(dc, counter);
            VbSpelers.getChildren().add(Snids);
            // VbSpelers.getChildren().add(buildDetailScherm(counter));    
        }

        this.add(VbSpelers, 0, 0);
        this.setAlignment(Pos.CENTER);
        this.setGridLinesVisible(true);

        btnGaVerder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnGaVerderOnAction(arg0);
            }
        });

    }

//       public HBox buildDetailScherm(int counter) {
//     
//        HBox HbSpelers = new HBox();
//        HbSpelers.setPadding(new Insets(10));
//        HbSpelers.setSpacing(10);
//        lblVoegSpelerToe = new Label();
//        lblVoegSpelerToe.setText(String.format("Geef de naam in van speler %d", counter));
//
//        txfSpelerNaam = new TextField("");
//        txfSpelerNaam.requestFocus();
//
//        dpGeboorteDatum = new DatePicker();
//
//        btnVoegSpelerToe = new Button();
//        btnVoegSpelerToe.setText("Voeg toe.");
//
//        HbSpelers.getChildren().addAll(lblVoegSpelerToe, txfSpelerNaam, dpGeboorteDatum, btnVoegSpelerToe);
//
//        btnVoegSpelerToe.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                String SpelerNaam = txfSpelerNaam.getText();
//                Date geboorteDatum = Date.from(dpGeboorteDatum.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                int index = counter - 1;
//                dc.geefDetails(SpelerNaam, geboorteDatum, index);
//                btnVoegSpelerToeOnAction(event);
//                btnVoegSpelerToe.setVisible(false);
//                detailSchermUpdate();
//
//            }
//        });
//        return HbSpelers;
//    }
//
//    void detailSchermUpdate() {
//        txfSpelerNaam.setEditable(false);
//        dpGeboorteDatum.setEditable(false);
//    }
//
//    public void btnVoegSpelerToeOnAction(ActionEvent event) {
//        txfSpelerNaam.setFocusTraversable(true);
//    }
    void update() {
        txfKeuze.setText("");
    }

    private void btnGaVerderOnAction(ActionEvent event) {
        dc.vulTegelRij();
        dc.sorteerJongNaarOud();
        hoofdScherm.toonDobbelScherm();
    }

    private void btnTerugOnAction(ActionEvent event) {
        this.getChildren().clear();
        dc.emptySpelers();

        buildGui();
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
