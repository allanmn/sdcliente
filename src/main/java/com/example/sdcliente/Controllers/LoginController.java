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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField txtLogin;

    @FXML
    PasswordField txtPassword;

    @FXML
    Button btnRegister;

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

                if (res.getError()) {
                    HelperService.showErrorMessage(res.getMessage());
                } else {
                    TokenService.saveJwtToken(res.getData().getToken());

                    openMain();
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }
    }

    private void openMain() {
        Stage stage = new Stage();

        String token = TokenService.getJwtToken();

        try {
            FXMLLoader loader = null;
            if (TokenService.isAdmin(token)) {
                loader = new FXMLLoader(Main.class.getResource("main-menu.fxml"));
            } else {
                loader = new FXMLLoader(Main.class.getResource("client-main-menu.fxml"));
            }

            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Main");
            stage.setScene(scene);

            Stage old_stage = (Stage) this.txtLogin.getScene().getWindow();
            old_stage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    public void openRegister() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("register-user.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Registrar-se");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }
}