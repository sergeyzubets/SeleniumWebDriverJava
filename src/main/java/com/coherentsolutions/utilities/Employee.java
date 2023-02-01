package com.coherentsolutions.utilities;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.coherentsolutions.utilities.ByVariables.SortAnsSearch.*;
import static com.coherentsolutions.utilities.Constants.Config.*;
import static com.coherentsolutions.utilities.UtilMethods.convertSalaryToInt;

@Setter
@Getter
public class Employee {
    private String Name;
    private String Position;
    private String Office;
    private int age;
    private int salary;

    public static List<Employee> sortEmployees(List<Employee> listOfEmployees) {
        return listOfEmployees.stream()
                .filter(employee -> employee.getAge() > MIN_AGE)
                .filter(employee -> employee.getSalary() <= MAX_SALARY)
                .collect(Collectors.toList());
    }

    public static List<Employee> parseEmployeeTable(List<Employee> listOfEmployees, WebDriver webDriver) {
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
        return listOfEmployees;
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
}