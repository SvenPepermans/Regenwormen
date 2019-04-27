package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;



public class BeginScherm extends VBox {
    
    private HoofdScherm hoofdScherm;
    private Label lblWelkom;
    private Image frontImage;
    
    public BeginScherm(HoofdScherm hoofdScherm) {
        this.hoofdScherm = hoofdScherm;
        buildGui();
    }
    
    public void buildGui() {
        this.setPadding(new Insets(20));
 
        lblWelkom = new Label();
        lblWelkom.setText("Welkom in het spel Regenwormen, klik op de knop om verder te gaan.");
        lblWelkom.setAlignment(Pos.TOP_CENTER);
        
        final ImageView selectedImage = new ImageView();
        frontImage = new Image(BeginScherm.class.getResourceAsStream("/images/front.jpg"));
        selectedImage.setImage(frontImage);
       
        
        BeginKnop bk = new BeginKnop(this,hoofdScherm);
        
        this.getChildren().addAll(lblWelkom, selectedImage, bk);
      
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
