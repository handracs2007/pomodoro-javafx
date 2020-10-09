package org.handra.tools.productivity;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    private boolean started = false;

    private int lastDurationMinute = 25;
    private int currentMinute = 25;
    private int currentSecond = 1;

    private Timer timer = new Timer ( );

    @FXML
    private Label timerLabel;

    @FXML
    private Button startStopButton;

    @FXML
    private ImageView pomodoroImage;

    private void resetPomodoro ( ) {
        // Cancel the timer.
        this.timer.cancel ( );

        // Enable back the duration button.
        this.pomodoroImage.setDisable ( false );

        // Reset the UI and internal data.
        switch ( this.lastDurationMinute ) {
            case 25 -> {
                this.timerLabel.setText ( "25:00" );
                this.currentMinute = 25;
                this.currentSecond = 1;
            }

            case 50 -> {
                this.timerLabel.setText ( "50:00" );
                this.currentMinute = 50;
                this.currentSecond = 1;
            }
        }

        // Reset the button text.
        this.startStopButton.setText ( "Start" );
    }

    @FXML
    private void startStopPomodoro ( ActionEvent event ) {
        if ( started ) {
            // Pomodoro is started. Let's stop it.
            this.resetPomodoro ( );
        } else {
            // Let's start the pomodoro.
            this.pomodoroImage.setDisable ( true );
            this.startStopButton.setText ( "Stop" );

            // Now, let's start the timer.
            this.timer = new Timer ( );
            this.timer.scheduleAtFixedRate ( new TimerTask ( ) {

                @Override
                public void run ( ) {

                    Platform.runLater ( ( ) -> {
                        // Reduce 1 second.
                        Controller.this.currentSecond--;

                        if ( Controller.this.currentSecond < 0 ) {
                            // Once second reaches 0, reduce the minute.
                            Controller.this.currentSecond = 59;
                            Controller.this.currentMinute--;
                        }

                        if ( Controller.this.currentMinute == 0 && Controller.this.currentSecond == 0 ) {
                            // Time is up. Reset the pomodoro.
                            Controller.this.resetPomodoro ( );

                            // Let's bring the window to the front.
                            Node source = ( Node ) event.getSource ( );
                            Stage stage = ( Stage ) source.getScene ( ).getWindow ( );
                            stage.toFront ( );

                            // Play audio.
                            String yooURI = "file://" + System.getProperty ( "user.dir" ) + File.separator + "yoo.ogg";
                            Media media = new Media ( yooURI );
                            MediaPlayer mediaPlayer = new MediaPlayer ( media );
                            mediaPlayer.setAutoPlay ( true );

                            // Reset the status flag.
                            Controller.this.started = false;

                            return;
                        }

                        Controller.this.timerLabel.setText ( String.format ( "%02d:%02d" , Controller.this.currentMinute , Controller.this.currentSecond ) );
                    } );
                }
            } , 0 , 1000 );
        }

        this.started = !this.started;
    }

    @FXML
    public void adjustDuration ( ) {
        switch ( this.lastDurationMinute ) {
            case 25 -> {
                this.timerLabel.setText ( "50:00" );
                this.lastDurationMinute = 50;
            }

            case 50 -> {
                this.timerLabel.setText ( "25:00" );
                this.lastDurationMinute = 25;
            }
        }

        this.currentMinute = this.lastDurationMinute;
    }
}
