module com.example.bomberman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens GameRunner to javafx.fxml;
    exports GameRunner;
}