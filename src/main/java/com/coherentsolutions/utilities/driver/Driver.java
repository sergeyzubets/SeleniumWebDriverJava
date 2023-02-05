package com.coherentsolutions.utilities.driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.coherentsolutions.utilities.constants.Constants.Config.*;

@Log4j2
public class Driver {
    private static WebDriver instance;

    protected WebDriver getWebDriver() {
        if (instance != null) {
            return instance;
        }
        instance = new ChromeDriver();
        setupWebDriver(instance);
        return instance;
    }

    private void setupWebDriver(WebDriver webDriver) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_DURATION);
        log.info("WebDriver setup is done.");
    }

    protected void webDriverQuit() {
        try {
            getWebDriver().close();
            getWebDriver().quit();
            log.info("WebDriver quit.");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(getWebDriver(), EXPLICIT_WAIT_DURATION);
    }
}
