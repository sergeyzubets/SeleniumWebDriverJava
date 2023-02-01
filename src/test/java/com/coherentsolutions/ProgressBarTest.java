package com.coherentsolutions;

import com.coherentsolutions.utilities.DataProviders;
import com.sun.org.glassfish.gmbal.Description;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.coherentsolutions.utilities.ByVariables.ProgressBar.DOWNLOAD_BUTTON;
import static com.coherentsolutions.utilities.ByVariables.ProgressBar.DOWNLOAD_PROCESS_VALUE;
import static com.coherentsolutions.utilities.Constants.Config.*;
import static com.coherentsolutions.utilities.Constants.Message.DOWNLOAD_TEST_FAIL;

public class ProgressBarTest {
    private static WebDriver webDriver;
    private static final Logger LOGGER = LogManager.getLogger(DynamicDataTest.class);

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        LOGGER.info("Set up is done.");
    }

    @Test(dataProvider = "progressBar", dataProviderClass = DataProviders.class)
    @Description("Task 4 point 8: The test starts download process and refreshes the page when percentage is >= " + PERCENTAGE_MAX)
    @SneakyThrows
    public void progressBarTest(String url) {
        LOGGER.info("Open task page " + url);
        webDriver.get(url);

        WebElement downloadProcess = webDriver.findElement(DOWNLOAD_PROCESS_VALUE);
        webDriver.findElement(DOWNLOAD_BUTTON).click();

        while (true) {
            if (Integer.parseInt(downloadProcess.getText().replace("%", "")) >= PERCENTAGE_MAX) {
                LOGGER.info("Achieved value percent value is: " + downloadProcess.getText());
                webDriver.navigate().refresh();
                break;
            }
        }

        Assert.assertTrue(webDriver.findElement(DOWNLOAD_BUTTON).isDisplayed(), DOWNLOAD_TEST_FAIL);
    }

    @AfterSuite
    public void cleanUp() {
        webDriver.quit();
        LOGGER.info("Clean up");
    }

}
