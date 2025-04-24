module org.cantinesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqljdbc4;
    requires java.desktop;


    opens org.cantinesystem to javafx.fxml;
    exports org.cantinesystem;
}