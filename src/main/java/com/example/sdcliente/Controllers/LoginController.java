package com.example.sdcliente.Controllers;

import com.example.sdcliente.Senders.Data.LoginData;
import com.example.sdcliente.Senders.LoginSender;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    TextField txtLogin;

    @FXML
    PasswordField txtPassword;

    public void initialize() {
    }

    @FXML
    public void login() {
        LoginData data = new LoginData(txtLogin.getText(), txtPassword.getText());

        LoginSender sender = new LoginSender(data);

        sender.send();
    }
}