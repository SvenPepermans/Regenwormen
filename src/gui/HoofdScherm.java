/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Windows 10
 */
public class HoofdScherm extends VBox {

    public static DomeinController dc;

    private BeginScherm beginScherm = new BeginScherm(this);
    private AantalSpelersKeuzeScherm aantalSpelersScherm;
    private SpelernaamInvoerScherm SpelerInvoerScherm;
    private DobbelScherm dobbelScherm;

    public HoofdScherm(DomeinController dc) {
        this.dc = dc;
        this.aantalSpelersScherm = new AantalSpelersKeuzeScherm(this, dc);
        setSpacing(10);
        getChildren().add(new Label("HoofdScherm"));
        getChildren().add(beginScherm);

    }

    public void toonAantalSpelersKeuzeScherm() {
        getChildren().remove(beginScherm);
        getChildren().add(aantalSpelersScherm);
    }

    public void toonSpelerInvoerScherm() {
        this.SpelerInvoerScherm = new SpelernaamInvoerScherm(this, dc);
        getChildren().remove(aantalSpelersScherm);
        getChildren().add(SpelerInvoerScherm);

    }
    public void toonDobbelScherm(){
        this.dobbelScherm = new DobbelScherm(this,dc);
        getChildren().remove(SpelerInvoerScherm);
        getChildren().add(dobbelScherm);
    }
}
