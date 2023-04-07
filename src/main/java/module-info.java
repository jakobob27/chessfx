module com.jackern {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.jackern to javafx.fxml;

    exports com.jackern;
}