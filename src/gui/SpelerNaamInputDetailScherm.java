package gui;

import domein.DomeinController;
import java.time.ZoneId;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SpelerNaamInputDetailScherm extends HBox {

    private HoofdScherm hoofdScherm;
    private Button btnVoegSpelerToe;
    private Label lblVoegSpelerToe;
    private DatePicker dpGeboorteDatum;
    private TextField txfSpelerNaam;
    private DomeinController dc;
    private int counter;
    private SpelersGegevensScherm sgs;

    public SpelerNaamInputDetailScherm(DomeinController dc,SpelersGegevensScherm parent, int counter) {
        this.dc = dc;
        this.sgs = parent;
        this.counter = counter;
         this.getStyleClass().add("SNIDS");
         this.setId(""+counter);
        buildGui();

    }

    public void buildGui() {
        
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        
        lblVoegSpelerToe = new Label();
        lblVoegSpelerToe.setText(String.format("Geef de naam in van speler %d", counter));
        lblVoegSpelerToe.setId("VoegSpelersToe");
        txfSpelerNaam = new TextField("");
        txfSpelerNaam.getStyleClass().add("input");
        txfSpelerNaam.requestFocus();

        dpGeboorteDatum = new DatePicker();
        dpGeboorteDatum.getStyleClass().add("input");
        
        btnVoegSpelerToe = new Button();
        btnVoegSpelerToe.setText("Voeg toe.");
        btnVoegSpelerToe.getStyleClass().add("buttons");
        btnVoegSpelerToe.setId("btnVoegSpelerToe");
        
        this.getChildren().addAll(lblVoegSpelerToe, txfSpelerNaam, dpGeboorteDatum, btnVoegSpelerToe);
        this.setMaxHeight(USE_COMPUTED_SIZE);

        btnVoegSpelerToe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String SpelerNaam = txfSpelerNaam.getText();
                Date geboorteDatum = Date.from(dpGeboorteDatum.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                int index = counter - 1;
                dc.geefDetails(SpelerNaam, geboorteDatum, index);
                btnVoegSpelerToeOnAction(event);
                btnVoegSpelerToe.setVisible(false);              
                update();

            }
        });

         txfSpelerNaam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dpGeboorteDatum.requestFocus();
            }
        });
        
        dpGeboorteDatum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnVoegSpelerToe.requestFocus();
            }
        });
             

        
    }

    void update() {
        txfSpelerNaam.setEditable(false);
        dpGeboorteDatum.setEditable(false);
    }

    public void btnVoegSpelerToeOnAction(ActionEvent event) {
       
        if(counter == dc.getSpelers().size()){
            sgs.getBtnGaVerder().requestFocus();
        }else{
             txfSpelerNaam.setFocusTraversable(true);
        }
 
    }
    
    public TextField getTxfSpelerNaam(){
    
        return txfSpelerNaam;
}
}
