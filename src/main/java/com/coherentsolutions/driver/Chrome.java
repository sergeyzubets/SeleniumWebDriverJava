package com.coherentsolutions.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.coherentsolutions.utilities.Constants.Config.CHROME_DRIVER_LOCATION;

@Slf4j
public class Chrome implements DriverManager {

    @Override
    public WebDriver setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        log.info("WebDriver setup for Chrome browser is done.");
        return webDriver;
    }
}