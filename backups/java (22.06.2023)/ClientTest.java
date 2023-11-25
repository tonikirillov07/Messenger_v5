package com.darkstudio.messenger_v5.ServerAndClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest implements Runnable{
    ServerTest serverTest;
    PrintWriter out;
    Scanner in;
    int clientCounter = 0;
    public String userName;
    public ClientTest(Socket client, ServerTest serverTest) throws IOException {
        clientCounter++;
        this.serverTest = serverTest;
        this.out = new PrintWriter(client.getOutputStream());
        this.in = new Scanner(client.getInputStream());
    }

    @Override
    public void run() {
        serverTest.sendMsgToAll("Пользователь присоединился к чату");
        System.out.println("Пользователь "+ userName +" присоединился к чату");

        while(true){
            if(in.hasNext()){
                String message = in.nextLine();
                serverTest.sendMsgToAll(message);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMsg(String msg){
        out.println(msg);
        out.flush();
    }

    public void close(){
        serverTest.remove(this.toString());
        clientCounter--;
        serverTest.sendMsgToAll("Пользователь покинул чат");
    }
}
