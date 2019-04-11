package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
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

        
    }

    private void btnGaVerderOnAction(ActionEvent event){
       dc.vulTegelRij();
        gaVerder();
    }
    
    public void gaVerder(){
        Stage stage = (Stage)this.getScene().getWindow();
       
       double breedte = this.getScene().getWidth();
       double hoogte = this.getScene().getHeight();
       stage.setTitle("Dobbelen");
       Scene scene = new Scene(new TegelScherm(this.dc), breedte, hoogte);
       stage.setScene(scene);   
    }
}
