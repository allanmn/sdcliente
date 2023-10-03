package com.example.sdcliente;

import com.example.sdcliente.Controllers.ConnectDialogController;
import com.example.sdcliente.Controllers.InitialController;
import com.example.sdcliente.Models.Results.ConnectModalResult;
import com.example.sdcliente.Services.SocketService;
import com.example.sdcliente.Services.TokenService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static SocketService socketService = new SocketService();

    private final TokenService tokenService = new TokenService();

    private Stage primaryStage = null;

    private static Main instance;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("initial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("CONECTANDO...");
        primaryStage.setScene(scene);
        primaryStage.show();
        ((InitialController)fxmlLoader.getController()).setStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }

    public static ConnectModalResult openConnectDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("connect_dialog.fxml"));
            loader.setController(new ConnectDialogController());
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("CONECTANDO AO SERVIDOR...");
            dialog.getDialogPane().setContent(loader.load());
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

            // Wait for the dialog to be closed
            dialog.showAndWait();

            // Retrieve the input values from the controller
            ConnectDialogController controller = loader.getController();
            String ip = controller.getIp();
            String port = controller.getPort();

            return new ConnectModalResult(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void restart() throws IOException {
        primaryStage.close();

        this.start(primaryStage);
    }

    public static SocketService getSocketService() {
        return socketService;
    }

    public static Main getInstance() {
        return instance;
    }
}