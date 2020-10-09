package org.handra.tools.productivity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start ( Stage primaryStage ) throws Exception {
        URL fxmlUrl = new URL ( "file://" + System.getProperty ( "user.dir" ) + File.separator + "pomodoro.fxml" );
        Parent root = FXMLLoader.load ( fxmlUrl );

        primaryStage.setTitle ( "Pomodoro" );
        primaryStage.setScene ( new Scene ( root , 280 , 70 ) );
        primaryStage.show ( );
    }

    public static void main ( String[] args ) {
        launch ( args );
    }
}
