package com.darkstudio.messenger_v5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AIMain extends Application {
    private TextArea chatHistory;
    private TextField messageInput;
    private Button sendButton;
    private ListView<String> userListView;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public void start(Stage primaryStage) {
        // Создаем пользовательский интерфейс
        chatHistory = new TextArea();
        messageInput = new TextField();
        sendButton = new Button("Отправить");
        userListView = new ListView<String>();
        HBox messageBox = new HBox(messageInput, sendButton);
        VBox mainBox = new VBox(chatHistory, messageBox, userListView);
        Scene scene = new Scene(mainBox, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Устанавливаем обработчик событий
        messageInput.setOnAction(e -> sendMessage());
        sendButton.setOnAction(e -> sendMessage());

        // Подключаемся к серверу
        try {
            socket = new Socket("localhost", 7777);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch(IOException ex) {
            ex.printStackTrace();
            return;
        }

        // Получаем список пользователей
        try {
            out.writeUTF("users");
            String userList = in.readUTF();
            String[] users = userList.split(",");
            userListView.setItems(FXCollections.observableArrayList(users));
        } catch(IOException ex) {
            ex.printStackTrace();
            return;
        }

        // Получаем сообщения от сервера
        new Thread(() -> {
            while(true) {
                try {
                    String message = in.readUTF();
                    Platform.runLater(() -> chatHistory.appendText(message + "\n"));
                } catch(IOException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
        }).start();
    }

    private void sendMessage() {
        try {
            String message = messageInput.getText();
            out.writeUTF(message);
            messageInput.setText("");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
