package com.example.sdcliente.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ConnectDialogController {

    public TextField inputIP;
    public TextField inputPort;

    public void connect(ActionEvent actionEvent) {
        System.out.println("aa");
    }

    public String getIp() {
        return this.inputIP.getText();
    }

    public String getPort() {
        return this.inputPort.getText();
    }
}