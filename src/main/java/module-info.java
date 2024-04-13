module com.cashcash {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;

    opens com.cashcash to javafx.fxml;
    exports com.cashcash;
}
