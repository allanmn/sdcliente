package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Models.User;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.example.sdcliente.Receivers.*;
import com.example.sdcliente.Senders.Data.EditUserData;
import com.example.sdcliente.Senders.Data.RemoveSelfUserData;
import com.example.sdcliente.Senders.Data.RequestAutoUserData;
import com.example.sdcliente.Senders.Data.RequestUserData;
import com.example.sdcliente.Senders.EditUserSender;
import com.example.sdcliente.Senders.RemoveSelfUserSender;
import com.example.sdcliente.Senders.RequestAutoUserSender;
import com.example.sdcliente.Senders.RequestUserSender;
import com.example.sdcliente.Services.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class AutoEditUserController {

    public ClientMainMenuController controller;

    @FXML
    Button saveBtn;

    @FXML
    Button removeBtn;

    @FXML
    TextField nomeField;

    @FXML
    TextField emailField;

    @FXML
    PasswordField senhaField;

    public void initialize() {
        getData();
    }

    public void getData() {
        RequestAutoUserData data = new RequestAutoUserData(TokenService.getJwtToken());

        RequestAutoUserSender request = new RequestAutoUserSender(data);

        String res = request.send();

        if (res != null) {
            try {
                RequestAutoUserReceiver response = RequestAutoUserReceiver.fromJson(res, RequestAutoUserReceiver.class);

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
        this.saveBtn.setDisable(false);
        this.removeBtn.setDisable(false);
    }

    public void create() {
        this.saveBtn.setDisable(true);
        this.removeBtn.setDisable(true);

        saveBtn.setDisable(false);
        this.removeBtn.setDisable(false);
    }


    public void remove() {
        this.saveBtn.setDisable(true);
        this.removeBtn.setDisable(true);

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Confirme sua ação");
        dialog.setHeaderText("Informe o e-mail e a senha:");

        ButtonType loginButtonType = new ButtonType("OK");
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPrefWidth(300);

        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();

        grid.add(new Label("E-mail:"), 0, 0);
        grid.add(emailField, 1, 0);
        grid.add(new Label("Senha:"), 0, 1);
        grid.add(passwordField, 1, 1);

       dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(emailField.getText(), passwordField.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent()) {
            String email = result.get().getKey();
            String password = result.get().getValue();

            RemoveSelfUserData data = new RemoveSelfUserData(email, password, TokenService.getJwtToken());

            try {
                if (data.validate()) {
                    RemoveSelfUserSender request = new RemoveSelfUserSender(data);

                    String res = request.send();

                    if (res != null) {
                        try {
                            RemoveSelfUserReceiver response = RemoveSelfUserReceiver.fromJson(res, RemoveSelfUserReceiver.class);

                            if (response.getError()) {
                                HelperService.showErrorMessage(response.getMessage());
                            } else {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Sucesso");
                                alert.setHeaderText(response.getMessage());

                                alert.showAndWait();

                                Main.getSocketService().close();

                                Stage stage = (Stage) removeBtn.getScene().getWindow();
                                stage.close();

                                controller.closeWindow();

                                Main.getInstance().restart();
                            }
                        } catch (JsonProcessingException e) {
                            HelperService.showErrorMessage(e.getMessage());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } catch (ValidationException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }

        saveBtn.setDisable(false);
        this.removeBtn.setDisable(false);
    }
}
