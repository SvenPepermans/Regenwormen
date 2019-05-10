package gui;

import domein.DomeinController;
import static gui.HoofdScherm.dc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;




public class BeginScherm extends GridPane {
    
    private HoofdScherm hoofdScherm;
    private Button btnVerder, btnLaden;
  
    
    public BeginScherm(HoofdScherm hoofdScherm,DomeinController dc) {
        this.hoofdScherm = hoofdScherm;
        buildGui();
    }
    
    public void buildGui() {
        
      
      GridPane grid = new GridPane();
      grid = this;
        
       
        btnVerder = new Button();
        btnVerder.setId("groteKnop");     
        btnVerder.setText("Begin");
        btnVerder.requestFocus();
        btnLaden = new Button();
        btnLaden.setId("groteKnop");
        btnLaden.setText("Laad vorig spel");
        
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().add(colConstraints);
        grid.setAlignment(Pos.CENTER);
        grid.add(btnVerder, 0, 1);
        grid.add(btnLaden, 1, 1);
 

        

        btnVerder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnVerderOnAction(arg0);
            }
        });
                btnLaden.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    dc.Laden();
                    btnLadenOnAction(arg0);
                } catch (Exception e) {
                    Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
                    ErrorAlert.setContentText("Geen vorig spel om te laden.");
                    ErrorAlert.showAndWait();
                }
                
                
                
            }
        });
    }
    
    private void btnVerderOnAction(ActionEvent event) {
        hoofdScherm.toonSpelersGegevensScherm();
    }
        private void btnLadenOnAction(ActionEvent event) {
            
        hoofdScherm.toonDobbelScherm();
    }
}
    /* public void gaVerder(){
        Stage stage = (Stage)this.getScene().getWindow();
       
       double breedte = this.getScene().getWidth();
       double hoogte = this.getScene().getHeight();
       stage.setTitle("Aantal spelers kiezen");
       Scene scene = new Scene(new AantalSpelersKeuzeScherm(this.dc), breedte, hoogte);
       stage.setScene(scene);
    }

   
} */
