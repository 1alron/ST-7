package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;

public class Task3 {
    public static void getWeatherForecast(WebDriver driver) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            driver.get(url);
            String jsonStr = "";
            try {
                WebElement preElem = driver.findElement(By.tagName("pre"));
                jsonStr = preElem.getText();
            } catch (Exception e) {
                jsonStr = driver.getPageSource();
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);

            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray timeArray = (JSONArray) hourly.get("time");
            JSONArray tempArray = (JSONArray) hourly.get("temperature_2m");
            JSONArray rainArray = (JSONArray) hourly.get("rain");

            StringBuilder table = new StringBuilder();
            table.append(String.format("%-4s | %-20s | %-12s | %-12s\n", "№", "Дата/время", "Температура", "Осадки (мм)"));
            table.append("-------------------------------------------------------------\n");
            int count = Math.min(timeArray.size(), Math.min(tempArray.size(), rainArray.size()));
            for (int i = 0; i < count; i++) {
                String dateTime = timeArray.get(i).toString();
                String temperature = tempArray.get(i).toString();
                String rain = rainArray.get(i).toString();
                table.append(String.format("%-4d | %-20s | %-12s | %-12s\n", (i + 1), dateTime, temperature, rain));
            }
            System.out.println(table);

            try (FileWriter fw = new FileWriter("../result/forecast.txt")) {
                fw.write(table.toString());
            } catch (IOException e) {
                System.out.println("Error writing forecast file: " + e);
            }
        } catch (Exception e) {
            System.out.println("Error in Task3: " + e);
        }
    }
}
