module com.example.prog_j {
    requires javafx.controls;
    requires javafx.fxml;
    requires exp4j;
    requires json.simple;
    requires java.xml;


    opens com.example.prog_j to javafx.fxml;
    exports com.example.prog_j;
}