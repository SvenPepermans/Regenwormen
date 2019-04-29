package gui;

import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;



public class BeginScherm extends GridPane {
    
    private HoofdScherm hoofdScherm;
    private Text txtWelkom, txtSpelen;
    private Button btnVerder;
  
    
    public BeginScherm(HoofdScherm hoofdScherm) {
        this.hoofdScherm = hoofdScherm;
        buildGui();
    }
    
    public void buildGui() {
        
      GridPane grid = new GridPane();
      grid = this;
        
        txtWelkom = new Text("Regenwormen");
        txtWelkom.setStyle("-fx-font-size:48;");
       
        btnVerder = new Button();
        btnVerder.setId("groteKnop");     
        btnVerder.setText("Ga verder");
        
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().add(colConstraints);
        grid.setAlignment(Pos.CENTER);
        grid.add(txtWelkom, 0,0);
        grid.add(btnVerder, 0, 1);    
        //this.setVgap(200); 
        grid.setGridLinesVisible(true);
        

        btnVerder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                btnVerderOnAction(arg0);
            }
        });
    }
    
    private void btnVerderOnAction(ActionEvent event) {
        hoofdScherm.toonSpelersGegevensScherm();
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
