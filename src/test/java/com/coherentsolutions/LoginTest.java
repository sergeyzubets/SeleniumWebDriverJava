package com.coherentsolutions;

import lombok.SneakyThrows;
import org.apache.logging.log4j.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static com.coherentsolutions.utilities.ByVariables.EmailBoxPage.*;
import static com.coherentsolutions.utilities.ByVariables.LoginPage.*;
import static com.coherentsolutions.utilities.ByVariables.MainPage.*;
import static com.coherentsolutions.utilities.Constants.TestProperties.*;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;

public class LoginTest {
    private static String url;
    private static String username;
    private static String password;
    private static WebDriver webDriver;
    private static WebDriverWait wait;
    private static final Logger LOGGER = LogManager.getLogger(LoginTest.class);

    @BeforeAll
    public static void setUp() {
        url = getPropertyValue("login.page.url");
        username = getPropertyValue("user");
        password = getPropertyValue("password");

        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver\\108\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));

        LOGGER.info("Set up is done.");
    }

    @SneakyThrows
    @Test
    public void loginTest() {
        LOGGER.info("Open main page " + url);
        webDriver.get(url);
        webDriver.findElement(LOGIN_BUTTON_ON_MAIN_PAGE).click();

        LOGGER.info("Provide username: " + username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FIELD));
        WebElement loginField = webDriver.findElement(LOGIN_FIELD);
        loginField.sendKeys(username);
        webDriver.findElement(LOGIN_BUTTON_ON_LOGIN_PAGE).click();

        LOGGER.info("Provide pass: " + password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        WebElement passwordField = webDriver.findElement(PASSWORD_FIELD);
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);

        LOGGER.info("Verification");
        wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAME));
        WebElement accountName = webDriver.findElement(ACCOUNT_NAME);
        Assertions.assertEquals(username, accountName.getText(), LOGIN_IS_NOT_SUCCESSFUL);
        LOGGER.info("Test is done");
    }

    @AfterAll
    public static void cleanUp() {
        webDriver.quit();
        LOGGER.info("Clean up");
    }
}