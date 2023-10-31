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

public class ClientMainMenuController {

    @FXML
    public MenuItem logoutBtn;

    @FXML
    public void logout() {
        boolean res = HelperService.showConfirmationDialog("Tem certeza?", "Tem certeza que deseja sair?");

        if (res) {
            LogoutSender request = new LogoutSender(new LogoutData(TokenService.getJwtToken()));

            try {
                LogoutReceiver response = LogoutReceiver.fromJson(request.send(), LogoutReceiver.class);

                if (!response.getError()) {
                    Main.getSocketService().close();

                    closeWindow();

                    Main.getInstance().restart();
                }
            } catch (IOException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }
    }

    public void closeWindow() {
        Stage stage = (Stage) logoutBtn.getParentPopup().getOwnerWindow();
        stage.close();
    }

    public void openEdit() {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("auto-edit-user.fxml"));

        try {
            Parent root = loader.load();

            AutoEditUserController controller = loader.getController();
            controller.controller = this;


            Scene scene = new Scene(root);
            stage.setTitle("Editar usuário");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }
}