package utilities;

import org.testng.annotations.DataProvider;

import static utilities.GenerateTestData.*;

public class DataProviders {

    @DataProvider(name = "itemDataProvider")
    public static Object[][] itemDataProvider() {
        return new Object[][]{
                {getRandomString(), getRandomDouble(), getRandomDouble()},
                {"", -10, 0},
                {" ", 0, -10},
                {null, 10, 10}};
    }

    @DataProvider(name = "cartDataProvider")
    public static Object[][] cartDataProvider() {
        return new Object[][]{
                {getRandomString()},
                {""},
                {" "},
                {null}};
    }

    @DataProvider(name = "fileDataProvider")
    public static Object[][] fileDataProvider() {
        return new Object[][]{
                {"readFromFile"},
                {"readFromEmptyFile"},
                {"doesNotExist"},
                {"readFromFileWithInvalidData"}};
    }
}