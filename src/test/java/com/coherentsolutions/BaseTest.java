package com.coherentsolutions;

import com.coherentsolutions.listener.Listener;
import com.coherentsolutions.pages.MainPage;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import static com.coherentsolutions.driver.Driver.getDriverInstance;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Slf4j
@Listeners({Listener.class})
public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    void setAllureEnvironment() {
        Capabilities cap = ((RemoteWebDriver) getDriverInstance().getDriver()).getCapabilities();
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", cap.getPlatformName().toString())
                        .put("Browser", cap.getBrowserName().toLowerCase())
                        .put("Browser.Version", cap.getBrowserVersion())
                        .put("URL", getPropertyValue("loginPageUrl"))
                        .build());
    }

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