package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Receivers.LogoutReceiver;
import com.example.sdcliente.Senders.Data.LogoutData;
import com.example.sdcliente.Senders.LogoutSender;
import com.example.sdcliente.Services.TokenService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public MenuItem logoutBtn;

    @FXML
    public void openCreateUser() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("create-user.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Criar usuário");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    public void openCreatePoint() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("create-point.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Criar Ponto");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    public void openPoints() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("list-points.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Listar pontos");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    public void openCreateSegment() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("create-segment.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Criar Segmento");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    public void openSegments() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("list-segments.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Listar segmentos");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    public void openUsers() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("list-users.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Listar usuários");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    public void logout() {
        boolean res = HelperService.showConfirmationDialog("Tem certeza?", "Tem certeza que deseja sair?");

        if (res) {
            LogoutSender request = new LogoutSender(new LogoutData(TokenService.getJwtToken()));

            try {
                LogoutReceiver response = LogoutReceiver.fromJson(request.send(), LogoutReceiver.class);

                if (!response.getError()) {
                    Main.getSocketService().close();

                    Stage stage = (Stage) logoutBtn.getParentPopup().getOwnerWindow();
                    stage.close();

                    Main.getInstance().restart();
                }
            } catch (IOException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }
    }
}