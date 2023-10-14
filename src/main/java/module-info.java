module com.example.sdcliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.codec;
    requires jjwt.api;


    opens com.example.sdcliente to javafx.fxml;
    opens com.example.sdcliente.Controllers to javafx.fxml;
    exports com.example.sdcliente.Senders.Data to com.fasterxml.jackson.databind;
    exports com.example.sdcliente.Senders to com.fasterxml.jackson.databind;
    exports com.example.sdcliente.Receivers.Data to com.fasterxml.jackson.databind;
    exports com.example.sdcliente.Receivers to com.fasterxml.jackson.databind;
    exports com.example.sdcliente.Models to com.fasterxml.jackson.databind;
    exports com.example.sdcliente;
}