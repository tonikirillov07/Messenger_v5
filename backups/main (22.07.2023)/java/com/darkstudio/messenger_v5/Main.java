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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main extends Application {
    static PropertiesClass propertiesClass;
    static ShowExceptions showExceptions;

    public Main(){
        try {
            propertiesClass = new PropertiesClass();
            showExceptions = new ShowExceptions();
        }catch (Exception e){
            Platform.runLater(() -> showExceptions.showException(e));
        }
    }

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartWindow/logInUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            scene.setFill(Color.TRANSPARENT);
            stage.setTitle(new PropertiesClass().getProperty("app_title"));
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("StartWindow/images/icon.png")).openStream()));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            Platform.runLater(() -> showExceptions.showException(ex));
        }
    }

    public static void main(String[] args) {
        try {
            CheckMinimumSysReq checkMinimumSysReq = new CheckMinimumSysReq();
            checkMinimumSysReq.init();

            if (!checkMinimumSysReq.systemIsMeetMinSysReq()) {
                if(Boolean.parseBoolean(new PropertiesClass().getProperty("show_alert_if_sys_req_didnt_meet"))) {
                    Platform.runLater(() -> {
                        try {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Предупреждение");

                            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(Objects.requireNonNull(Main.class.getResource("images/warning.png")).openStream()));
                            alert.setHeaderText("Ваша система не отвечает минимальным системным требованиям к " + new PropertiesClass().getProperty("app_title"));
                            alert.setContentText("Причина: " + checkMinimumSysReq.getReason() + ".\n Использование мессенджера на неподдерживаемом оборудовании" +
                                    " может привести к нестабильной работе. ");
                            alert.showAndWait();
                        } catch (Exception e) {
                            showExceptions.showException(e);
                        }
                    });
                }else System.err.println(checkMinimumSysReq.getReason());
            }
            launch();
        }catch (Exception ex){
            Platform.runLater(() -> showExceptions.showException(ex));
        }
    }
}