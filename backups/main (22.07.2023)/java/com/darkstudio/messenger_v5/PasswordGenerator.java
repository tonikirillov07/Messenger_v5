package com.darkstudio.messenger_v5;

import java.util.Collections;
import java.util.List;

public class PasswordGenerator {
    String password = "";
    List<String> allCharsList = new java.util.ArrayList<>(List.of());
    int length = 7;

    public String getRecommendedPassword(){
        return password;
    }

    public void generatePassword(){
        String chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890-><()";
        char[] charsArray = chars.toCharArray();

        for(int i = 0; i!=charsArray.length; i++){
            allCharsList.add(String.valueOf(charsArray[i]));
        }

        Collections.shuffle(allCharsList);

        for(int i = 0; i!=length; i++){
            password = allCharsList.get(i) + password;
        }
    }
}
