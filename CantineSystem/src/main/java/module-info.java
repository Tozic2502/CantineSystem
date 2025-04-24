module org.cantinesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.cantinesystem to javafx.fxml;
    exports org.cantinesystem;
}