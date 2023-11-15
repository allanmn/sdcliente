package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Models.Point;
import com.example.sdcliente.Models.User;
import com.example.sdcliente.Receivers.EditUserReceiver;
import com.example.sdcliente.Receivers.RequestPointReceiver;
import com.example.sdcliente.Receivers.RequestUserReceiver;
import com.example.sdcliente.Senders.Data.EditPointData;
import com.example.sdcliente.Senders.Data.EditUserData;
import com.example.sdcliente.Senders.Data.RequestPointData;
import com.example.sdcliente.Senders.Data.RequestUserData;
import com.example.sdcliente.Senders.EditPointSender;
import com.example.sdcliente.Senders.EditUserSender;
import com.example.sdcliente.Senders.RequestPointSender;
import com.example.sdcliente.Senders.RequestUserSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

public class EditPointController {

    public ListPointsController controller;

    @FXML
    Button saveBtn;

    @FXML
    TextField nomeField;

    @FXML
    TextArea obsField;

    private long pointId;

    public void getData() {
        RequestPointData data = new RequestPointData(TokenService.getJwtToken(), this.pointId);

        RequestPointSender request = new RequestPointSender(data);

        String res = request.send();

        if (res != null) {
            try {
                RequestPointReceiver response = RequestPointReceiver.fromJson(res, RequestPointReceiver.class);

                if (response.getError()) {
                    HelperService.showErrorMessage(response.getMessage());
                } else {
                    setData(response.getData().getPoint());
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }
    }

    public void setData(Point point) {
        this.nomeField.setText(point.getName());
        this.obsField.setText(point.getObs());

        this.saveBtn.setDisable(false);
    }

    public void create() {
        this.saveBtn.setDisable(true);

        EditPointData data = new EditPointData(this.getPointId(), nomeField.getText(), this.obsField.getText(), TokenService.getJwtToken());

        EditPointSender sender = new EditPointSender(data);

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

    public long getPointId() {
        return pointId;
    }

    public void setPointId(long pointId) {
        this.pointId = pointId;
    }
}
