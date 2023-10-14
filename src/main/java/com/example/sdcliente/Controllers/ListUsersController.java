package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Models.User;
import com.example.sdcliente.Receivers.ListUsersReceiver;
import com.example.sdcliente.Receivers.LoginReceiver;
import com.example.sdcliente.Receivers.LogoutReceiver;
import com.example.sdcliente.Senders.Data.ListUsersData;
import com.example.sdcliente.Senders.Data.LoginData;
import com.example.sdcliente.Senders.Data.LogoutData;
import com.example.sdcliente.Senders.ListUsersSender;
import com.example.sdcliente.Senders.LoginSender;
import com.example.sdcliente.Senders.LogoutSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ListUsersController {
    @FXML
    private ListView<HBox> userListView;

    @FXML
    public void initialize() {
        // Fetch user data from the database
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
                    userList = res.getData().getUsuarios();

                    System.out.println(userList);
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        // Populate the ListView with user information
        for (User user : userList) {
            HBox userRow = createUserRow(user);
            userListView.getItems().add(userRow);
        }
    }
    private HBox createUserRow(User user) {
        Label nameLabel = new Label("Name: " + user.getNome());
        Label emailLabel = new Label("Email: " + user.getEmail());
        Label typeLabel = new Label("Type: " + (Objects.equals(user.getTipo(), "admin") ? "Admin" : "Common"));

        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");

        // Set actions for delete and update buttons
        deleteButton.setOnAction(event -> onDeleteButtonClicked(user));
        updateButton.setOnAction(event -> onUpdateButtonClicked(user));

        HBox userRow = new HBox(nameLabel, emailLabel, typeLabel, deleteButton, updateButton);
        userRow.setSpacing(10);

        return userRow;
    }

    private void onDeleteButtonClicked(User user) {
        // Implement delete action here based on the user
    }

    private void onUpdateButtonClicked(User user) {
        // Implement update action here based on the user
    }
}