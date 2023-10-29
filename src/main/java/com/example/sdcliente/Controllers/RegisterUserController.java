package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Receivers.CreateUserReceiver;
import com.example.sdcliente.Receivers.RegisterUserReceiver;
import com.example.sdcliente.Senders.Data.RegisterUserData;
import com.example.sdcliente.Senders.Data.UserData;
import com.example.sdcliente.Senders.RegisterUserSender;
import com.example.sdcliente.Senders.UserSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterUserController {

    @FXML
    Button saveBtn;

    @FXML
    TextField nomeField;

    @FXML
    TextField emailField;

    @FXML
    PasswordField senhaField;

    public void initialize() {
    }

    public void create() {
        saveBtn.setDisable(true);

        RegisterUserData data = new RegisterUserData(nomeField.getText(), emailField.getText(), senhaField.getText());

        RegisterUserSender sender = new RegisterUserSender(data);

        String res = sender.send();

        if (res != null) {
            try {
                RegisterUserReceiver response = RegisterUserReceiver.fromJson(res, RegisterUserReceiver.class);

                if (response.getError()) {
                    HelperService.showErrorMessage(response.getMessage());
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(response.getMessage());

                    alert.showAndWait();

                    Stage stage = (Stage) saveBtn.getScene().getWindow();

                    stage.close();
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        saveBtn.setDisable(false);
    }
}
