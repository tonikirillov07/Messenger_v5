package com.darkstudio.messenger_v5;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.PrintWriter;

public class JSONFuncs {
    JSONObject jsonObject;
    ShowExceptions showExceptions;

    public JSONFuncs(){
        showExceptions = new ShowExceptions();
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void createJSONObject(){
        try {
            jsonObject = new JSONObject();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public void putSmthInJSONObject(String key, String value){
        try {
            jsonObject.put(key, value);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public void createJSONFile(String jsonPathAndName) {
        try {
            PrintWriter printWriter = new PrintWriter(jsonPathAndName);
            printWriter.println(jsonObject);

            printWriter.flush();
            printWriter.close();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public String getDataFromJSON(String jsonFile, String key) {
        try {
            Object object = new JSONParser().parse(new FileReader(jsonFile));
            JSONObject jsonObject1 = (JSONObject) object;
            return jsonObject1.get(key).toString();
        }catch (Exception e){
            showExceptions.showException(e);
        }
        return null;
    }

}
