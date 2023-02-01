package com.coherentsolutions;

import com.coherentsolutions.utilities.DataProviders;
import com.coherentsolutions.utilities.Employee;
import com.sun.org.glassfish.gmbal.Description;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.coherentsolutions.utilities.ByVariables.SortAnsSearch.*;
import static com.coherentsolutions.utilities.Constants.Config.*;
import static com.coherentsolutions.utilities.Constants.Message.SORTING_TEST_FAIL;
import static com.coherentsolutions.utilities.Employee.parseEmployeeTable;
import static com.coherentsolutions.utilities.Employee.sortEmployees;

public class SortAndSearchTest {
    private static WebDriver webDriver;
    private static final Logger LOGGER = LogManager.getLogger(DynamicDataTest.class);

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION.toString());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        LOGGER.info("Set up is done.");
    }

    @Test(dataProvider = "sortAndSearch", dataProviderClass = DataProviders.class)
    @Description("Task 4 point 9: The test verifies sorted list of employees from the site data stored in the table")
    @SneakyThrows
    public void progressBarTest(String url) {
        //init
        LOGGER.info("Open task page " + url);
        webDriver.get(url);
        List<Employee> listOfEmployees = new ArrayList<>();
        new Select(webDriver.findElement(SHOW_ENTRIES_DROPDOWN_LIST)).selectByValue(SHOW_ENTRIES_VALUE);
        //get list of all employees
        parseEmployeeTable(listOfEmployees, webDriver);
        LOGGER.info("Size of listOfEmployees = " + listOfEmployees.size());
        List<Employee> sortedEmployees = sortEmployees(listOfEmployees);
        LOGGER.info("Size of sortedEmployees = " + sortedEmployees.size());
        //sorting
        Assert.assertEquals(sortedEmployees.size(), 2, SORTING_TEST_FAIL);
    }

    @AfterSuite
    public void cleanUp() {
        webDriver.quit();
        LOGGER.info("Clean up");
    }
}
