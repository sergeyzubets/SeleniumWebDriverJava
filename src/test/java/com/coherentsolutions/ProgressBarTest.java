package com.coherentsolutions;

import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.coherentsolutions.utilities.constants.ByVariables.ProgressBar.*;
import static com.coherentsolutions.utilities.constants.Constants.Config.*;
import static com.coherentsolutions.utilities.constants.Constants.Message.DOWNLOAD_TEST_FAIL;
import static com.coherentsolutions.utilities.driver.Driver.getWebDriver;

@Log4j2
public class ProgressBarTest extends BaseTest {

    @BeforeClass
    @Parameters("progressBarUrl")
    public void setUp(String url) {
        openPage(url);
    }

    @Test
    @Description("Task 4 point 8: The test starts download process and refreshes the page when percentage is >= " + PERCENTAGE_MAX)
    public void progressBarTest() {
        WebElement downloadProcess = getWebDriver().findElement(DOWNLOAD_PROCESS_VALUE);
        getWebDriver().findElement(DOWNLOAD_BUTTON).click();
        long whileStartTime = System.currentTimeMillis();

        while (true) {
            if (Integer.parseInt(downloadProcess.getText().replace("%", "")) >= PERCENTAGE_MAX) {
                log.info("Achieved percent value is: " + downloadProcess.getText());
                getWebDriver().navigate().refresh();
                break;
            }

            if (System.currentTimeMillis() >= whileStartTime + WHILE_LIFETIME_SEC * 1000) {
                log.info("while() was interrupted by timeout.");
                break;
            }
        }

        Assert.assertTrue(getWebDriver().findElement(DOWNLOAD_BUTTON).isDisplayed(), DOWNLOAD_TEST_FAIL);
    }
}