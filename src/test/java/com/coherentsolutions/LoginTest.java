package com.coherentsolutions;

import com.coherentsolutions.pages.EmailBoxPage;
import com.coherentsolutions.pages.LoginPage;
import com.coherentsolutions.pages.MainPage;
import com.coherentsolutions.pages.PasswordPage;
import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static com.coherentsolutions.driver.Driver.getDriverInstance;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;
import static com.coherentsolutions.utilities.Constants.TestErrorMessage.*;
import static org.junit.jupiter.api.Assertions.*;

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
    @Description("Task 5: The test verifies the login to the email mechanism. " +
            "The test compares the username in the account with the username used to log in.")
    public void loginTest() {
        MainPage mainPage = openMainPage();
        assertTrue(mainPage.isPageOpened(), MAIN_PAGE_IS_NOT_OPENED);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened(), LOGIN_PAGE_IS_NOT_OPENED);
        loginPage.fillInLoginField(getPropertyValue("user")).clickLoginButton();

        PasswordPage passwordPage = new PasswordPage(driver);
        assertTrue(passwordPage.isPageOpened(), PASSWORD_PAGE_IS_NOT_OPENED);
        passwordPage.fillInPasswordField(getPropertyValue("password")).clickLoginButton();

        EmailBoxPage emailBoxPage = new EmailBoxPage(driver);
        assertTrue(emailBoxPage.isPageOpened(), EMAIL_BOX_PAGE_IS_NOT_OPENED);
        String currentUserName = emailBoxPage.getAccountName();
        emailBoxPage.openUserMenu().clickLogoutButton();

        assertAll(
                () -> assertTrue(mainPage.isPageOpened(), LOGOUT_IS_NOT_SUCCESSFUL),
                () -> assertEquals(getPropertyValue("user"), currentUserName, LOGIN_IS_NOT_SUCCESSFUL));
    }

    @AfterEach
    public void cleanUp() {
        getDriverInstance().webDriverQuit();
    }
}