module com.example.bomberman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.bomberman to javafx.fxml;
    exports com.example.bomberman;
}