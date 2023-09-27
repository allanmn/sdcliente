package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Receivers.LoginReceiver;
import com.example.sdcliente.Senders.Data.LoginData;
import com.example.sdcliente.Senders.LoginSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField txtLogin;

    @FXML
    PasswordField txtPassword;

    public void initialize() {
    }

    @FXML
    public void login()  {
        LoginData data = new LoginData(txtLogin.getText(), txtPassword.getText());

        LoginSender sender = new LoginSender(data);

        String response = sender.send();

        if (response != null) {
            try {
                LoginReceiver res = LoginReceiver.fromJson(response, LoginReceiver.class);

                TokenService.saveJwtToken(res.getData().getToken());


            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }
    }

    private void openMain() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Main");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}