package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SpelerNaamInputDetailScherm extends HBox
{
     private Button btnVoegSpelerToe;
    private Label lblVoegSpelerToe;
    private TextField txfSpelerNaam;
    private DomeinController dc;
    
    
    public SpelerNaamInputDetailScherm(DomeinController dc, int aantalSpelers){
        this.dc = dc;
        
        buildGui(aantalSpelers);
        
    }
    
 public void buildGui(int aantalSpelers){
    this.setPadding(new Insets(10));    
    this.setSpacing(10);
      
    lblVoegSpelerToe = new Label();
    lblVoegSpelerToe.setText(String.format("Geef de naam in van speler"));
    
    txfSpelerNaam = new TextField("");
    
    
 
     this.getChildren().addAll(lblVoegSpelerToe, txfSpelerNaam);
     update();
     
    txfSpelerNaam.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String SpelerNaam = txfSpelerNaam.getText();
              dc.addSpeler(SpelerNaam);
              txfSpelerNaamOnAction(event);
                    update();
        }
    });
 }
     
     
 

 void update(){
     txfSpelerNaam.setText("");
 }
 
public void txfSpelerNaamOnAction(ActionEvent event){
    
}
}
