package com.coherentsolutions;

import com.coherentsolutions.pages.EmailBoxPage;
import com.coherentsolutions.pages.MainPage;
import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;
import static com.coherentsolutions.utilities.Constants.TestErrorMessage.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        EmailBoxPage emailBoxPage = new MainPage()
                .clickLoginButton()
                .fillInLoginField(getPropertyValue("user"))
                .clickLoginButton()
                .fillInPasswordField(getPropertyValue("password"))
                .clickLoginButton();

        assertEquals(LOGIN_IS_NOT_SUCCESSFUL, getPropertyValue("user"), emailBoxPage.getAccountName());

        MainPage mainPage = emailBoxPage
                .openUserMenu()
                .clickLogoutButton();

        assertTrue(LOGOUT_IS_NOT_SUCCESSFUL, mainPage.isPageOpened());
    }
}