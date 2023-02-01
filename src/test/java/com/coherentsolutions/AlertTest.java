package com.coherentsolutions;

import com.coherentsolutions.utilities.DataProviders;
import com.sun.org.glassfish.gmbal.Description;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import static com.coherentsolutions.utilities.ByVariables.Alert.*;
import static com.coherentsolutions.utilities.Constants.Config.*;
import static com.coherentsolutions.utilities.Constants.Message.*;
import static com.coherentsolutions.utilities.UtilMethods.getRandomString;

public class AlertTest {
    private static WebDriver webDriver;
    private static final Logger LOGGER = LogManager.getLogger(AlertTest.class);

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAITER_DURATION));
        LOGGER.info("Set up is done.");
    }

    @Test(dataProvider = "alerts", dataProviderClass = DataProviders.class)
    @Description("Task 4 point 6: The test invokes Confirm box, provides a string, confirms/denies and compares the decision value with the displayed one.")
    @SneakyThrows
    public void alertConfirmBoxTest(String url, String decision) {
        LOGGER.info("Open task page " + url);
        webDriver.get(url);

        webDriver.findElement(CONFIRM_BOX_BUTTON).click();
        String expectedResult = "";
        String actualResult = "";

        switch (decision) {
            case "accept":
                webDriver.switchTo().alert().accept();
                actualResult = webDriver.findElement(CONFIRM_RESULT).getText();
                expectedResult = "OK";
                break;
            case "dismiss":
                webDriver.switchTo().alert().dismiss();
                actualResult = webDriver.findElement(By.id("confirm-demo")).getText();
                expectedResult = "Cancel";
                break;
        }

        LOGGER.info("Entered value: " + expectedResult);
        LOGGER.info("Actual value: " + actualResult);
        Assert.assertTrue(actualResult.contains(expectedResult), PROMPT_ALERT_FAIL);
    }

    @Test(dataProvider = "alerts", dataProviderClass = DataProviders.class)
    @Description("Task 4 point 6: The test invokes Prompt box, provides a string, confirms/denies and compares the string value with the displayed one.")
    @SneakyThrows
    public void alertPromptBoxTest(String url, String decision) {
        LOGGER.info("Open task page " + url);
        webDriver.get(url);

        webDriver.findElement(PROMPT_BOX_BUTTON).click();
        String name = "";
        String actualResult = "";

        switch (decision) {
            case "accept":
                name = getRandomString();
                webDriver.switchTo().alert().sendKeys(name);
                webDriver.switchTo().alert().accept();
                actualResult = webDriver.findElement(PROMPT_RESULT).getText();
                break;
            case "dismiss":
                webDriver.switchTo().alert().dismiss();
                break;
        }

        LOGGER.info("Entered value " + name);
        LOGGER.info("Actual value " + actualResult);
        Assert.assertTrue(actualResult.contains(name), PROMPT_ALERT_FAIL);
    }

    @AfterSuite
    public void cleanUp() {
        webDriver.quit();
        LOGGER.info("Clean up");
    }

}
