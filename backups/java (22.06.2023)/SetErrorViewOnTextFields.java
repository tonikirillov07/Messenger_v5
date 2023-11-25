package com.darkstudio.messenger_v5;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SetErrorViewOnTextFields {
    public void setErrorViewToAnyField(TextField textField, PasswordField passwordField){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.RED);

        Node node = null;
        if(textField!=null) node=textField; else if(passwordField!=null) node=passwordField;

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100.0), node);
        translateTransition.setFromX(0);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.play();

        new Thread(() ->{
            if(textField!=null) textField.setEffect(dropShadow); else if(passwordField!=null) passwordField.setEffect(dropShadow);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(textField!=null) textField.setEffect(null); else if(passwordField!=null) passwordField.setEffect(null);
        }).start();
    }
}
