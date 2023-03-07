package com.coherentsolutions.listener;

import com.coherentsolutions.driver.Driver;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static com.coherentsolutions.driver.Utils.makeScreenshot;

@Slf4j
public class Listener implements IInvokedMethodListener {

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return makeScreenshot(driver);
    }

    @Attachment(value = "Environment details", type = "text/plain")
    public String saveErrorLog(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String os = cap.getPlatformName().toString();
        String version = cap.getBrowserVersion();
        return "Browser.name = " + browserName + "\n" +
                "Browser.version = " + version + "\n" +
                "OS = " + os;
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            WebDriver driver = Driver.getDriverInstance().getDriver();
            saveScreenshot(driver);
            saveErrorLog(driver);
        }
    }
}