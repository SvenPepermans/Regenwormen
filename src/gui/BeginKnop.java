package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BeginKnop extends HBox {

    private HoofdScherm hoofdScherm;
    private Button btnVerder;
    private BeginScherm parent;

    public BeginKnop(BeginScherm parent, HoofdScherm hoofdScherm) {
        this.hoofdScherm = hoofdScherm;
        this.parent = parent;
        buildGui();
    }

    public void buildGui() {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

        btnVerder = new Button();
        btnVerder.setText("Ga verder");

        this.getChildren().addAll(btnVerder);

        btnVerder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnVerderOnAction(arg0);
            }
        });

    }

    private void btnVerderOnAction(ActionEvent event) {
        hoofdScherm.toonAantalSpelersKeuzeScherm();
    }
}
