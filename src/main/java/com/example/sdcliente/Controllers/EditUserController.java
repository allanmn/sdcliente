package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Models.User;
import com.example.sdcliente.Receivers.CreateUserReceiver;
import com.example.sdcliente.Receivers.EditUserReceiver;
import com.example.sdcliente.Receivers.RequestUserReceiver;
import com.example.sdcliente.Senders.Data.EditUserData;
import com.example.sdcliente.Senders.Data.RequestUserData;
import com.example.sdcliente.Senders.Data.UserData;
import com.example.sdcliente.Senders.EditUserSender;
import com.example.sdcliente.Senders.RequestUserSender;
import com.example.sdcliente.Senders.UserSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

public class EditUserController {

    public ListUsersController controller;

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

    private long userId;

    private String type = "comum";

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

    public void getData() {
        RequestUserData data = new RequestUserData(TokenService.getJwtToken(), this.userId);

        RequestUserSender request = new RequestUserSender(data);

        String res = request.send();

        if (res != null) {
            try {
                RequestUserReceiver response = RequestUserReceiver.fromJson(res, RequestUserReceiver.class);

                if (response.getError()) {
                    HelperService.showErrorMessage(response.getMessage());
                } else {
                    setData(response.getData().getUser());
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }
    }

    public void setData(User user) {
        this.nomeField.setText(user.getName());
        this.emailField.setText(user.getEmail());
        if (Objects.equals(user.getType(), "admin")) {
            this.adm.setSelected(true);
        } else {
            this.comum.setSelected(true);
        }

        this.saveBtn.setDisable(false);
    }

    public void create() {
        this.saveBtn.setDisable(true);

        EditUserData data = new EditUserData(this.getUserId(), nomeField.getText(), type, emailField.getText(), senhaField.getText(), TokenService.getJwtToken());



        EditUserSender sender = new EditUserSender(data);

        String res = sender.send();

        if (res != null) {
            try {
                EditUserReceiver response = EditUserReceiver.fromJson(res, EditUserReceiver.class);

                if (response.getError()) {
                    HelperService.showErrorMessage(response.getMessage());
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(response.getMessage());

                    alert.showAndWait();

                    Stage stage = (Stage) saveBtn.getScene().getWindow();

                    stage.close();

                    controller.getData();
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        saveBtn.setDisable(false);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
