package com.darkstudio.messenger_v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AIServer {
    private List<DataOutputStream> outputStreams = new ArrayList<>();
    private AIDatabase db;

    public AIServer() {
        try {
            db = new AIDatabase();
            ServerSocket server = new ServerSocket(7777);
            while(true) {
                Socket socket = server.accept();
                ClientHandler handler = new ClientHandler(socket);
                outputStreams.add(handler.out);
                new Thread(handler).start();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private String username;

        public ClientHandler(Socket socket) {
            try {
                this.socket = socket;
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                // Запрашиваем имя пользователя
                out.writeUTF("Введите имя пользователя:");
                username = in.readUTF();

                // Добавляем пользователя в базу данных
                db.addUser(username);
                updateUsers();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void run () {
            try {
                while (true) {
                    String message = in.readUTF();
                    db.addMessage(username, message);
                    String formattedMessage = String.format("[%s]: %s", username, message);
                    broadcast(formattedMessage);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                removeClient(this);
            }
        }

            private void updateUsers () {
                String userList = db.getUserList();
                String[] users = userList.split(",");
                for (DataOutputStream out : outputStreams) {
                    try {
                        out.writeUTF(userList);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            private void broadcast (String message){
                for (DataOutputStream out : outputStreams) {
                    try {
                        out.writeUTF(message);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        private void removeClient(ClientHandler handler) {
            outputStreams.remove(handler.out);
            try {
                handler.socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String formattedMessage = String.format("%s покинул(а) чат", handler.username);
            broadcast(formattedMessage);
            updateUsers();
        }

    }


    public static void main(String[] args) {
        new AIServer();
    }
}