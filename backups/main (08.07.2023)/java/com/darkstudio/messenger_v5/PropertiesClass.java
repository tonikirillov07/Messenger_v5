package com.darkstudio.messenger_v5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesClass {
    Properties properties;

    public PropertiesClass() throws Exception {
        this.properties = new Properties();
        properties.load(new FileReader("configs/app.properties"));

    }

    public void changeValue(String key, String newValue) throws IOException {
        properties.setProperty(key, newValue);
        properties.store(new FileWriter("configs/app.properties"), null);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
