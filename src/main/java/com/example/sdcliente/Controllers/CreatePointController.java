package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Receivers.CreatePointReceiver;
import com.example.sdcliente.Receivers.CreateUserReceiver;
import com.example.sdcliente.Senders.Data.PointData;
import com.example.sdcliente.Senders.PointSender;
import com.example.sdcliente.Senders.UserSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreatePointController {

    @FXML
    Button saveBtn;

    @FXML
    TextField nomeField;

    @FXML
    TextArea obsField;

    public void initialize() {
    }

    public void create() {
        saveBtn.setDisable(true);

        PointData data = new PointData(nomeField.getText(), obsField.getText(), TokenService.getJwtToken());

        PointSender sender = new PointSender(data);

        String res = sender.send();

        if (res != null) {
            try {
                CreatePointReceiver response = CreatePointReceiver.fromJson(res, CreatePointReceiver.class);

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
