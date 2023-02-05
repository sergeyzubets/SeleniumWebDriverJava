package com.coherentsolutions;

import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static com.coherentsolutions.utilities.ServiceMethods.*;
import static com.coherentsolutions.utilities.constants.Constants.Message.MULTISELECT_FAIL;

@Log4j2
public class MultiSelectTest extends BaseTest {

    @BeforeClass
    @Parameters("multiSelectListUrl")
    public void setUp(String url) {
        openPage(url);
    }

    @Test
    @Description("Task 4 point 5: The test verifies selected items in multi select list element.")
    public void multiSelectListTest() {
        List<Integer> optionPositions = getRandomOptionPositions(getWebDriver());
        selectOptionsInMultiSelectList(getWebDriver(), optionPositions);

        Assert.assertEquals(
                getActualResult(getWebDriver()),
                getExpectedResult(getWebDriver(), optionPositions),
                MULTISELECT_FAIL);
    }
}