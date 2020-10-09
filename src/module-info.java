module pomodoro {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports org.handra.tools.productivity;
    opens org.handra.tools.productivity;
}