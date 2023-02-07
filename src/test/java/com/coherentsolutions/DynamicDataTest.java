package com.coherentsolutions;

import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.coherentsolutions.utilities.ServiceMethods.simplifyUserDetails;
import static com.coherentsolutions.utilities.constants.ByVariables.DynamicData.*;
import static com.coherentsolutions.utilities.constants.Constants.Message.*;

@Log4j2
public class DynamicDataTest extends BaseTest {

    @BeforeClass
    @Parameters("dynamicDataUrl")
    public void setUp(String url) {
        openPage(url);
    }

    @Test
    @Description("Task 4 point 7: The test works with dynamic data:" +
            "waits for a new user provided after clicking the button. " +
            "Getting the first name, last name, and photo makes the test pass.")
    public void multiSelectListTest() {
        getDriver().findElement(GET_NEW_USER_BUTTON).click();
        getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(NEW_USER_DETAILS, "loading...")));

        String userDetails = simplifyUserDetails(getDriver().findElement(NEW_USER_DETAILS));
        log.info("New user details: " + userDetails);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(userDetails.contains("First Name"), MISSED_FIRST_NAME);
        softAssert.assertTrue(userDetails.contains("Last Name"), MISSED_LAST_NAME);
        softAssert.assertTrue(getDriver().findElement(NEW_USER_PHOTO).isDisplayed(), MISSED_PHOTO);
        softAssert.assertAll();
    }
}