package com.coherentsolutions;

import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;
import static com.coherentsolutions.utilities.constants.ByVariables.EmailLogin.EmailBoxPage.*;
import static com.coherentsolutions.utilities.constants.ByVariables.EmailLogin.LoginPage.*;
import static com.coherentsolutions.utilities.constants.ByVariables.EmailLogin.MainPage.LOGIN_BUTTON_ON_MAIN_PAGE;
import static com.coherentsolutions.utilities.constants.Constants.Message.*;
import static com.coherentsolutions.utilities.constants.Constants.Config.*;
import static com.coherentsolutions.utilities.driver.Driver.getWait;
import static com.coherentsolutions.utilities.driver.Driver.getWebDriver;

@Log4j2
public class LoginTest extends BaseTest {

    @BeforeClass
    @Parameters("loginPageUrl")
    public void setUp(String url) {
        openPage(url);
    }

    @Test(dataProvider = "loginTest")
    @Description("Task 4 points 1-4: The test verifies the login to the email mechanism for two existing users. " +
            "The test compares the username in the account with the username used to log in.")
    public void loginTest(String username, String password) throws InterruptedException {
        log.info("loginTest");
        getWebDriver().findElement(LOGIN_BUTTON_ON_MAIN_PAGE).click();

        try {
            WebElement currentAccount = getWebDriver().findElement(CURRENT_ACCOUNT_VALUE);

            if (currentAccount.isDisplayed()) {
                currentAccount.click();
                getWebDriver().findElement(LOGIN_TO_ANOTHER_ACCOUNT_BUTTON).click();
            }
        } catch (NoSuchElementException e) {
            log.info(LOGIN_CURRENT_ACCOUNT);
        }

        log.info("Provided username: " + username);
        WebElement loginField = getWebDriver().findElement(LOGIN_FIELD);
        loginField.sendKeys(username);
        getWebDriver().findElement(LOGIN_BUTTON_ON_LOGIN_PAGE).click();

        log.info("Provided password: " + password);
        WebElement passwordField = getWebDriver().findElement(PASSWORD_FIELD);
        passwordField.click();
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
        //Adding Thread.sleep makes the test more stable because necessary element (username) can be loaded and verified but with explicit waiter Thread.sleep does not make any sense
        Thread.sleep(SLEEP_IN_MILLISECONDS);

        log.info("Verification");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAME));
        WebElement accountName = getWebDriver().findElement(ACCOUNT_NAME);
        Assert.assertEquals(username, accountName.getText(), LOGIN_IS_NOT_SUCCESSFUL);

        log.info("Log out");
        accountName.click();
        WebElement logout = getWebDriver().findElement(LOGOUT_BUTTON);
        logout.click();
    }

    @DataProvider(name = "loginTest")
    public Object[][] loginTest() throws Exception {
        return new Object[][]{
                {getPropertyValue("user1"), getPropertyValue("password1")},
                {getPropertyValue("user2"), getPropertyValue("password2")}};
    }
}