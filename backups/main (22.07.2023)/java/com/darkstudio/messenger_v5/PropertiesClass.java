package com.darkstudio.messenger_v5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesClass {
    Properties properties;
    ShowExceptions showExceptions;

    public PropertiesClass() throws Exception {
        this.properties = new Properties();
        properties.load(new FileReader("configs/app.properties"));
        showExceptions = new ShowExceptions();
    }

    public void changeValue(String key, String newValue) throws IOException {
        try{
            properties.setProperty(key, newValue);
            properties.store(new FileWriter("configs/app.properties"), null);
        }catch(Exception e){
            showExceptions.showException(e);
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
