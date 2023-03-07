package com.coherentsolutions;

import com.coherentsolutions.listener.Listener;
import com.coherentsolutions.pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;

import static com.coherentsolutions.driver.Driver.getDriverInstance;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;

@Slf4j
@Listeners({Listener.class})
public class BaseTest {
    protected WebDriver driver;

    @AfterSuite
    public void tearDown() {
        getDriverInstance().webDriverQuit();
    }

    public MainPage openMainPage() {
        String url = getPropertyValue("loginPageUrl");
        driver = getDriverInstance().getDriver();
        driver.get(url);
        log.info("Open task page " + url);
        return new MainPage(driver);
    }
}