package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Models.Point;
import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Receivers.CreateSegmentReceiver;
import com.example.sdcliente.Receivers.ListPointsReceiver;
import com.example.sdcliente.Receivers.RequestRouteReceiver;
import com.example.sdcliente.Senders.Data.ListPointsData;
import com.example.sdcliente.Senders.Data.RouteData;
import com.example.sdcliente.Senders.Data.SegmentData;
import com.example.sdcliente.Senders.ListPointsSender;
import com.example.sdcliente.Senders.RouteSender;
import com.example.sdcliente.Senders.SegmentSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;

public class CreateRouteController {

    public ChoiceBox<Point> selectDestino;

    public ChoiceBox<Point> selectOrigem;

    @FXML
    Button saveBtn;

    Point selectedDestino;

    Point selectedOrigem;

    ObservableList<Point> optionsOrigem = FXCollections.observableArrayList();

    ObservableList<Point> optionsDestino = FXCollections.observableArrayList();

    ObservableList<Point> options = FXCollections.observableArrayList();

    public void initialize() {
        selectDestino.setConverter(new StringConverter<Point>() {
            @Override
            public String toString(Point object) {
                return object == null ? null : object.getName();
            }

            @Override
            public Point fromString(String string) {
                // You might need this method if you want to convert a string back to your object
                return null;
            }
        });

        selectOrigem.setConverter(new StringConverter<Point>() {
            @Override
            public String toString(Point object) {
                return object == null ? null : object.getName();
            }

            @Override
            public Point fromString(String string) {
                // You might need this method if you want to convert a string back to your object
                return null;
            }
        });

        this.selectDestino.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.selectedDestino = newSelection;
            this.optionsOrigem.remove(newSelection);
        });
        this.selectOrigem.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.selectedOrigem = newSelection;
            this.optionsDestino.remove(newSelection);
        });
        this.selectOrigem.setItems(optionsOrigem);
        this.selectDestino.setItems(optionsDestino);

        getPoints();
    }

    private void getPoints() {
        List<Point> pointList = null;

        ListPointsData senderData = new ListPointsData(TokenService.getJwtToken());

        ListPointsSender sender = new ListPointsSender(senderData);

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

        assert pointList != null;
        this.options.addAll(pointList);

        this.optionsOrigem.addAll(this.options);

        this.optionsDestino.addAll(this.options);
    }

    public void create() {
        saveBtn.setDisable(true);

        RouteData senderData = new RouteData(this.selectedOrigem, this.selectedDestino);

        RouteSender sender = new RouteSender(senderData);

        String res = sender.send();

        if (res != null) {
            try {
                RequestRouteReceiver response = RequestRouteReceiver.fromJson(res, RequestRouteReceiver.class);

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
