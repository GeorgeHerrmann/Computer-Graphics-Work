module com.georgster.csci4810 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.georgster.csci4810 to javafx.fxml;
    exports com.georgster.csci4810;
}
