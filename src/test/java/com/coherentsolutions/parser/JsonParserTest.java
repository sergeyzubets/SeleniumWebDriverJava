package com.coherentsolutions.parser;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import parser.*;
import shop.*;
import utilities.DataProviders;

import java.io.File;
import java.nio.file.*;

import static org.testng.Assert.assertEquals;
import static utilities.GenerateTestData.*;
import static utilities.TestConstants.ParserConstants.*;
import static utilities.TestConstants.ShopConstants.*;

public class JsonParserTest {

    private Cart cart;
    private RealItem realItem;
    private VirtualItem virtualItem;
    private static final Parser parser = new JsonParser();

    @BeforeMethod(groups = {"Smoke", "Regression"})
    public void setUpParsing() {
        realItem = createNewRealItem(REAL_ITEM_NAME_FOR_PARSING, ITEM_PRICE, VIRTUAL_ITEM_WEIGHT);
        virtualItem = createNewVirtualItem(VIRTUAL_ITEM_NAME_FOR_PARSING, ITEM_PRICE, VIRTUAL_ITEM_SIZE_ON_DISK);
    }

    @Test(groups = {"Smoke", "Regression"}, dataProvider = "fileDataProvider", dataProviderClass = DataProviders.class)
    @Parameters("tax")
    public void readFromFileTest(String filename, double tax) {
        cart = new Cart(filename);
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);

        File inputFile = new File(Paths.get("src", "test", "resources", filename + ".json").toUri());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(inputFile.exists(), filename + FILE_DOES_NOT_EXIST);
        softAssert.assertTrue(inputFile.length() > 0, filename + FILE_IS_EMPTY);
        softAssert.assertAll("Read to file Test with valid and invalid files");

        Cart cartFormFile = parser.readFromFile(inputFile);
        assertEquals(getTotalAfterAddingItem(ITEM_PRICE, ITEM_PRICE, tax), cartFormFile.getTotalPrice(), CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }

    @Ignore
    @Test(groups = {"Smoke", "Regression"}, dataProvider = "fileDataProvider", dataProviderClass = DataProviders.class)
    @SneakyThrows
    public void writeToFileTest(String filename) {
        cart = new Cart(filename);
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);

        createTestJSONFile(filename, cart);
        File expectedFile = new File(Paths.get("src", "test", "resources", filename + ".json").toUri());

        parser.writeToFile(cart);
        Path filepath = Paths.get("src", "main", "resources", cart.getCartName() + ".json");
        File actualFile = new File(filepath.toUri());

        assertEquals(
                FileUtils.readFileToString(actualFile, "utf-8"),
                FileUtils.readFileToString(expectedFile, "utf-8"),
                FILES_ARE_NOT_THE_SAME);

        Files.delete(filepath);
    }
}