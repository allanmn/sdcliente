package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Models.Point;
import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Models.User;
import com.example.sdcliente.Receivers.EditUserReceiver;
import com.example.sdcliente.Receivers.ListPointsReceiver;
import com.example.sdcliente.Receivers.RequestSegmentReceiver;
import com.example.sdcliente.Receivers.RequestUserReceiver;
import com.example.sdcliente.Senders.*;
import com.example.sdcliente.Senders.Data.*;
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
import java.util.Objects;

public class EditSegmentController {

    public ListSegmentsController controller;

    public long segmentId;

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

    public RadioButton radioBlocked;

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

        this.radioBlocked.selectedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) -> {
            if (isNowSelected) {
                this.radioBlocked.setText("Sim");
            } else {
                this.radioBlocked.setText("Não");
            }
        });

        this.selectOrigem.setItems(options);
        this.selectDestino.setItems(options);
    }

    public void getPoints() {
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
                    getData();
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        assert pointList != null;
        this.options.addAll(pointList);
    }

    public void getData() {
        RequestSegmentData data = new RequestSegmentData(TokenService.getJwtToken(), this.segmentId);

        RequestSegmentSender request = new RequestSegmentSender(data);

        String res = request.send();

        if (res != null) {
            try {
                RequestSegmentReceiver response = RequestSegmentReceiver.fromJson(res, RequestSegmentReceiver.class);

                if (response.getError()) {
                    HelperService.showErrorMessage(response.getMessage());
                } else {
                    setData(response.getData().getSegment());
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }
    }

    public void setData(Segment segment) {
        this.txtDistancia.setText(String.valueOf(segment.getDistancia()));
        this.txtDirecao.setText(segment.getDirecao());
        this.txtObs.setText(segment.getObs());
        this.selectDestino.getSelectionModel().select(segment.getPontoDestino());
        this.selectOrigem.getSelectionModel().select(segment.getPontoOrigem());
        this.radioBlocked.setSelected(segment.getBloqueado());

        this.saveBtn.setDisable(false);
    }

    public void create() {
        this.saveBtn.setDisable(true);

        try {
            Integer.parseInt(this.txtDistancia.getText());
        } catch (Exception ex) {
            HelperService.showErrorMessage("Distância deve ser inteiro.");
            return;
        }

        Segment segment = new Segment(this.selectedDestino, this.selectedOrigem, this.txtDirecao.getText(), Integer.parseInt(this.txtDistancia.getText()), this.txtObs.getText(), this.radioBlocked.isSelected());

        EditSegmentData data = new EditSegmentData(this.getSegmentId(), segment, TokenService.getJwtToken());

        EditSegmentSender sender = new EditSegmentSender(data);

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

    public long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(long segmentId) {
        this.segmentId = segmentId;
    }
}
