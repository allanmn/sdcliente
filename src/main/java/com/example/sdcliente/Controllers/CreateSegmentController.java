package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Models.Point;
import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Receivers.CreatePointReceiver;
import com.example.sdcliente.Receivers.CreateSegmentReceiver;
import com.example.sdcliente.Receivers.ListPointsReceiver;
import com.example.sdcliente.Senders.Data.ListPointsData;
import com.example.sdcliente.Senders.Data.SegmentData;
import com.example.sdcliente.Senders.ListPointsSender;
import com.example.sdcliente.Senders.SegmentSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;

public class CreateSegmentController {

    public TextField txtDistancia;
    public TextArea txtObs;
    public ChoiceBox<Point> selectDestino;

    public ChoiceBox<Point> selectOrigem;
    @FXML
    Button saveBtn;

    @FXML
    TextField txtDirecao;

    Point selectedDestino;

    Point selectedOrigem;

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
        });
        this.selectOrigem.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.selectedOrigem = newSelection;
        });
        this.selectOrigem.setItems(options);
        this.selectDestino.setItems(options);

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
    }

    public void create() {
        saveBtn.setDisable(true);

        try {
            Integer.parseInt(this.txtDistancia.getText());
        } catch (Exception ex) {
            HelperService.showErrorMessage("Distância deve ser inteiro.");
            return;
        }

        Segment segmento = new Segment(this.selectedDestino, this.selectedOrigem, this.txtDirecao.getText(), Integer.parseInt(this.txtDistancia.getText()), this.txtObs.getText());

        SegmentData senderData = new SegmentData(segmento, TokenService.getJwtToken());

        SegmentSender sender = new SegmentSender(senderData);

        String res = sender.send();

        if (res != null) {
            try {
                CreateSegmentReceiver response = CreateSegmentReceiver.fromJson(res, CreateSegmentReceiver.class);

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
