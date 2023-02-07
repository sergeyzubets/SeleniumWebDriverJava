package com.coherentsolutions.utilities.driver;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.coherentsolutions.utilities.constants.Constants.Config.*;

@Log4j2
@Getter
public class Driver {
    private static Driver instance = null;
    private WebDriver driver;

    private Driver() {
        driver = new ChromeDriver();
        setupWebDriver(driver);
    }

    public static Driver getDriverInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    private static void setupWebDriver(WebDriver webDriver) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_DURATION);
        log.info("WebDriver setup is done.");
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
}