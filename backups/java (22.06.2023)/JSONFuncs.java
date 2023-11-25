package com.darkstudio.messenger_v5;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class JSONFuncs {
    JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void createJSONObject(){
        jsonObject = new JSONObject();
    }

    public void putSmthInJSONObject(String key, String value){
        jsonObject.put(key, value);
    }

    public void createJSONFile(String jsonPathAndName) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(jsonPathAndName);
        printWriter.println(jsonObject);

        printWriter.flush();
        printWriter.close();
    }

    public String getDataFromJSON(String jsonFile, String key) throws Exception {
        Object object = new JSONParser().parse(new FileReader(jsonFile));
        JSONObject jsonObject1 = (JSONObject) object;
        return jsonObject1.get(key).toString();
    }

}
