package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Models.User;
import com.example.sdcliente.Receivers.ListSegmentsReceiver;
import com.example.sdcliente.Receivers.ListUsersReceiver;
import com.example.sdcliente.Receivers.RemoveSegmentReceiver;
import com.example.sdcliente.Receivers.RemoveUserReceiver;
import com.example.sdcliente.Senders.Data.ListSegmentsData;
import com.example.sdcliente.Senders.Data.ListUsersData;
import com.example.sdcliente.Senders.Data.RemoveSegmentData;
import com.example.sdcliente.Senders.Data.RemoveUserData;
import com.example.sdcliente.Senders.ListSegmentsSender;
import com.example.sdcliente.Senders.ListUsersSender;
import com.example.sdcliente.Senders.RemoveSegmentSender;
import com.example.sdcliente.Senders.RemoveUserSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListSegmentsController {
    @FXML
    private TableView<Segment> segmentTableView;

    private ObservableList<Segment> segmentsList;

    @FXML
    private Button removeBtn;

    @FXML
    private Button editBtn;

    private Segment selectedSegment;

    @FXML
    public void initialize() {
        this.segmentTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.selectedSegment = newSelection;
                this.setButtonsDisabled(false);
            } else {
                this.setButtonsDisabled(true);
            }
        });

        getData();
    }

    public void getData() {
        this.setButtonsDisabled(true);
        this.segmentsList = FXCollections.observableArrayList();

        this.segmentTableView.setItems(this.segmentsList);

        List<Segment> segmentsList = null;

        ListSegmentsData data = new ListSegmentsData(TokenService.getJwtToken());

        ListSegmentsSender sender = new ListSegmentsSender(data);

        String response = sender.send();

        if (response != null) {
            try {
                ListSegmentsReceiver res = ListSegmentsReceiver.fromJson(response, ListSegmentsReceiver.class);

                if (res.getError()) {
                    HelperService.showErrorMessage(res.getMessage());
                } else {
                    segmentsList = res.getData().getSegments();
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        assert segmentsList != null;
        for (Segment segment : segmentsList) {
            this.segmentTableView.getItems().add(segment);
        }
    }

    private void setButtonsDisabled(boolean isDisabled) {
        this.removeBtn.setDisable(isDisabled);
        this.editBtn.setDisable(isDisabled);
    }

    @FXML
    private void edit() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("edit-segment.fxml"));

        try {
            Parent root = loader.load();

            EditSegmentController controller = loader.getController();

            controller.setSegmentId(this.selectedSegment.getId());
            controller.controller = this;

            Scene scene = new Scene(root);
            stage.setTitle("Editar segmento");
            stage.setScene(scene);

            stage.show();

            controller.getPoints();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    private void remove() {
        this.setButtonsDisabled(true);

        RemoveSegmentData data = new RemoveSegmentData(TokenService.getJwtToken(), this.selectedSegment.getId());

        RemoveSegmentSender request = new RemoveSegmentSender(data);

        String res = request.send();

        if (res != null) {
            try {
                RemoveSegmentReceiver response = RemoveSegmentReceiver.fromJson(res, RemoveSegmentReceiver.class);

                if (response.getError()) {
                    HelperService.showErrorMessage(response.getMessage());
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(response.getMessage());

                    alert.showAndWait();

                    segmentsList.remove(this.segmentTableView.getSelectionModel().getSelectedItem());
                }
            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        this.setButtonsDisabled(false);
    }
}