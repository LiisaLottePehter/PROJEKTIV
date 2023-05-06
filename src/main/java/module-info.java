module com.example.projektiv {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projektiv to javafx.fxml;
    exports com.example.projektiv;
}