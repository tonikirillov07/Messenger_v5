package com.darkstudio.messenger_v5;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Weather {
    PropertiesClass propertiesClass = new PropertiesClass();
    String city;
    private String place, country, timezone, main, description, temperature, minTemperature, maxTemperature, temperatureFeelsLike, windSpeed, humidity, pressure, visibility;
    ShowExceptions showExceptions = new ShowExceptions();

    public Weather(String city) throws Exception {
        this.city = city;
    }

    public String getDataFromURL(String url) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL urlAddress = new URL(url);
            URLConnection urlConnection = urlAddress.openConnection();

            BufferedReader scanner = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = scanner.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }catch (Exception ex){
            showExceptions.showException(ex);
        }

        return stringBuilder.toString();
    }

    public String init() {
        try {
            String url = "http://api.openweathermap.org/data/2.5/weather?q="+city.trim()+"&appid=" + propertiesClass.getProperty("weather_api_key");

            JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(getDataFromURL(url));
            JSONArray weather = (JSONArray) jsonObject.get("weather");
            JSONObject weatherData = (JSONObject) weather.get(0);

            main = weatherData.get("main").toString();
            description = weatherData.get("description").toString();

            JSONObject jsonObjectMain = (JSONObject) jsonObject.get("main");
            JSONObject jsonObjectSys = (JSONObject) jsonObject.get("sys");

            temperature = jsonObjectMain.get("temp").toString();
            maxTemperature = jsonObjectMain.get("temp_max").toString();
            minTemperature = jsonObjectMain.get("temp_min").toString();
            temperatureFeelsLike = jsonObjectMain.get("feels_like").toString();

            JSONObject jsonObjectWind = (JSONObject) jsonObject.get("wind");
            windSpeed = jsonObjectWind.get("speed").toString();

            humidity = jsonObjectMain.get("humidity").toString();
            pressure = jsonObjectMain.get("pressure").toString();
            visibility = jsonObject.get("visibility").toString();

            country = jsonObjectSys.get("country").toString();
            place = jsonObject.get("name").toString();
            timezone = jsonObject.get("timezone").toString();
        }catch (Exception e){
            return e.getMessage();
        }
        return "1";
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getTemperatureFeelsLike() {
        return temperatureFeelsLike;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getPlace() {
        return place;
    }

    public String getCountry() {
        return country;
    }

    public String getTimezone() {
        return timezone;
    }
}
