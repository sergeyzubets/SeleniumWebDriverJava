package com.coherentsolutions;

import com.coherentsolutions.listener.Listener;
import com.coherentsolutions.pages.EmailBoxPage;
import com.coherentsolutions.pages.LoginPage;
import com.coherentsolutions.pages.MainPage;
import com.coherentsolutions.pages.PasswordPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.coherentsolutions.utilities.Constants.TestErrorMessage.*;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;

@Slf4j
@Listeners({Listener.class})
public class LoginTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("T1")
    @Description("The test verifies the login to the email mechanism. " +
            "The test compares the username in the account with the username used to log in.")
    public void loginTest() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = openMainPage();
        softAssert.assertTrue(mainPage.isPageOpened(), MAIN_PAGE_IS_NOT_OPENED);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        softAssert.assertTrue(loginPage.isPageOpened(), LOGIN_PAGE_IS_NOT_OPENED);
        loginPage.fillInLoginField(getPropertyValue("user")).clickLoginButton();

        PasswordPage passwordPage = new PasswordPage(driver);
        softAssert.assertTrue(passwordPage.isPageOpened(), PASSWORD_PAGE_IS_NOT_OPENED);
        passwordPage.fillInPasswordField(getPropertyValue("password")).clickLoginButton();

        EmailBoxPage emailBoxPage = new EmailBoxPage(driver);
        softAssert.assertTrue(emailBoxPage.isPageOpened(), EMAIL_BOX_PAGE_IS_NOT_OPENED);
        String currentUserName = emailBoxPage.getAccountName();
        emailBoxPage.openUserMenu().clickLogoutButton();

        softAssert.assertTrue(mainPage.isPageOpened(), LOGOUT_IS_NOT_SUCCESSFUL);
        softAssert.assertEquals(getPropertyValue("user"), currentUserName, LOGIN_IS_NOT_SUCCESSFUL);
        softAssert.assertAll(TEST_FAILED);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("T2")
    @Description("The test logs in with invalid user password to validate failed scenario in the Allure report.")
    public void failedTest() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = openMainPage();
        softAssert.assertTrue(mainPage.isPageOpened(), MAIN_PAGE_IS_NOT_OPENED);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        softAssert.assertTrue(loginPage.isPageOpened(), LOGIN_PAGE_IS_NOT_OPENED);
        loginPage.fillInLoginField(getPropertyValue("user")).clickLoginButton();

        PasswordPage passwordPage = new PasswordPage(driver);
        softAssert.assertTrue(passwordPage.isPageOpened(), PASSWORD_PAGE_IS_NOT_OPENED);
        passwordPage.fillInPasswordField(getPropertyValue("password2")).clickLoginButton();

        EmailBoxPage emailBoxPage = new EmailBoxPage(driver);
        softAssert.assertTrue(emailBoxPage.isPageOpened(), EMAIL_BOX_PAGE_IS_NOT_OPENED);
        String currentUserName = emailBoxPage.getAccountName();
        emailBoxPage.openUserMenu().clickLogoutButton();

        softAssert.assertTrue(mainPage.isPageOpened(), LOGOUT_IS_NOT_SUCCESSFUL);
        softAssert.assertEquals(getPropertyValue("user"), currentUserName, LOGIN_IS_NOT_SUCCESSFUL);
        softAssert.assertAll(TEST_FAILED);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("T3")
    @Description("The test opens login page to validate failed scenario in the Allure report.")
    public void passedTest() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = openMainPage();
        softAssert.assertTrue(mainPage.isPageOpened(), MAIN_PAGE_IS_NOT_OPENED);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        softAssert.assertTrue(loginPage.isPageOpened(), LOGIN_PAGE_IS_NOT_OPENED);
        loginPage.fillInLoginField(getPropertyValue("user")).clickLoginButton();
        softAssert.assertAll(TEST_FAILED);
    }
}