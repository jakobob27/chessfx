module com.jackern {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires transitive javafx.graphics;

    opens com.jackern to javafx.fxml;

    exports com.jackern;
}