package com.coherentsolutions.driver;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.coherentsolutions.utilities.Constants.Config.*;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;

@Slf4j
@Getter
public class Driver {
    private static Driver instance = null;
    private WebDriver driver;

    private Driver() {
        driver = selectDriver(getPropertyValue("browser"));
    }

    public static Driver getDriverInstance() {
        if (instance == null) {
            synchronized (Driver.class) {
                if (instance == null) {
                    instance = new Driver();
                }
            }
        }
        return instance;
    }

    public void webDriverQuit() {
        try {
            getDriver().close();
            getDriver().quit();
            log.info("WebDriver quit.");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), EXPLICIT_WAIT_DURATION);
    }

    private WebDriver selectDriver(String selectedBrowser) {
        DriverManager driverManager = selectedBrowser.equals("chrome") ? new Chrome() : new Firefox();
        return driverManager.setupWebDriver();
    }
}