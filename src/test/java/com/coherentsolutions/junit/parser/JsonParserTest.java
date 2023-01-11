package com.coherentsolutions.junit.parser;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.*;
import shop.*;

import java.io.File;
import java.nio.file.*;

import static com.coherentsolutions.junit.TestConstants.ParserConstants.*;
import static com.coherentsolutions.junit.TestConstants.ShopConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static utilities.GenerateTestData.*;

public class JsonParserTest {

    private Parser parser;
    private Cart cart;
    private RealItem realItem;
    private VirtualItem virtualItem;

    @BeforeEach
    public void setUpParsing() {
        parser = new JsonParser();
        realItem = createNewRealItem(REAL_ITEM_NAME_FOR_PARSING, ITEM_PRICE, VIRTUAL_ITEM_WEIGHT);
        virtualItem = createNewVirtualItem(VIRTUAL_ITEM_NAME_FOR_PARSING, ITEM_PRICE, VIRTUAL_ITEM_SIZE_ON_DISK);
    }

    @ParameterizedTest
    @Tag("Smoke")
    @DisplayName("Read From File Test")
    @ValueSource(strings = {"readFromFile", "readFromEmptyFile", "doesNotExist", "readFromFileWithInvalidData"})
    public void readFromFileTest(String filename) {
        cart = new Cart(filename);
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);

        File inputFile = new File(Paths.get("src", "test", "resources", filename + ".json").toUri());
        assertAll("Input File Test",
                () -> assertTrue(inputFile.exists(), filename + FILE_DOES_NOT_EXIST),
                () -> assertTrue(inputFile.length() > 0, filename + FILE_IS_EMPTY));

        Cart cartFormFile = parser.readFromFile(inputFile);
        assertEquals(getTotalAfterAddingItem(ITEM_PRICE, ITEM_PRICE), cartFormFile.getTotalPrice(), CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }

    @ParameterizedTest
    @Tag("Smoke")
    @DisplayName("Write to File Test")
    @ValueSource(strings = {"writeToFileTest"})
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
                FileUtils.readFileToString(expectedFile, "utf-8"),
                FileUtils.readFileToString(actualFile, "utf-8"),
                FILES_ARE_NOT_THE_SAME);

        Files.delete(filepath);
    }
}
