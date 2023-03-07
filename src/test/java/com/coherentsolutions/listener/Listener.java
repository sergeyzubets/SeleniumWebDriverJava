package com.coherentsolutions.listener;

import com.coherentsolutions.driver.Driver;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
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

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            WebDriver driver = Driver.getDriverInstance().getDriver();
            saveScreenshot(driver);
        }
    }
}