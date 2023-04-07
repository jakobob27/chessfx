module com.jackern {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.jackern to javafx.fxml;
    exports com.jackern;
}
