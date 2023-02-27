package com.coherentsolutions;

import com.coherentsolutions.pages.EmailBoxPage;
import com.coherentsolutions.pages.LoginPage;
import com.coherentsolutions.pages.MainPage;
import com.coherentsolutions.pages.PasswordPage;
import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.coherentsolutions.driver.Driver.getDriverInstance;
import static com.coherentsolutions.driver.Utils.makeScreenshot;
import static com.coherentsolutions.utilities.Constants.TestErrorMessage.*;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;

@Slf4j
public class LoginTest {
    private WebDriver driver;

    public MainPage openMainPage() {
        String url = getPropertyValue("loginPageUrl");
        driver = getDriverInstance().getDriver();
        driver.get(url);
        log.info("Open task page " + url);
        return new MainPage(driver);
    }

    @Test
    @Description("Task 6: The test performs the login action and makes screenshot of home page of yandex mail.")
    public void loginTest() {
        String userName = getPropertyValue("user");
        String password = getPropertyValue("password");
        MainPage mainPage = openMainPage();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(mainPage.isPageOpened(), MAIN_PAGE_IS_NOT_OPENED);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        softAssert.assertTrue(loginPage.isPageOpened(), LOGIN_PAGE_IS_NOT_OPENED);
        loginPage.fillInLoginField(userName).clickLoginButton();

        PasswordPage passwordPage = new PasswordPage(driver);
        softAssert.assertTrue(passwordPage.isPageOpened(), PASSWORD_PAGE_IS_NOT_OPENED);
        passwordPage.fillInPasswordField(password).clickLoginButton();

        EmailBoxPage emailBoxPage = new EmailBoxPage(driver);
        softAssert.assertTrue(emailBoxPage.isPageOpened(), EMAIL_BOX_PAGE_IS_NOT_OPENED);
        makeScreenshot(driver);

        String currentUserName = emailBoxPage.getAccountName();
        emailBoxPage.openUserMenu().clickLogoutButton();

        softAssert.assertTrue(mainPage.isPageOpened(), LOGOUT_IS_NOT_SUCCESSFUL);
        softAssert.assertEquals(userName, currentUserName, LOGIN_IS_NOT_SUCCESSFUL);
        softAssert.assertAll(TEST_FAILED);
    }

    @AfterSuite
    public void cleanUp() {
        getDriverInstance().webDriverQuit();
    }
}