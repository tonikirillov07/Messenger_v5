package com.darkstudio.messenger_v5;


public class Tests {
    public static void main(String[] args) throws Exception {
        new Tests().start();
    }

    private void start() throws Exception{

        System.out.println(createUserID("Anton1", "azlk2141"));
    }

    private int createUserID(String login, String password) {
        return login.hashCode()*password.hashCode();
    }

}
