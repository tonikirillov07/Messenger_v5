package com.darkstudio.messenger_v5;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Objects;

public class ShowExceptions {
    Time time = new Time();

    public void showException(@NotNull Exception exception) {
        exception.printStackTrace();

        try {
            exception.printStackTrace(new PrintStream(new File("errorLogs/"+getFileName())));

            new PlaySounds(PlaySounds.Constants.ERROR_SOUND, 6).play();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception has occurred");
            alert.setHeaderText("Было найдено исключение");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("images/error.png")).openStream()));
            alert.setContentText(exception.toString());
            alert.showAndWait();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Exception in showing exceptions: " + e + "\n Exception: " + exception, "Exception has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @NotNull
    private String getFileName(){
        return time.getLocalTime().getHour()+"-"+time.getLocalTime().getMinute()+"-"+time.getLocalTime().getSecond() + "_" + time.getDate() + ".log";
    }
}
