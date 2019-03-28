package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SpelernaamInvoerScherm extends VBox {

    private DomeinController dc;
    private Button btnVoegSpelersToe;
    AantalSpelersKeuzeScherm AsP = new AantalSpelersKeuzeScherm(dc);
    private Label lblVoegSpelerToe;
    private TextField txfSpelerNaam;

    public SpelernaamInvoerScherm(DomeinController dc) {

        this.dc = dc;
        buildGui();

    }

    public void buildGui() {

        SpelerNaamInputDetailScherm Snids = new SpelerNaamInputDetailScherm(dc, AsP.getAantalSpelers());
        this.setPadding(new Insets(10));

        btnVoegSpelersToe = new Button();
        btnVoegSpelersToe.setText("Voeg spelers toe");
        this.setSpacing(10);

        for (int counter = 1; counter <= AsP.getAantalSpelers(); counter++) {
            lblVoegSpelerToe = new Label();
            lblVoegSpelerToe.setText(String.format("Geef de naam in van speler %d", counter));
            
            txfSpelerNaam = new TextField("");
            this.getChildren().addAll(lblVoegSpelerToe, txfSpelerNaam);
        }
        this.getChildren().add(btnVoegSpelersToe);

    }

}
