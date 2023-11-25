package com.darkstudio.messenger_v5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartWindow/logInUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            scene.setFill(Color.TRANSPARENT);
            stage.setTitle("Messenger v.5");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("StartWindow/images/icon.png")).openStream()));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);

            //scene.getRoot().getB
            stage.show();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString(), "Exception has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            CheckMinimumSysReq checkMinimumSysReq = new CheckMinimumSysReq();
            checkMinimumSysReq.init();

            if (!checkMinimumSysReq.systemIsMeetMinSysReq()) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Предупреждение");
                    alert.setHeaderText("Ваша система не отвечает минимальным системным требованиям к Messenger v.5");
                    alert.setContentText("Причина: " + checkMinimumSysReq.getReason() + ".\n Использование мессенджера на неподдерживаемом оборудовании" +
                            " может привести к нестабильной работе. ");
                    alert.showAndWait();
                });
            }
            launch();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString(), "Exception has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }
}