module org.cantinesystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.cantinesystem to javafx.fxml;
    exports org.cantinesystem;
}