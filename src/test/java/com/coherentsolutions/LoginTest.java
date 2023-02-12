package com.coherentsolutions;

import com.coherentsolutions.pages.EmailBoxPage;
import com.coherentsolutions.pages.LoginPage;
import com.coherentsolutions.pages.MainPage;
import com.coherentsolutions.pages.PasswordPage;
import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.coherentsolutions.driver.Driver.getDriverInstance;
import static com.coherentsolutions.utilities.Constants.TestErrorMessage.*;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class LoginTest {

    @BeforeEach
    public void setUp() {
        String url = getPropertyValue("loginPageUrl");
        getDriverInstance().getDriver().get(url);
        log.info("Open task page " + url);
    }

    @Test
    @Description("Task 5: The test verifies the login to the email mechanism. " +
            "The test compares the username in the account with the username used to log in.")
    public void loginTest() {
        MainPage mainPage = new MainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage();
        loginPage.fillInLoginField(getPropertyValue("user"));
        loginPage.clickLoginButton();

        PasswordPage passwordPage = new PasswordPage();
        passwordPage.fillInPasswordField(getPropertyValue("password"));
        passwordPage.clickLoginButton();

        EmailBoxPage emailBoxPage = new EmailBoxPage();
        String currentUserName = emailBoxPage.getAccountName();
        emailBoxPage.openUserMenu();
        emailBoxPage.clickLogoutButton();

        assertAll(
                () -> assertEquals(getPropertyValue("user"), currentUserName, LOGIN_IS_NOT_SUCCESSFUL),
                () -> assertTrue(mainPage.isPageOpened(), LOGOUT_IS_NOT_SUCCESSFUL));
    }

    @AfterEach
    public void cleanUp() {
        getDriverInstance().webDriverQuit();
    }
}