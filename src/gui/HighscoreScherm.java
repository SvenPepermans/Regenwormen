/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author michi
 */
public class HighscoreScherm extends BorderPane
{
     private HoofdScherm hoofdScherm;
    private DomeinController dc;
    private ArrayList<String> Namen;
    private ArrayList<Integer> score;
    private Button btnterug;
    
    public HighscoreScherm(HoofdScherm hoofdScherm, DomeinController dc){
        this.hoofdScherm = hoofdScherm;
        this.dc = dc;
        buildGui();
    }
    
    public void buildGui()
    {
        VBox vb1 = new VBox();
        VBox vb2 = new VBox();
        GridPane gridMid = new GridPane();
        Namen = dc.geefnamen();
        score = dc.geefscore();
        btnterug = new  Button("terug");
        btnterug.setVisible(true);
        btnterug.getStyleClass().add("buttons");
        
        for (int i = 0; i < Namen.size(); i++) 
        {
            Label naamLabel = new Label(Namen.get(i) + ":");
            naamLabel.getStyleClass().add("Tekst");
            vb1.getChildren().add(naamLabel);
        }
        for (int i = 0; i < score.size(); i++) {
            Label scoreLabel = new Label(score.get(i).toString());
            scoreLabel.getStyleClass().add("Tekst");
            vb2.getChildren().add(scoreLabel);
        }
        
        gridMid.addRow(4, vb1);
        gridMid.addRow(4, vb2);
        gridMid.addRow(5, btnterug);
        gridMid.setAlignment(Pos.CENTER);
        gridMid.setGridLinesVisible(true);
        setCenter(gridMid);
        
        btnterug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                
                hoofdScherm.verwijderHighscoreScherm();

            }
        });
        
    }
}
