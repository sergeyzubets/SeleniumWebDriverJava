package com.coherentsolutions;

import com.coherentsolutions.pages.MainPage;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;

import static com.coherentsolutions.driver.Driver.getDriverInstance;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Slf4j
@Getter
public class BaseTest {
    protected static boolean isTestFailed;
    protected WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void resultAnalysis() {
        if (isTestFailed) {
            addScreenshotToFailedTest();
        }
    }

    @BeforeSuite
    void setAllureEnvironment() {
        Capabilities cap = ((RemoteWebDriver) getDriverInstance().getDriver()).getCapabilities();
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", cap.getPlatformName().toString().toLowerCase())
                        .put("Browser", cap.getBrowserName().toLowerCase())
                        .put("Browser.Version", cap.getBrowserVersion())
                        .put("URL", getPropertyValue("loginPageUrl"))
                        .build());
    }

    @AfterSuite(alwaysRun = true)
    public void driverQuit() {
        getDriverInstance().webDriverQuit();
    }

    public MainPage openMainPage() {
        String url = getPropertyValue("loginPageUrl");
        driver = getDriverInstance().getDriver();
        driver.get(url);
        log.info("Open task page " + url);
        return new MainPage(driver);
    }

    public void addScreenshotToFailedTest() {
        Allure.addAttachment("Screenshot of failed test", new ByteArrayInputStream(((TakesScreenshot) getDriverInstance().getDriver()).getScreenshotAs(OutputType.BYTES)));
    }
}