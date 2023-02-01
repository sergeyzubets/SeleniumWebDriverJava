package com.coherentsolutions;

import com.coherentsolutions.utilities.DataProviders;
import com.sun.org.glassfish.gmbal.Description;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

import static com.coherentsolutions.utilities.ByVariables.MultiSelect.*;
import static com.coherentsolutions.utilities.Constants.Config.*;
import static com.coherentsolutions.utilities.Constants.Message.MULTISELECT_FAIL;
import static com.coherentsolutions.utilities.UtilMethods.getThreeUniqueInt;

public class MultiSelectTest {
    private static WebDriver webDriver;
    private static final Logger LOGGER = LogManager.getLogger(MultiSelectTest.class);

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAITER_DURATION));
        LOGGER.info("Set up is done.");
    }

    @Test(dataProvider = "multiSelectList", dataProviderClass = DataProviders.class)
    @Description("Task 4 point 5: The test verifies selected items in multi select list element.")
    @SneakyThrows
    public void multiSelectListTest(String url) {
        LOGGER.info("Open task page " + url);
        webDriver.get(url);

        List<WebElement> listOfOptions = webDriver.findElements(MULTI_SELECT_LIST);
        List<Integer> optionPosition = getThreeUniqueInt(listOfOptions.size());
        StringBuilder expectedResult = new StringBuilder();

        optionPosition.forEach(item -> expectedResult.append(listOfOptions.get(item).getText()).append(","));
        expectedResult.deleteCharAt(expectedResult.length() - 1);
        LOGGER.info("expectedResult: " + expectedResult);

        new Actions(webDriver).keyDown(Keys.CONTROL)
                .click(listOfOptions.get(optionPosition.get(0)))
                .click(listOfOptions.get(optionPosition.get(1)))
                .click(listOfOptions.get(optionPosition.get(2)))
                .keyUp(Keys.CONTROL)
                .build()
                .perform();

        webDriver.findElement(GET_SELECTED_ITEMS_BUTTON).click();

        String actualResult = webDriver
                .findElement(GET_LIST_OF_SELECTED_ITEMS)
                .getText()
                .replace("Options selected are : ", "");

        LOGGER.info("actualResult: " + actualResult);
        Assert.assertEquals(actualResult, expectedResult.toString(), MULTISELECT_FAIL);
    }

    @AfterSuite
    public void cleanUp() {
        webDriver.quit();
        LOGGER.info("Clean up");
    }

}
