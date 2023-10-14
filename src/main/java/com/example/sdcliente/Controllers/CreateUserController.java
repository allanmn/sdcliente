package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Receivers.CreateUserReceiver;
import com.example.sdcliente.Receivers.LoginReceiver;
import com.example.sdcliente.Senders.Data.UserData;
import com.example.sdcliente.Senders.UserSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreateUserController {

    @FXML
    Button saveBtn;

    @FXML
    TextField nomeField;

    @FXML
    TextField emailField;

    @FXML
    PasswordField senhaField;

    @FXML
    RadioButton adm;
    @FXML
    RadioButton comum;

    String type = "comum";

    public void initialize() {
        // Agrupamento dos radio buttons para permitir seleção única
        ToggleGroup toggleGroup = new ToggleGroup();
        adm.setToggleGroup(toggleGroup);
        comum.setToggleGroup(toggleGroup);

        // Evento para imprimir o valor selecionado
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                type = ((RadioButton) newValue).getUserData().toString();
            }
        });
    }

    public void create() {
        saveBtn.setDisable(true);

        UserData data = new UserData(nomeField.getText(), type, emailField.getText(), senhaField.getText(), TokenService.getJwtToken());

        UserSender sender = new UserSender(data);

        String res = sender.send();

        if (res != null) {
            try {
                CreateUserReceiver response = CreateUserReceiver.fromJson(res, CreateUserReceiver.class);

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
