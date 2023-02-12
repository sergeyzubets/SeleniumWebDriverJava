package com.coherentsolutions.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.coherentsolutions.utilities.Constants.Config.FIREFOX_DRIVER_LOCATION;

@Slf4j
public class Firefox implements DriverManager {

    @Override
    public WebDriver setupWebDriver() {
        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_LOCATION.toString());
        WebDriver webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        log.info("WebDriver setup for Firefox browser is done.");
        return webDriver;
    }
}