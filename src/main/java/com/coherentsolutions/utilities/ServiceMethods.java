package com.coherentsolutions.utilities;

import com.coherentsolutions.utilities.models.Employee;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

import static com.coherentsolutions.utilities.UtilMethods.getThreeUniqueInt;
import static com.coherentsolutions.utilities.constants.ByVariables.MultiSelect.*;
import static com.coherentsolutions.utilities.constants.ByVariables.SortAnsSearch.*;
import static com.coherentsolutions.utilities.constants.Constants.Config.*;
import static com.coherentsolutions.utilities.UtilMethods.convertSalaryToInt;

@Log4j2
public class ServiceMethods {
    public static List<Employee> sortEmployees(List<Employee> listOfEmployees) {
        return listOfEmployees.stream()
                .filter(employee -> employee.getAge() > MIN_AGE)
                .filter(employee -> employee.getSalary() <= MAX_SALARY)
                .collect(Collectors.toList());
    }

    public static void fillListOfEmployees(List<Employee> listOfEmployees, WebDriver webDriver) {
        parseTableToEmployees(listOfEmployees, webDriver.findElements(EMPLOYEES_TABLE));

        do {
            webDriver.findElement(NEXT_BUTTON).click();
            parseTableToEmployees(listOfEmployees, webDriver.findElements(EMPLOYEES_TABLE));

            try {
                if (webDriver.findElement(DISABLED_NEXT_BUTTON).isDisplayed()) {
                    break;
                }
            } catch (NoSuchElementException ignored) {
            }
        } while (true);
    }

    private static void parseTableToEmployees(List<Employee> listOfEmployees, List<WebElement> table) {
        for (int i = 0; i < table.size(); i += 6) {
            Employee employee = new Employee();

            employee.setName(table.get(i).getText());
            employee.setPosition(table.get(i + 1).getText());
            employee.setOffice(table.get(i + 2).getText());
            employee.setAge(Integer.parseInt(table.get(i + 3).getText()));
            employee.setSalary(convertSalaryToInt(table.get(i + 5).getText()));

            listOfEmployees.add(employee);
        }
    }

    public static void selectOptionsInMultiSelectList(WebDriver webDriver, List<Integer> optionPositions) {
        new Actions(webDriver).keyDown(Keys.CONTROL)
                .click(getListOfOptions(webDriver).get(optionPositions.get(0)))
                .click(getListOfOptions(webDriver).get(optionPositions.get(1)))
                .click(getListOfOptions(webDriver).get(optionPositions.get(2)))
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
    }

    private static List<WebElement> getListOfOptions(WebDriver webDriver) {
        return webDriver.findElements(MULTI_SELECT_LIST);
    }

    public static String getExpectedResult(WebDriver webDriver, List<Integer> optionPositions) {
        StringBuilder expectedResult = new StringBuilder();
        List<WebElement> listOfOptions = getListOfOptions(webDriver);

        optionPositions.forEach(item -> expectedResult.append(listOfOptions.get(item).getText()).append(","));
        expectedResult.deleteCharAt(expectedResult.length() - 1);

        log.info("Expected Result: " + expectedResult);
        return expectedResult.toString();
    }

    public static List<Integer> getRandomOptionPositions(WebDriver webDriver) {
        return getThreeUniqueInt(getListOfOptions(webDriver).size());
    }

    public static String getActualResult(WebDriver webDriver) {
        webDriver.findElement(GET_SELECTED_ITEMS_BUTTON).click();

        String actualResult = webDriver
                .findElement(GET_LIST_OF_SELECTED_ITEMS)
                .getText()
                .replace("Options selected are : ", "");

        log.info("Actual Result: " + actualResult);
        return actualResult;
    }

    public static String simplifyUserDetails(WebElement webElement) {
        return webElement.getText()
                .replace(" : ", ": ")
                .replace("\n", " ")
                .replace("  ", "")
                .replace("Last", ", Last");
    }
}