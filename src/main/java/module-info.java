module com.cashcash {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;
    requires spring.security.crypto;
    requires itextpdf;

    opens com.cashcash to javafx.fxml;
    exports com.cashcash;
}
