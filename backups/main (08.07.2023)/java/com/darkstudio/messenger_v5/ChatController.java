package com.darkstudio.messenger_v5;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatController {
    @FXML
    private Label addressLabel;
    @FXML
    public TextField hidenTextField, messageTextField;
    @FXML
    private ScrollPane messagesScrollPane;
    @FXML
    private VBox mainVBox, messagesVBox;
    @FXML
    private HBox controlHbox;
    @FXML
    private ImageView applyChatButton, cancelChatButton, sendButtonImageView, closeWindowButton, minimizeWindowButton;
    @FXML
    public Label usersListLabel;
    private Scanner in;
    private PrintWriter out;
    public Stage stage;
    public String clientName;
    private double x, y;
    private Socket socket;
    SetErrorViewOnTextFields setErrorViewOnTextFields = new SetErrorViewOnTextFields();
    ShowExceptions showExceptions = new ShowExceptions();
    public String avatarSource, userName;
    boolean userNameDidCreated = false;

    private void hideInput(boolean b){
        messageTextField.setVisible(b);
        sendButtonImageView.setVisible(b);
    }

    private void showConfirm(boolean b){
        try {
            hideInput(!b);
            applyChatButton.setVisible(b);
            cancelChatButton.setVisible(b);

            BoxBlur blur = new BoxBlur();
            messagesVBox.setEffect(blur);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public void connectToServer(String address, int port){
        try {
            socket = new Socket(address, port);

            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());

            addressLabel.setText(address + ":" + port);

            new Thread(() -> {
                while(true) {
                    try {
                        String message = in.nextLine();
                        Platform.runLater(() -> {
                            if(!message.contains("##session##end##"))
                                createMsgLabel(message);
                            else createMsgLabelFromServer("Пользователь покинул чат");
                        });

                        if(stage.isIconified()){
                            new SendNotification(message, address, TrayIcon.MessageType.INFO);
                        }
                    } catch(Exception ex) {
                        break;
                    }
                }
            }).start();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    @FXML
    void initialize() {
        setActionsOnObjects();
    }

    private void setActionsOnObjects() {
        try {
            BackgroundImage backgroundImage = new BackgroundImage(new Image(new File("background/bg.png").getAbsolutePath()),
                    BackgroundRepeat.ROUND,
                    BackgroundRepeat.ROUND,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            mainVBox.setBackground(background);

            messageTextField.setOnKeyPressed(key -> {
                if(key.getCode()== KeyCode.ENTER) {
                    if(userNameDidCreated) sendMessage(messageTextField.getText().trim()); else {
                        setUserName();
                        sendMessage(messageTextField.getText().trim());
                        userNameDidCreated = true;
                    }
                }
            });

            sendButtonImageView.setOnMouseClicked(mouseEvent -> {
                if(userNameDidCreated) sendMessage(messageTextField.getText().trim()); else {
                    setUserName();
                    sendMessage(messageTextField.getText().trim());
                    userNameDidCreated = true;
                }
            });

            closeWindowButton.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    try {
                        showConfirm(true);

                        applyChatButton.setOnMouseClicked(mouseEvent1 -> {
                            if(mouseEvent1.getButton()==MouseButton.PRIMARY){
                                disconnect();
                            }
                        });

                        cancelChatButton.setOnMouseClicked(mouseEvent1 -> {
                            if(mouseEvent1.getButton()==MouseButton.PRIMARY){
                                try{
                                    showConfirm(false);

                                    messagesVBox.setEffect(null);
                                }catch (Exception e){
                                    showExceptions.showException(e);
                                }
                            }
                        });
                    }catch (Exception e){
                        showExceptions.showException(e);
                    }
                }
            });

            minimizeWindowButton.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    stage.setIconified(true);
                }
            });

            controlHbox.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            controlHbox.setOnMouseDragged(event -> {
                controlHbox.getScene().getWindow().setX(event.getScreenX() - x);
                controlHbox.getScene().getWindow().setY(event.getScreenY() - y);
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public void disconnect() {
        try {
            showConfirm(false);

            sendMessage("##session##end##");

            socket.close();
            stage.close();
        }catch (Exception e){
            showExceptions.showException(e);
            stage.close();
        }
    }

    private void sendMessage(String trim) {
        try {
            if (!trim.isEmpty()) {
                out.println(clientName + ": " +trim);
                out.flush();

                messageTextField.clear();
            } else setErrorViewOnTextFields.setErrorViewToAnyField(messageTextField, null);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void createMsgLabelFromServer(String message){
        HBox messageHBox = new HBox();
        messageHBox.setAlignment(Pos.CENTER_LEFT);
        messageHBox.setSpacing(10);
        messageHBox.setPrefHeight(Region.USE_COMPUTED_SIZE);

        HBox userDataHBox = new HBox();
        userDataHBox.setAlignment(Pos.CENTER_LEFT);
        userDataHBox.setSpacing(10);
        userDataHBox.setStyle("-fx-padding: 10");

        Circle userAvatar = new Circle();
        userAvatar.setStroke(Paint.valueOf("white"));
        userAvatar.setFill(Paint.valueOf("white"));

        String avatarPath =  "images/userDefAvatars/1.png";
        userAvatar.setFill(new ImagePattern(new Image(new File(avatarPath).getAbsolutePath())));
        userAvatar.setRadius(27);

        VBox userNameAndTimeVBox = new VBox();
        userNameAndTimeVBox.setSpacing(10);
        userNameAndTimeVBox.setAlignment(Pos.CENTER_LEFT);

        Label userName = new Label("Server");

        Tooltip tooltip = new Tooltip();
        tooltip.setText("[Server] является техническим аккаунтом");
        tooltip.setHideDelay(Duration.seconds(2));
        tooltip.setAutoHide(true);
        tooltip.setWrapText(true);
        userName.setTooltip(tooltip);

        String userNameColor =  "yellow";
        userName.setStyle("-fx-text-fill: "+userNameColor);

        Label msgTime = new Label(new Time().getTime());
        msgTime.setStyle("-fx-text-fill: #8a8a8a; -fx-font-size: 11");

        userNameAndTimeVBox.getChildren().add(userName);
        userNameAndTimeVBox.getChildren().add(msgTime);

        userDataHBox.getChildren().add(userAvatar);
        userDataHBox.getChildren().add(userNameAndTimeVBox);

        Label msg = new Label(message);
        msg.setWrapText(true);
        msg.setStyle("-fx-text-fill: white");

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(80), messageHBox);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        int textLength = message.length()-1;
        Animation animation = new Transition() {
            {
                setCycleDuration(Duration.seconds(1));
            }
            @Override
            protected void interpolate(double v) {
                int n = (int) Math.round(textLength * v);

                msg.setText(message.substring(0, n));
            }
        };
        animation.play();
    }

    private void createMsgLabel(String text){
        try {
            HBox messageHBox = new HBox();
            messageHBox.setAlignment(Pos.CENTER_LEFT);
            messageHBox.setSpacing(10);
            messageHBox.setPrefHeight(Region.USE_COMPUTED_SIZE);

            HBox userDataHBox = new HBox();
            userDataHBox.setAlignment(Pos.CENTER_LEFT);
            userDataHBox.setSpacing(10);
            userDataHBox.setStyle("-fx-padding: 10");

            Circle userAvatar = new Circle();
            userAvatar.setStroke(Paint.valueOf("white"));
            userAvatar.setFill(Paint.valueOf("white"));

            String avatarPath = text.charAt(text.length() - 1) == '1' ? "images/userDefAvatars/1.png": avatarSource;
            userAvatar.setFill(new ImagePattern(new Image(new File(avatarPath).getAbsolutePath())));
            userAvatar.setRadius(27);

            VBox userNameAndTimeVBox = new VBox();
            userNameAndTimeVBox.setSpacing(10);
            userNameAndTimeVBox.setAlignment(Pos.CENTER_LEFT);

            text = text + "|";

            StringBuilder stringBuilder = new StringBuilder();
            char[] achars = text.toCharArray();

            String name;
            String message;

            int ii = 0, ai;

            while(achars[ii] != ':'){
                stringBuilder.append(achars[ii]);
                ii++;
            }

            name = stringBuilder.toString();

            ai = ii+2;

            stringBuilder.delete(0, stringBuilder.length());

            while(achars[ai] != '|'){
                stringBuilder.append(achars[ai]);
                ai++;
            }

            message = stringBuilder.toString();

            boolean b = text.charAt(text.length() - 2) == '1';
            Label userName = new Label(b ? "Server": name);

            Tooltip tooltip = new Tooltip();
            tooltip.setText("[Server] является техническим аккаунтом");
            tooltip.setHideDelay(Duration.seconds(2));
            tooltip.setAutoHide(true);
            tooltip.setWrapText(true);
            if(b) userName.setTooltip(tooltip);

            String userNameColor =  b ? "yellow": "#006bff";
            userName.setStyle("-fx-text-fill: "+userNameColor);

            Label msgTime = new Label(new Time().getTime());
            msgTime.setStyle("-fx-text-fill: #8a8a8a; -fx-font-size: 11");

            userNameAndTimeVBox.getChildren().add(userName);
            userNameAndTimeVBox.getChildren().add(msgTime);

            userDataHBox.getChildren().add(userAvatar);
            userDataHBox.getChildren().add(userNameAndTimeVBox);

            Label msg = new Label(message);
            msg.setWrapText(true);
            msg.setStyle("-fx-text-fill: white");

            StringBuilder textWithoutLastSymbol = new StringBuilder();

            char[] chars = text.toCharArray();
            for(int i = 0; i!=chars.length-1; i++){
                textWithoutLastSymbol.append(chars[i]);
            }
            msg.setText(textWithoutLastSymbol.toString());

            messageHBox.getChildren().add(userDataHBox);
            messageHBox.getChildren().add(msg);

            messagesVBox.getChildren().add(messageHBox);
            messagesScrollPane.setVvalue(messagesScrollPane.getVmax());

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(80), messageHBox);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            StringBuilder stringBuilder1 = new StringBuilder();
            int aint = 0;
            while(aint != text.length() - 1){
                stringBuilder1.append(chars[aint]);
                aint++;
            }
            String line = b ? stringBuilder1.toString(): getMessageFromText(text);

            int textLength = line.length()-1;
            Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.seconds(1));
                }
                @Override
                protected void interpolate(double v) {
                    int n = (int) Math.round(textLength * v);

                    msg.setText(line.substring(0, n));
                }
            };
            animation.play();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private String getMessageFromText(String text) {
        StringBuilder astringBuilder = new StringBuilder();
        char[] bchars = text.toCharArray();

        int iii = 0, aai;

        while(bchars[iii] != ':'){
            iii++;
        }

        aai = iii+2;

        while(bchars[aai] != '|'){
            astringBuilder.append(bchars[aai]);
            aai++;
        }

        astringBuilder.append(bchars[aai]);

        String messageFull = astringBuilder.toString();
        StringBuilder messageWithoutLastSymbol = new StringBuilder();
        char[] messageChars = messageFull.toCharArray();

        int i = 0;

        while(i!=messageFull.length()-1){
            messageWithoutLastSymbol.append(messageChars[i]);
            i++;
        }

        return messageWithoutLastSymbol.toString();
    }

    public void setUserName() {
        clientName = hidenTextField.getText();
    }
}
