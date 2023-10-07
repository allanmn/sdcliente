package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Models.Results.ConnectModalResult;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.example.sdcliente.Services.TokenService;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class InitialController {
    private Stage stage;

    public void initialize() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        pause.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                openDialog();
            }
        });

        pause.play();
    }

    public void openDialog() {
        Platform.runLater(() -> {
            ConnectModalResult result = Main.openConnectDialog();

            try {
                if (result != null && result.validate()) {
                    String ip = result.getIp();
                    String port = result.getPort();
                    try {
                        Main.getSocketService().connect(ip, port);

                        openLogin();
                    } catch (Exception ex) {
                        HelperService.showErrorMessage(ex.getMessage());
                        openDialog();
                    }
                } else {
                    openDialog();
                }
            } catch (ValidationException e) {
                openDialog();
                HelperService.showErrorMessage(e.getMessage());
            }
        });
    }

    private void openLogin() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("login.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Entre no sistema");
            stage.setScene(scene);
            stage.show();

            this.stage.close();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}