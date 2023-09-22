module com.example.sdcliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.sdcliente to javafx.fxml;
    opens com.example.sdcliente.Controllers to javafx.fxml;
    exports com.example.sdcliente.Senders.Data to com.fasterxml.jackson.databind;
    exports com.example.sdcliente.Senders to com.fasterxml.jackson.databind;
    exports com.example.sdcliente;
}