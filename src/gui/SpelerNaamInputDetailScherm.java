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

public class SpelerNaamInputDetailScherm extends HBox
{
     private Button btnVoegSpelerToe;
    private Label lblVoegSpelerToe;
    private DatePicker dpGeboorteDatum;
    private TextField txfSpelerNaam;
    private DomeinController dc;
   private int counter;
    
    
    
    public SpelerNaamInputDetailScherm(DomeinController dc, int counter){
        this.dc = dc;
        this.counter = counter;
        buildGui();
        
    }
    
 public void buildGui(){
    this.setPadding(new Insets(10));    
    this.setSpacing(10);
      
      lblVoegSpelerToe = new Label();
    lblVoegSpelerToe.setText(String.format("Geef de naam in van speler %d", counter));
      
    txfSpelerNaam = new TextField("");
    
    dpGeboorteDatum = new DatePicker();
    
    btnVoegSpelerToe = new Button();
    btnVoegSpelerToe.setText("Voeg toe.");
    
    
 
     this.getChildren().addAll(lblVoegSpelerToe, txfSpelerNaam, dpGeboorteDatum, btnVoegSpelerToe);
 
    btnVoegSpelerToe.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        String SpelerNaam = txfSpelerNaam.getText();
       Date geboorteDatum = Date.from(dpGeboorteDatum.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
       int index = counter-1;
       dc.geefDetails(SpelerNaam, geboorteDatum, index);
        //dc.addSpeler(SpelerNaam, geboorteDatum);      
        btnVoegSpelerToeOnAction(event);
        btnVoegSpelerToe.setVisible(false);
                    update();
        
        }
    });
     
 }
     
     
 

 void update(){
     txfSpelerNaam.setEditable(false);
     dpGeboorteDatum.setEditable(false);
 }
 
public void btnVoegSpelerToeOnAction(ActionEvent event){
txfSpelerNaam.setFocusTraversable(true);
}
}
