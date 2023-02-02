package com.coherentsolutions;

import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;


import static com.coherentsolutions.utilities.constants.ByVariables.Alert.*;
import static com.coherentsolutions.utilities.constants.Constants.Message.*;
import static com.coherentsolutions.utilities.UtilMethods.getRandomString;

@Log4j2
public class AlertTest extends BaseTest {

    @BeforeClass
    @Parameters("alertUrl")
    public void setUp(String url) {
        getWebDriver();
        openPage(url);
    }

    @Test(dataProvider = "alerts")
    @Description("Task 4 point 6: The test invokes Confirm box, provides a string, confirms/denies and compares the decision value with the displayed one.")
    public void alertConfirmBoxTest(String decision) {
        log.info("alertConfirmBoxTest");
        getWebDriver().findElement(CONFIRM_BOX_BUTTON).click();
        String expectedResult = "";
        String actualResult = "";

        switch (decision) {
            case "accept":
                getWebDriver().switchTo().alert().accept();
                actualResult = getWebDriver().findElement(CONFIRM_RESULT).getText();
                expectedResult = "OK";
                break;
            case "dismiss":
                getWebDriver().switchTo().alert().dismiss();
                actualResult = getWebDriver().findElement(By.id("confirm-demo")).getText();
                expectedResult = "Cancel";
                break;
        }

        log.info("Expected Result: " + expectedResult);
        log.info("Actual Result: " + actualResult);
        Assert.assertTrue(actualResult.contains(expectedResult), PROMPT_ALERT_FAIL);
    }

    @Test(dataProvider = "alerts")
    @Description("Task 4 point 6: The test invokes Prompt box, provides a string, confirms/denies and compares the string value with the displayed one.")
    public void alertPromptBoxTest(String decision) {
        log.info("alertPromptBoxTest");
        getWebDriver().findElement(PROMPT_BOX_BUTTON).click();
        String name = "";
        String actualResult = "";

        switch (decision) {
            case "accept":
                name = getRandomString();
                getWebDriver().switchTo().alert().sendKeys(name);
                getWebDriver().switchTo().alert().accept();
                actualResult = getWebDriver().findElement(PROMPT_RESULT).getText();
                break;
            case "dismiss":
                getWebDriver().switchTo().alert().sendKeys(getRandomString());
                getWebDriver().switchTo().alert().dismiss();
                break;
        }

        log.info("Expected Result: " + name);
        log.info("Actual Result: " + actualResult);
        Assert.assertTrue(actualResult.contains(name), PROMPT_ALERT_FAIL);
    }

    @DataProvider(name = "alerts")
    public Object[][] alerts() {
        return new Object[][]{
                {"accept"},
                {"dismiss"}};
    }

    @AfterClass
    public void cleanUp() {
        webDriverQuit();
    }
}
