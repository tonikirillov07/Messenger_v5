package com.darkstudio.messenger_v5.ServerAndClient;

import com.darkstudio.messenger_v5.MainWindowController;
import com.darkstudio.messenger_v5.PropertiesClass;
import com.darkstudio.messenger_v5.SendNotification;
import com.darkstudio.messenger_v5.ShowExceptions;
import javafx.application.Platform;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTest {
    Socket client;
    public ServerSocket server;
    ShowExceptions showExceptions = new ShowExceptions();
    private final ArrayList<ClientTest> clients = new ArrayList<>();
    public boolean sendNotification;

    public ServerTest(int port) throws Exception {
        server = new ServerSocket(port);

        String address = InetAddress.getLocalHost().getHostAddress() + ":" + port;
        Platform.runLater(() -> {
            try {
                if(sendNotification) {
                    SendNotification sendNotification = new SendNotification("Создан чат с адресом: " + address + ". Нажмите, чтобы скопировать в буфер обмена", new PropertiesClass().getProperty("app_title"), TrayIcon.MessageType.INFO);
                    sendNotification.display();
                    sendNotification.getTrayIcon().addActionListener(actionListener -> {
                        try {
                            new MainWindowController().copyText(address);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }else{
                    System.out.println("Created chat with address: " + address);
                }
            }catch (Exception e){
                showExceptions.showException(e);
            }
        });

        new Thread(() -> {
            while (true) {
                try {
                    client = server.accept();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ClientTest clientTest = new ClientTest(client, this);
                clients.add(clientTest);
                new Thread(clientTest).start();
            }
        }).start();
    }

    public void sendMsgToAll(String msg, boolean fromServer){
        for(ClientTest clientTest: clients){
            System.out.println(msg.contains("##session##end##") ? "Покинул чат": msg);

            int aint = fromServer ? 1: 0;
            clientTest.sendMsg(msg + aint);
        }
    }

    public void stop() throws Exception{
        client.close();
        server.close();
    }

    public Socket getClient() {
        return client;
    }

    public void remove(String client){
        clients.remove(client);
    }
}
