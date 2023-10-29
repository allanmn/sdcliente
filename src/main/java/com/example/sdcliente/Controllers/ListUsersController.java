package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Models.User;
import com.example.sdcliente.Receivers.CreateUserReceiver;
import com.example.sdcliente.Receivers.ListUsersReceiver;
import com.example.sdcliente.Receivers.RemoveUserReceiver;
import com.example.sdcliente.Senders.Data.ListUsersData;
import com.example.sdcliente.Senders.Data.RemoveUserData;
import com.example.sdcliente.Senders.ListUsersSender;
import com.example.sdcliente.Senders.RemoveUserSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ListUsersController {
    @FXML
    private TableView<User> userTableView;

    private ObservableList<User> userList;

    @FXML
    private Button removeBtn;

    @FXML
    private Button editBtn;

    private User selectedUser;

    @FXML
    public void initialize() {
        this.userTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.selectedUser = newSelection;
                this.setButtonsDisabled(false);
            } else {
                this.setButtonsDisabled(true);
            }
        });

        getData();
    }

    public void getData() {
        this.setButtonsDisabled(true);
        this.userList = FXCollections.observableArrayList();

        this.userTableView.setItems(this.userList);

        List<User> userList = null;

        ListUsersData data = new ListUsersData(TokenService.getJwtToken());

        ListUsersSender sender = new ListUsersSender(data);

        String response = sender.send();

        if (response != null) {
            try {
                ListUsersReceiver res = ListUsersReceiver.fromJson(response, ListUsersReceiver.class);

                if (res.getError()) {
                    HelperService.showErrorMessage(res.getMessage());
                } else {
                    userList = res.getData().getUsers();
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        // Populate the ListView with user information
        for (User user : userList) {
            this.userTableView.getItems().add(user);
        }
    }

    private void setButtonsDisabled(boolean isDisabled) {
        this.removeBtn.setDisable(isDisabled);
        this.editBtn.setDisable(isDisabled);
    }

    @FXML
    private void edit() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("edit-user.fxml"));

        try {
            Parent root = loader.load();

            EditUserController controller = loader.getController();

            controller.setUserId(this.selectedUser.getId());
            controller.controller = this;

            Scene scene = new Scene(root);
            stage.setTitle("Editar usuário");
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

        RemoveUserData data = new RemoveUserData(TokenService.getJwtToken(), this.selectedUser.getId());

        RemoveUserSender request = new RemoveUserSender(data);

        String res = request.send();

        if (res != null) {
            try {
                RemoveUserReceiver response = RemoveUserReceiver.fromJson(res, RemoveUserReceiver.class);

                if (response.getError()) {
                    HelperService.showErrorMessage(response.getMessage());
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(response.getMessage());

                    alert.showAndWait();

                    userList.remove(this.userTableView.getSelectionModel().getSelectedItem());
                }
            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        this.setButtonsDisabled(false);
    }
}