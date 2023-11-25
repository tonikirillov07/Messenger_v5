package com.darkstudio.messenger_v5.ServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTest {
    Socket client;
    public ServerSocket server;
    public String userName;
    int port;
    private final ArrayList<ClientTest> clients = new ArrayList<>();

    public ServerTest(int port) throws Exception {
        this.port = port;

        server = new ServerSocket(port);

        System.out.println("Server is running on port "+port);
        new Thread(() ->{
            try {
                client = server.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ClientTest clientTest;
            try {
                clientTest = new ClientTest(client, this);
                clientTest.userName=userName;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clients.add(clientTest);
            new Thread(clientTest).start();

        }).start();
    }

    public void sendMsgToAll(String msg){
        for(ClientTest clientTest: clients){
            System.out.println(msg);
            clientTest.sendMsg(msg);
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
