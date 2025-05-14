package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.calculator.net/password-generator.html");
            String clientIP = Task2.getClientIP(driver);
            Task3.getWeatherForecast(driver);
        } finally {
            driver.quit();
        }
    }
}
