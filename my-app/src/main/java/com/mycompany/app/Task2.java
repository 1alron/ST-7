package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    public static String getClientIP(WebDriver driver) {
        String ipAddress = "";
        try {
            driver.get("https://api.ipify.org/?format=json");
            String jsonResponse = "";
            try {
                WebElement preElem = driver.findElement(By.tagName("pre"));
                jsonResponse = preElem.getText();
            } catch (Exception e) {
                jsonResponse = driver.getPageSource();
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonResponse);
            ipAddress = (String) obj.get("ip");
            System.out.println("Client IP address: " + ipAddress);
        } catch (Exception e) {
            System.out.println("Error in Task2: " + e);
        }
        return ipAddress;
    }
}
