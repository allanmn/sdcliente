package com.example.sdcliente.Controllers;

import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Receivers.LogoutReceiver;
import com.example.sdcliente.Senders.Data.LogoutData;
import com.example.sdcliente.Senders.LogoutSender;
import com.example.sdcliente.Services.TokenService;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public MenuItem logoutBtn;

    @FXML
    public void openCreateUser() {

    }

    @FXML
    public void openUsers() {

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