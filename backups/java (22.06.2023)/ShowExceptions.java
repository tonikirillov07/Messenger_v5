package com.darkstudio.messenger_v5;

import javafx.scene.control.Alert;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.PrintStream;

public class ShowExceptions {
    Time time = new Time();

    public void showException(@NotNull Exception exception) {
        exception.printStackTrace();

        try {
            exception.printStackTrace(new PrintStream(new File("errorLogs/"+getFileName())));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception has occurred");
            alert.setHeaderText("Было найдено исключение");
            alert.setContentText(exception.toString());
            alert.showAndWait();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString(), "Exception in showing message about exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    @NotNull
    private String getFileName(){
        return time.getLocalTime().getHour()+"-"+time.getLocalTime().getMinute()+"-"+time.getLocalTime().getSecond() + "_" + time.getDate() + ".txt";
    }
}
