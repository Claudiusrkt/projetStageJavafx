package com.project.javafx4.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

public class Pilote {
    public WebDriver chromeDriver(){ // pilote chrome driver
        WebDriver driver = null; //path vers chromedriver.exe
        try {
            String path = new File("webDriver/chromedriver-win64/chromedriver.exe").getAbsolutePath();
            System.setProperty("webdriver.chrome.driver", path);
            driver = new org.openqa.selenium.chrome.ChromeDriver();
        }catch (Exception e){
            e.printStackTrace();
        }
        return driver;
    }
    public WebDriver chromeDriverNoGui(){//pilote chrome driver sans gui
        WebDriver driver = null; //path vers chromedriver.exe
        try {
            String path = new File("webDriver/chromedriver-win64/chromedriver.exe").getAbsolutePath();
            System.setProperty("webdriver.chrome.driver", path);
        }catch (Exception e){
            e.printStackTrace();
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920x1080");
//        options.addArguments("--disable-dev-shm-usage");

        driver = new org.openqa.selenium.chrome.ChromeDriver(options);
        return driver;
    }
    public WebDriver edgeDriver(){ // pilote chrome driver
        WebDriver driver = null; //path vers chromedriver.exe
        try {
            String path = new File("webDriver/edgedriver_win64/msedgedriver.exe").getAbsolutePath();
            System.setProperty("webdriver.edge.driver", path);
            driver = new org.openqa.selenium.edge.EdgeDriver();
        }catch (Exception e){
            e.printStackTrace();
        }
        return driver;
    }
    public WebDriver edgeDriverNoGui(){//pilote chrome driver sans gui
        WebDriver driver = null; //path vers chromedriver.exe
        try {
            String path = new File("webDriver/edgedriver_win64/msedgedriver.exe").getAbsolutePath();
            System.setProperty("webdriver.edge.driver", path);
        }catch (Exception e){
            e.printStackTrace();
        }
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920x1080");
//        options.addArguments("--disable-dev-shm-usage");
        driver = new org.openqa.selenium.edge.EdgeDriver(options);
        return driver;
    }
    public WebDriver firefoxDriver(){ // pilote chrome driver
        WebDriver driver = null; //path vers chromedriver.exe
        try {
            String path = new File("webDriver/geckodriver-v0.35.0-win-aarch64/geckodriver.exe").getAbsolutePath();
            System.setProperty("webdriver.gecko.driver", path);
            driver = new FirefoxDriver();
        }catch (Exception e){
            e.printStackTrace();
        }
        return driver;
    }
    public WebDriver firefoxDriverNoGui(){ // pilote chrome driver
        WebDriver driver = null; //path vers chromedriver.exe
        try {
            String path = new File("webDriver/geckodriver-v0.35.0-win-aarch64/geckodriver.exe").getAbsolutePath();
            System.setProperty("webdriver.gecko.driver", path);
        }catch (Exception e){
            e.printStackTrace();
        }
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920x1080");
//        options.addArguments("--disable-dev-shm-usage");
        driver = new org.openqa.selenium.firefox.FirefoxDriver(options);
        return driver;
    }
}
