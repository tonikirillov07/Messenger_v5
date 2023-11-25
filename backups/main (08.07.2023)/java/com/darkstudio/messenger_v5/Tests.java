package com.darkstudio.messenger_v5;

public class Tests {
    public static void main(String[] args) {
        new Tests().start();
    }

    private void start(){
        String text = "User: My message0|";
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

        while(i!=messageFull.length()-2){
            messageWithoutLastSymbol.append(messageChars[i]);
            i++;
        }

        System.out.println(messageWithoutLastSymbol);
    }


}
