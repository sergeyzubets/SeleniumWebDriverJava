package com.coherentsolutions;

import com.coherentsolutions.utilities.DataProviders;
import com.sun.org.glassfish.gmbal.Description;
import lombok.SneakyThrows;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import static com.coherentsolutions.utilities.ByVariables.EmailLogin.EmailBoxPage.*;
import static com.coherentsolutions.utilities.ByVariables.EmailLogin.LoginPage.*;
import static com.coherentsolutions.utilities.ByVariables.EmailLogin.MainPage.LOGIN_BUTTON_ON_MAIN_PAGE;
import static com.coherentsolutions.utilities.Constants.Message.*;
import static com.coherentsolutions.utilities.Constants.Config.*;

public class LoginTest {
    private static WebDriver webDriver;
    private static WebDriverWait wait;
    private static final Logger LOGGER = LogManager.getLogger(LoginTest.class);

    @BeforeSuite
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAITER_DURATION));
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(EXPLICIT_WAITER_DURATION));

        LOGGER.info("Set up is done.");
    }

    @Test(dataProvider = "loginTest", dataProviderClass = DataProviders.class)
    @Description("Task 4 points 1-4: The test verifies the login to the email mechanism for two existing users. The test compares the username in the account with the username used to log in.")
    @SneakyThrows
    public void loginTest(String url, String username, String password) {
        LOGGER.info("Open main page " + url);
        webDriver.get(url);
        webDriver.findElement(LOGIN_BUTTON_ON_MAIN_PAGE).click();

        try {
            WebElement currentAccount = webDriver.findElement(CURRENT_ACCOUNT_VALUE);

            if (currentAccount.isDisplayed()) {
                currentAccount.click();
                webDriver.findElement(LOGIN_TO_ANOTHER_ACCOUNT_BUTTON).click();
            }
        } catch (NoSuchElementException e) {
            LOGGER.info(LOGIN_CURRENT_ACCOUNT);
        }

        LOGGER.info("Provide username: " + username);
        WebElement loginField = webDriver.findElement(LOGIN_FIELD);
        loginField.sendKeys(username);
        webDriver.findElement(LOGIN_BUTTON_ON_LOGIN_PAGE).click();

        LOGGER.info("Provide pass: " + password);
        WebElement passwordField = webDriver.findElement(PASSWORD_FIELD);
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
        //Adding Thread.sleep makes the test more stable because necessary element (username) can be loaded and verified but with explicit waiter Thread.sleep does not make any sense
        Thread.sleep(SLEEP_IN_MILLISECONDS);

        LOGGER.info("Verification");
        wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAME));
        WebElement accountName = webDriver.findElement(ACCOUNT_NAME);
        Assert.assertEquals(username, accountName.getText(), LOGIN_IS_NOT_SUCCESSFUL);

        LOGGER.info("Log out");
        accountName.click();
        WebElement logout = webDriver.findElement(LOGOUT_BUTTON);
        logout.click();

        LOGGER.info("Test is done");
    }

    @AfterSuite
    public static void cleanUp() {
        webDriver.quit();
        LOGGER.info("Clean up");
    }
}