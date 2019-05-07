/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Windows 10
 */
public class HoofdScherm extends StackPane {

    public static DomeinController dc;

    private BeginScherm beginScherm = new BeginScherm(this);
    private SpelersGegevensScherm spelersGegevensScherm;
    private DobbelScherm dobbelScherm;
    private WinnaarScherm winnaarScherm;

    public HoofdScherm(DomeinController dc) {
        this.dc = dc;
        this.spelersGegevensScherm = new SpelersGegevensScherm(this, dc);
        //setSpacing(10);
       // getChildren().add(new Label("HoofdScherm"));
       this.setAlignment(Pos.CENTER);
        getChildren().add(beginScherm);
        getStyleClass().add("achtergrond");
        

    }

    public void toonSpelersGegevensScherm() {
        getChildren().remove(beginScherm);
        getChildren().add(spelersGegevensScherm);
    }

    
    public void toonDobbelScherm(){
        this.dobbelScherm = new DobbelScherm(this,dc);
        getChildren().remove(spelersGegevensScherm);
        getChildren().add(dobbelScherm);
    }
    
    public void toonWinnaarScherm(){
        this.winnaarScherm = new WinnaarScherm(this, dc);
        getChildren().remove(dobbelScherm);
        getChildren().add(winnaarScherm);
    }
}
