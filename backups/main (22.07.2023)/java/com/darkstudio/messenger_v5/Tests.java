package com.darkstudio.messenger_v5;

public class Tests {
    public static void main(String[] args) {
        new Tests().start();
    }

    private void start(){
        try {
            String astring = "Antony: imageMessage|E:\\Users\\toniw\\OneDrive\\Изображения\\Снимки экрана\\Screenshot 2023-06-28 202449.png";
            char[] chars = astring.toCharArray();

            int aint = 0;

            while(chars[aint] != '|'){
                aint++;
            }
            aint++;

            StringBuilder image = new StringBuilder();
            for(int i = aint; i!=chars.length; i++){
                image.append(chars[i]);
            }

            System.out.println(image);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
