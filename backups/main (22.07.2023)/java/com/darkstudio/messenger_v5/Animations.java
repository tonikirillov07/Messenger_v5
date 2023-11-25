package com.darkstudio.messenger_v5;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animations {
    private boolean useAnimations;
    ShowExceptions showExceptions = new ShowExceptions();

    public Animations(boolean useAnimations) {
        this.useAnimations = useAnimations;
    }

    public void setAnimationForSettings(Node node, int duration){
        try {
            if (useAnimations) {
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), node);
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);
                fadeTransition.play();

                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(duration), node);
                translateTransition.setFromY(-15);
                translateTransition.setToY(0);
                translateTransition.play();
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public void playAnimToTileInDesktop(Node node) {
        try {
            if (useAnimations) {
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100), node);
                translateTransition.setFromY(0);
                translateTransition.setToY(-55);
                translateTransition.play();
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }
}
