package gui;

import domein.DomeinController;
import java.io.InputStream;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class DobbelScherm extends GridPane
{
    private DomeinController dc;
    private Button btndobbelsteen;
    private InputStream input;
    

public DobbelScherm(DomeinController dc){
    
    this.dc = dc;
    buildGui();
}

public void buildGui(){
      
    dc.zetAantalDobbelstenen(8);
    for (int aantalIG = 0; aantalIG < dc.geefAantalDobbelstenen(); aantalIG++) {
                    dc.aanmakendobbelsteen();
    }
    
    
    
    for(int index = 1; index <= dc.geefDobbelsteenWaarden().size(); index++){
    if(dc.geefDobbelsteenWaarden().get(index).equals("1")){
       input = //
                getClass().getResourceAsStream("/images/dice1.png"); 
    } else if(dc.geefDobbelsteenWaarden().get(index).equals("2")){
         input = //
                getClass().getResourceAsStream("/images/dice2.png"); 
    
    }else if(dc.geefDobbelsteenWaarden().get(index).equals("3")){
         input = //
                getClass().getResourceAsStream("/images/dice3.png"); 
        
 }else if(dc.geefDobbelsteenWaarden().get(index).equals("4")){
         input = //
                getClass().getResourceAsStream("/images/dice4.png"); 
        
    }else if(dc.geefDobbelsteenWaarden().get(index).equals("5")){
         input = //
                getClass().getResourceAsStream("/images/dice5.png"); 
    }else{
         input = //
                getClass().getResourceAsStream("/images/dice6.png");
    }
     
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
 
        btndobbelsteen = new Button();
        btndobbelsteen.setGraphic(imageView);
     this.getChildren().add(btndobbelsteen);
    }
    
}
}
