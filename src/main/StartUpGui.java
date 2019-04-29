package main;

import domein.DomeinController;
import gui.SpelersGegevensScherm;
import gui.BeginScherm;
import gui.DobbelScherm;
import gui.HoofdScherm;
import gui.SpelerNaamInputDetailScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        DomeinController dc = new DomeinController();

        Scene scene = new Scene(new HoofdScherm(dc), Double.MAX_VALUE, Double.MAX_VALUE);
        scene.getStylesheets().add("/css/Style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Regenwormen");

        primaryStage.show();
    }
    
        public static void main(String... args)
    {
        Application.launch(StartUpGui.class, args);
    }
    
}
