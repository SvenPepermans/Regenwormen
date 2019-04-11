package gui;

import domein.DomeinController;
import javafx.scene.layout.HBox;

public class TegelScherm extends HBox
{
    private DomeinController dc;
    
public TegelScherm(DomeinController dc){
    this.dc = dc;
    
    buildGui();
}

public void buildGui(){
    
}
}
