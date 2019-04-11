package main;

import domein.DomeinController;
import gui.AantalSpelersKeuzeScherm;
import gui.BeginScherm;
import gui.DobbelScherm;
import gui.SpelerNaamInputDetailScherm;
import gui.SpelernaamInvoerScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        DomeinController dc = new DomeinController();

        Scene scene = new Scene(new BeginScherm(dc));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Regenwormen");

        primaryStage.show();
    }
    
        public static void main(String... args)
    {
        Application.launch(StartUpGui.class, args);
    }
    
}
