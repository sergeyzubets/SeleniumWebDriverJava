package com.coherentsolutions;

import com.coherentsolutions.utilities.DataProviders;
import com.sun.org.glassfish.gmbal.Description;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.coherentsolutions.utilities.ByVariables.DynamicData.*;
import static com.coherentsolutions.utilities.Constants.Config.*;
import static com.coherentsolutions.utilities.Constants.Message.*;

public class DynamicDataTest {
    private static WebDriver webDriver;
    private static WebDriverWait wait;
    private static final Logger LOGGER = LogManager.getLogger(DynamicDataTest.class);

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(EXPLICIT_WAITER_DURATION));
        LOGGER.info("Set up is done.");
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = DataProviders.class)
    @Description("Task 4 point 7: The test works with dynamic data: waits for a new user provided after clicking the button. Getting the first name, last name, and photo makes the test pass.")
    @SneakyThrows
    public void multiSelectListTest(String url) {
        LOGGER.info("Open task page " + url);
        webDriver.get(url);

        webDriver.findElement(GET_NEW_USER_BUTTON).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(NEW_USER_DETAILS, "loading...")));
        WebElement newUserDetails = webDriver.findElement(NEW_USER_DETAILS);
        WebElement newUserPhoto = webDriver.findElement(NEW_USER_PHOTO);
        LOGGER.info("New user details: " + '\n' + newUserDetails.getText());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newUserDetails.getText().contains("First Name"), MISSED_FIRST_NAME);
        softAssert.assertTrue(newUserDetails.getText().contains("Last Name"), MISSED_LAST_NAME);
        softAssert.assertTrue(newUserPhoto.isDisplayed(), MISSED_PHOTO);
        softAssert.assertAll();
    }

    @AfterSuite
    public void cleanUp() {
        webDriver.quit();
        LOGGER.info("Clean up");
    }
}
