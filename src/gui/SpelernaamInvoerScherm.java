package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SpelernaamInvoerScherm extends VBox {

    private DomeinController dc;
    private Button btnGaVerder;
    private Label lblVoegSpelerToe;
    private TextField txfSpelerNaam;

    public SpelernaamInvoerScherm(DomeinController dc) {

        this.dc = dc;
        buildGui();

    }

    public void buildGui() {

        this.setPadding(new Insets(10));
        for (int counter = 1; counter <= dc.getSpelers().size(); counter++) {
            SpelerNaamInputDetailScherm Snids = new SpelerNaamInputDetailScherm(dc, counter);
            this.getChildren().add(Snids);
        }
        btnGaVerder = new Button();
        btnGaVerder.setText("Ga verder");
        this.setSpacing(10);

        this.getChildren().add(btnGaVerder);

        btnGaVerder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnGaVerderOnAction(arg0);
            }
        });

    }

    private void btnGaVerderOnAction(ActionEvent event) {
        dc.vulTegelRij();
        dc.sorteerJongNaarOud();
        verder();
    }

    public void verder() {
        Stage stage = (Stage) this.getScene().getWindow();

        double breedte = this.getScene().getWidth();
        double hoogte = this.getScene().getHeight();
        stage.setTitle("Dobbelen");
        Scene scene = new Scene(new DobbelScherm(this.dc), breedte, hoogte);
        stage.setScene(scene);
    }
}
