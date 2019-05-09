/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 *
 * @author Windows 10
 */
public class WinnaarScherm extends BorderPane {

    private HoofdScherm hoofdScherm;
    private DomeinController dc;
    private Label lblTopLabel, lblCenLabel;
    private String winnaar;
    private int score;
    
    public WinnaarScherm(HoofdScherm hoofdScherm, DomeinController dc){
        this.hoofdScherm = hoofdScherm;
        this.dc = dc;
        buildGui();
    }
    
    public void buildGui(){
        this.getStyleClass().add("WinnaarAchtergrond");
        this.winnaar = dc.getWinnaar();
        this.score = dc.getHoogsteScore();
        lblCenLabel = new Label(String.format("De winnaar is: %s met een score van %s worm%s", winnaar, score, score>1?"en":""));
        lblCenLabel.getStyleClass().add("Winnaar");
        setCenter(lblCenLabel);
    }

    
}
