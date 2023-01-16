package utilities;

import org.apache.logging.log4j.*;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

import static utilities.GenerateTestData.*;
import static utilities.TestConstants.*;

public class DataProviders {
    private static final Logger logger = LogManager.getLogger();

    @DataProvider(name = "itemDataProvider")
    public static Object[][] itemDataProvider(Method method, ITestContext iTestContext) {
        String name = getRandomString();
        double price = getRandomDouble();
        double weight = getRandomDouble();
        double sizeOnDisk = getRandomDouble();

        switch (method.getName()) {
            case "newRealItem":
                for (String coverage : iTestContext.getIncludedGroups()) {
                    switch (coverage) {
                        case "Smoke":
                            return new Object[][]{
                                    {name, price, weight}};
                        case "Regression":
                            return new Object[][]{
                                    {name, price, weight},
                                    {"", -10, 0},
                                    {" ", 0, -10},
                                    {null, 10, 10}};
                        default:
                            logger.error("'" + coverage + "'" + UNKNOWN_COVERAGE);
                            return null;
                    }
                }
            case "newVirtualItem":
                for (String coverage : iTestContext.getIncludedGroups()) {
                    switch (coverage) {
                        case "Smoke":
                            return new Object[][]{
                                    {name, price, sizeOnDisk}};
                        case "Regression":
                            return new Object[][]{
                                    {name, price, sizeOnDisk},
                                    {"", -10, 0},
                                    {" ", 0, -10},
                                    {null, 10, 10}};
                        default:
                            logger.error("'" + coverage + "'" + UNKNOWN_COVERAGE);
                            return null;
                    }
                }
            default:
                logger.error("'" + method.getName() + "'" + TEST_WITHOUT_DATA_PROVIDER);
                return null;
        }
    }

    @DataProvider(name = "cartDataProvider")
    public static Object[][] cartDataProvider(Method method, ITestContext iTestContext) {

        switch (method.getName()) {
            case "newCart":
                for (String coverage : iTestContext.getIncludedGroups()) {
                    switch (coverage) {
                        case "Smoke":
                            return new Object[][]{
                                    {getRandomString()}};
                        case "Regression":
                            return new Object[][]{
                                    {getRandomString()},
                                    {""},
                                    {" "},
                                    {null}};
                        default:
                            logger.error("'" + coverage + "'" + UNKNOWN_COVERAGE);
                            return null;
                    }
                }
            default:
                logger.error("'" + method.getName() + "'" + TEST_WITHOUT_DATA_PROVIDER);
                return null;
        }
    }

    @DataProvider(name = "fileDataProvider")
    public static Object[][] fileDataProvider(Method method, ITestContext iTestContext) {

        switch (method.getName()) {
            case "readFromFileTest":
                double tax = Double.parseDouble(iTestContext.getCurrentXmlTest().getParameter("tax"));

                for (String coverage : iTestContext.getIncludedGroups()) {
                    switch (coverage) {
                        case "Smoke":
                            return new Object[][]{
                                    {"readFromFile", tax}};
                        case "Regression":
                            return new Object[][]{
                                    {"readFromFile", tax},
                                    {"readFromEmptyFile", tax},
                                    {"doesNotExist", tax},
                                    {"readFromFileWithInvalidData", tax}};
                        default:
                            logger.error(UNKNOWN_COVERAGE);
                            return null;
                    }
                }
            case "writeToFileTest":
                for (String coverage : iTestContext.getIncludedGroups()) {
                    switch (coverage) {
                        case "Smoke":
                        case "Regression":
                            return new Object[][]{
                                    {"writeToFileTest"}};
                        default:
                            logger.error("'" + coverage + "'" + UNKNOWN_COVERAGE);
                            return null;
                    }
                }
            default:
                logger.error("'" + method.getName() + "'" + TEST_WITHOUT_DATA_PROVIDER);
                return null;
        }
    }
}