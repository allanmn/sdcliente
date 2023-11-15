package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Models.Point;
import com.example.sdcliente.Receivers.ListPointsReceiver;
import com.example.sdcliente.Receivers.RemovePointReceiver;
import com.example.sdcliente.Senders.Data.ListPointsData;
import com.example.sdcliente.Senders.Data.RemovePointData;
import com.example.sdcliente.Senders.ListPointsSender;
import com.example.sdcliente.Senders.RemovePointSender;
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

public class ListPointsController {
    @FXML
    private TableView<Point> pointTableView;

    private ObservableList<Point> points;

    @FXML
    private Button removeBtn;

    @FXML
    private Button editBtn;

    private Point selectedPoint;

    @FXML
    public void initialize() {
        this.pointTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.selectedPoint = newSelection;
                this.setButtonsDisabled(false);
            } else {
                this.setButtonsDisabled(true);
            }
        });

        getData();
    }

    public void getData() {
        this.setButtonsDisabled(true);
        this.points = FXCollections.observableArrayList();

        this.pointTableView.setItems(this.points);

        List<Point> pointList = null;

        ListPointsData data = new ListPointsData(TokenService.getJwtToken());

        ListPointsSender sender = new ListPointsSender(data);

        String response = sender.send();

        if (response != null) {
            try {
                ListPointsReceiver res = ListPointsReceiver.fromJson(response, ListPointsReceiver.class);

                if (res.getError()) {
                    HelperService.showErrorMessage(res.getMessage());
                } else {
                    pointList = res.getData().getPoints();
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        // Populate the ListView with user information
        assert pointList != null;
        for (Point point : pointList) {
            this.pointTableView.getItems().add(point);
        }
    }

    private void setButtonsDisabled(boolean isDisabled) {
        this.removeBtn.setDisable(isDisabled);
        this.editBtn.setDisable(isDisabled);
    }

    @FXML
    private void edit() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("edit-point.fxml"));

        try {
            Parent root = loader.load();

            EditPointController controller = loader.getController();

            controller.setPointId(this.selectedPoint.getId());
            controller.controller = this;

            Scene scene = new Scene(root);
            stage.setTitle("Editar ponto");
            stage.setScene(scene);

            stage.show();

            controller.getData();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    private void remove() {
        this.setButtonsDisabled(true);

        RemovePointData data = new RemovePointData(TokenService.getJwtToken(), this.selectedPoint.getId());

        RemovePointSender request = new RemovePointSender(data);

        String res = request.send();

        if (res != null) {
            try {
                RemovePointReceiver response = RemovePointReceiver.fromJson(res, RemovePointReceiver.class);

                if (response.getError()) {
                    HelperService.showErrorMessage(response.getMessage());
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(response.getMessage());

                    alert.showAndWait();

                    points.remove(this.pointTableView.getSelectionModel().getSelectedItem());
                }
            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        this.setButtonsDisabled(false);
    }
}