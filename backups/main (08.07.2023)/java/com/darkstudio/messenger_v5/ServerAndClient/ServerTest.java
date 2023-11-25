package com.darkstudio.messenger_v5.ServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTest {
    Socket client;
    public ServerSocket server;
    private final ArrayList<ClientTest> clients = new ArrayList<>();

    public ServerTest(int port) throws Exception {
        server = new ServerSocket(port);

        System.out.println("Server is running");
        System.out.println("Server address: " + "localhost: " + port);

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
