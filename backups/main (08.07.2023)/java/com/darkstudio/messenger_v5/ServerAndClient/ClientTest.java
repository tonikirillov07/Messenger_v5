package com.darkstudio.messenger_v5.ServerAndClient;

import com.darkstudio.messenger_v5.ShowExceptions;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest implements Runnable{
    private ServerTest server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    public static int clients_count;
    ShowExceptions showExceptions = new ShowExceptions();
    public String userName;

    public ClientTest(@NotNull Socket socket, ServerTest server) {
        try {
            clients_count++;
            this.server = server;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            showExceptions.showException(e);
        }
    }

    public void run() {
        try {
            this.server.sendMsgToAll("Новый пользователь "+ userName +" присоединился к чату! Активных пользователей: " + clients_count, true);

            while(true) {
                if (this.inMessage.hasNext()) {
                    String clientMessage = this.inMessage.nextLine();
                    if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        server.sendMsgToAll("Пользователь "+ userName +" покинул чат", true);
                        break;
                    }

                    this.server.sendMsgToAll(clientMessage, false);
                }

                Thread.sleep(100);
            }
        } catch (Exception e) {
             showExceptions.showException(e);
        } finally {
            this.close();
        }

    }

    public void sendMsg(String msg) {
        try {
            this.outMessage.println(msg);
            this.outMessage.flush();
        } catch (Exception e) {
            showExceptions.showException(e);
        }

    }

    public void close() {
        try {
            this.server.remove(this.toString());
            clients_count--;
            this.server.sendMsgToAll("Активных пользователей: " + clients_count, true);
        } catch (Exception e) {
            showExceptions.showException(e);
        }

    }
}
