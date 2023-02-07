package com.coherentsolutions;

import com.coherentsolutions.utilities.models.Employee;
import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static com.coherentsolutions.utilities.constants.ByVariables.SortAnsSearch.*;
import static com.coherentsolutions.utilities.constants.Constants.Config.*;
import static com.coherentsolutions.utilities.constants.Constants.Message.SORTING_TEST_FAIL;
import static com.coherentsolutions.utilities.ServiceMethods.*;
import static com.coherentsolutions.utilities.driver.Driver.getWebDriver;

@Log4j2
public class SortAndSearchTest extends BaseTest {

    @BeforeClass
    @Parameters("sortAndSearchUrl")
    public void setUp(String url) {
        openPage(url);
        new Select(getWebDriver()
                .findElement(SHOW_ENTRIES_DROPDOWN_LIST))
                .selectByValue(SHOW_ENTRIES_VALUE);
    }

    @Test
    @Description("Task 4 point 9: The test verifies sorted list of employees from the site data stored in the table")
    public void sortAndSearchTest() {
        List<Employee> listOfEmployees = new ArrayList<>();

        fillListOfEmployees(listOfEmployees, getWebDriver());
        log.info("Total amount of employees = " + listOfEmployees.size());

        List<Employee> sortedEmployees = sortEmployees(listOfEmployees);
        log.info("Employees match the criteria = " + sortedEmployees.size());

        Assert.assertEquals(sortedEmployees.size(), 2, SORTING_TEST_FAIL);
    }
}