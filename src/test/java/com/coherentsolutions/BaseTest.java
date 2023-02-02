package com.coherentsolutions;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.coherentsolutions.utilities.constants.Constants.Config.CHROME_DRIVER_LOCATION;
import static com.coherentsolutions.utilities.constants.Constants.Config.IMPLICIT_WAIT_DURATION;

@Log4j2
public abstract class BaseTest {
    private static WebDriver instance;

    protected static WebDriver getWebDriver() {
        if (instance != null) {
            return instance;
        }
        instance = new ChromeDriver();
        setupWebDriver(instance);
        return instance;
    }

    private static void setupWebDriver(WebDriver webDriver) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_DURATION);
        log.info("WebDriver setup is done.");
    }

    protected void openPage(String url) {
        getWebDriver().get(url);
        log.info("Open task page " + url);
    }

    protected static void webDriverQuit() {
        try {
            getWebDriver().close();
            getWebDriver().quit();
            log.info("WebDriver quit.");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}
