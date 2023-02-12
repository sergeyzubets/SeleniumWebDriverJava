package com.coherentsolutions;

import com.coherentsolutions.pages.EmailBoxPage;
import com.coherentsolutions.pages.LoginPage;
import com.coherentsolutions.pages.MainPage;
import com.coherentsolutions.pages.PasswordPage;
import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static com.coherentsolutions.utilities.Constants.TestErrorMessage.*;
import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.runners.Suite.*;

@Slf4j
@SuiteClasses(BaseTest.class)
public class LoginTest extends BaseTest {

    @Before
    public void setUp() {
        openPage(getPropertyValue("loginPageUrl"));
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
                () -> assertEquals(LOGIN_IS_NOT_SUCCESSFUL, getPropertyValue("user"), currentUserName),
                () -> assertTrue(LOGOUT_IS_NOT_SUCCESSFUL, mainPage.isPageOpened()));
    }
}