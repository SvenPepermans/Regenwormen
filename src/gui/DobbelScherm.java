package gui;

import domein.DomeinController;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DobbelScherm extends HBox
{
    private DomeinController dc;
    private Button btnDobbelsteen;
    private InputStream input;
    private int spelerIndex = 0;
   
    

public DobbelScherm(DomeinController dc){
    
    this.dc = dc;
    buildGui();
}

public void buildGui(){
        this.setSpacing(5);
      dc.veranderVanSpeler(spelerIndex);
      dc.leegGekozenWaardenSpeler();
      dc.setTegel(null);    
      dc.zetAantalDobbelstenen(8);
    
    for (int aantalIG = 0; aantalIG < dc.geefAantalDobbelstenen(); aantalIG++) {
                    dc.aanmakendobbelsteen();
    }
    
    
    
    for(int index = 0; index < dc.geefDobbelsteenWaarden().size(); index++){
        String waarde;
          switch (dc.geefDobbelsteenWaarden().get(index)) {
              case "1":
                  input = //
                          getClass().getResourceAsStream("/images/dice1.png");
                  waarde = "1";
                  break;
              case "2":
                  input = //
                          getClass().getResourceAsStream("/images/dice2.png");
                  waarde = "2";
                  break;
              case "3":
                  input = //
                          getClass().getResourceAsStream("/images/dice3.png");
                  waarde = "3";
                  break;
              case "4":
                  input = //
                          getClass().getResourceAsStream("/images/dice4.png");
                  waarde = "4";
                  break;
              case "5":
                  input = //
                          getClass().getResourceAsStream("/images/dice5.png");
                  waarde = "5";
                  break;
              default:
                  input = //
                          getClass().getResourceAsStream("/images/dice6.png");
                  waarde = "Worm";
                  break;
          }
     
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
 
        btnDobbelsteen = new Button();
        btnDobbelsteen.setGraphic(imageView);
        btnDobbelsteen.setText(waarde);
     this.getChildren().add(btnDobbelsteen);
    }
    
    btnDobbelsteen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String keuze = btnDobbelsteen.getText();
                dc.addChoiceGUI(keuze);
              
            }
        });
    
}

private void btnDobbelsteenOnAction(ActionEvent event){
    
}
}
