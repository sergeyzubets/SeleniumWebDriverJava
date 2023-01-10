package parser;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.GenerateTestData.*;
import static utilities.GenerateTestData.generateRandomString;
import static utilities.ItemFactory.*;
import static utilities.ItemFactory.getListOfRealItems;
import static utilities.TestConstants.ShopConstants.CALCULATION_ACCURACY;

public class JsonParserTest {
    private Parser parser;
    private Path filepath;
    private Cart cart;

    @BeforeEach
    void setUpCart() {
        parser = new JsonParser();
        String cartName = generateRandomString();
        String itemName = generateRandomString();
        double price = generateRandomDouble();
        double weight = generateRandomDouble();
        double setSizeOnDisk = generateRandomDouble();
        cart = new Cart(cartName);
        RealItem realItem = createNewRealItem(itemName, price, weight);
        VirtualItem virtualItem = createNewVirtualItem(itemName, price, setSizeOnDisk);
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
    }

    @ParameterizedTest
    @Tag("Smoke")
    @DisplayName("readFromFileTest() Test")
    @ValueSource(strings = {"readFromFileTest.json"})
    void readFromFileTest(String filename) {
        generateTestJSONFile(filename, cart);
        Path filepath = Paths.get("src", "test", "resources", filename);
        Cart cartFormFile = parser.readFromFile(new File(filepath.toUri()));
        assertAll("reading from JSON file",
                () -> assertEquals(cart.getCartName(), cartFormFile.getCartName()),
                () -> assertEquals(getListOfRealItems(cart).get(0).getName(), getListOfRealItems(cartFormFile).get(0).getName()),
                () -> assertEquals(getListOfRealItems(cart).get(0).getPrice(), getListOfRealItems(cartFormFile).get(0).getPrice()),
                () -> assertEquals(getListOfRealItems(cart).get(0).getWeight(), getListOfRealItems(cartFormFile).get(0).getWeight()),
                () -> assertEquals(getListOfVirtualItems(cart).get(0).getName(), getListOfVirtualItems(cartFormFile).get(0).getName()),
                () -> assertEquals(getListOfVirtualItems(cart).get(0).getPrice(), getListOfVirtualItems(cartFormFile).get(0).getPrice()),
                () -> assertEquals(getListOfVirtualItems(cart).get(0).getSizeOnDisk(), getListOfVirtualItems(cartFormFile).get(0).getSizeOnDisk()),
                () -> assertEquals(cart.getTotalPrice(), cartFormFile.getTotalPrice(), CALCULATION_ACCURACY));
    }

    @Disabled
    @Test
    @DisplayName("fileDoesNotExistTest() Test")
    @SneakyThrows
    void fileDoesNotExistTest() {
        Path filepath = Paths.get("src", "test", "resources", generateRandomString());
        Files.createFile(filepath);
        File inputFile = new File(filepath.toUri());
        parser.readFromFile(inputFile);
        deleteTestFile(filepath);
        assertFalse(inputFile.exists());
    }

    @ParameterizedTest
    @Tag("Smoke")
    @DisplayName("writeToFileTest() Test")
    @ValueSource(strings = {"writeToFileTest.json"})
    @SneakyThrows
    void writeToFileTest(String filename) {
        generateTestJSONFile(filename, cart);
        File expectedFile = new File(Paths.get("src", "test", "resources", filename).toUri());
        parser.writeToFile(cart);
        filepath = Paths.get("src", "main", "resources", cart.getCartName() + ".json");
        File actualFile = new File(filepath.toUri());
        assertEquals(
                FileUtils.readFileToString(expectedFile, "utf-8"),
                FileUtils.readFileToString(actualFile, "utf-8"));
    }

    @ParameterizedTest
    @DisplayName("readFromEmptyFileTest() Test")
    @ValueSource(strings = {"readFromEmptyFileTest.json"})
    @SneakyThrows
    void readFromEmptyFileTest(String filename) {
        filepath = Paths.get("src", "test", "resources", filename);
        Files.createFile(filepath);
        File inputFile = new File(filepath.toUri());
        parser.readFromFile(inputFile);
        assertAll(
                () -> assertTrue(inputFile.exists()),
                () -> assertEquals(0, inputFile.length()));
    }

    @ParameterizedTest
    @DisplayName("readFromFileWithInvalidTotalTest() Test")
    @ValueSource(strings = {"readFromFileWithInvalidTotalTest.json"})
        //@ValueSource(strings = {"readFromFileWithInvalidTotalTest2.json"})
    void readFromInvalidFileTest(String filename) {
        Path filepath = Paths.get("src", "test", "resources", filename);
        Cart cartFormFile = parser.readFromFile(new File(filepath.toUri()));
        assertEquals(calculateTotalPrice(cartFormFile), cartFormFile.getTotalPrice());
    }

    @ParameterizedTest
    @DisplayName("writeCardFromFileWithMultipleItemsTest() Test")
    @ValueSource(strings = {"writeCardFromFileWithMultipleItemsTest.json"})
    @SneakyThrows
    void writeCardFromFileWithMultipleItemsTest(String filename) {
        //init
        Cart cart = new Cart(filename);
        //generate data
        for (int i = 0; i < generateDataSetAmount(); i++) {
            RealItem realItem = createNewRealItem();
            VirtualItem virtualItem = createNewVirtualItem();
            cart.addRealItem(realItem);
            cart.addVirtualItem(virtualItem);
        }
        //write to JSON & test
        generateTestJSONFile(filename, cart);
        Path expectedFilePath = Paths.get("src", "test", "resources", filename);
        File expectedFile = new File(expectedFilePath.toUri());
        parser.writeToFile(cart);
        filepath = Paths.get("src", "main", "resources", cart.getCartName() + ".json");
        File actualFile = new File(filepath.toUri());
        assertEquals(
                FileUtils.readFileToString(expectedFile, "utf-8"),
                FileUtils.readFileToString(actualFile, "utf-8"));
    }

    @ParameterizedTest
    @DisplayName("readCardFromFileWithMultipleItemsTest() Test")
    @ValueSource(strings = {"readCardFromFileWithMultipleItemsTest.json"})
    @SneakyThrows
    void readCardFromFileWithMultipleItemsTest(String filename) {
        //init
        Cart cart = new Cart(filename);
        List<RealItem> realItemsExpectedList = new ArrayList<>();
        List<VirtualItem> virtualItemsExpectedList = new ArrayList<>();
        //generate data
        for (int i = 0; i < generateDataSetAmount(); i++) {
            RealItem realItem = createNewRealItem();
            VirtualItem virtualItem = createNewVirtualItem();
            cart.addRealItem(realItem);
            cart.addVirtualItem(virtualItem);
            realItemsExpectedList.add(realItem);
            virtualItemsExpectedList.add(virtualItem);
        }
        //write to JSON
        parser.writeToFile(cart);
        filepath = Paths.get("src", "main", "resources", cart.getCartName() + ".json");
        File actualFile = new File(filepath.toUri());
        //read from JSON & test
        Cart cartFormFile = parser.readFromFile(actualFile);
        assertAll("reading from JSON file",
                () -> assertEquals(cart.getCartName(), cartFormFile.getCartName()),
                () -> assertTrue(isRealItemListsEqual(realItemsExpectedList, getListOfRealItems(cartFormFile))),
                () -> assertTrue(isVirtualItemListsEqual(virtualItemsExpectedList, getListOfVirtualItems(cartFormFile))),
                () -> assertEquals(calculateTotalPrice(cart), cartFormFile.getTotalPrice(), CALCULATION_ACCURACY));
    }

    @ParameterizedTest
    @DisplayName("writeMultipleCardsFromFileTest() Test")
    @ValueSource(strings = {"readMultipleCardsFromFileTest.json"})
    @SneakyThrows
    void writeMultipleCardsFromFileTest(String filename) {
        //init
        List<Cart> listOfCarts = new ArrayList<>();
        List<Path> listOfExpectedFilePaths = new ArrayList<>();
        List<Path> listOfFilePaths = new ArrayList<>();
        List<File> listOfExpectedFiles = new ArrayList<>();
        List<File> listOfActualFiles = new ArrayList<>();
        //generate data
        for (int i = 0; i < generateDataSetAmount(); i++) {
            Cart cart = new Cart(filename + i);
            RealItem realItem = createNewRealItem();
            VirtualItem virtualItem = createNewVirtualItem();
            cart.addRealItem(realItem);
            cart.addVirtualItem(virtualItem);
            listOfCarts.add(cart);
        }
        //write to JSON & test
        listOfCarts.forEach(cart -> generateTestJSONFile(cart.getCartName() + ".json", cart));
        listOfCarts.forEach(cart -> listOfExpectedFilePaths.add(Paths.get("src", "test", "resources", cart.getCartName() + ".json")));
        listOfExpectedFilePaths.forEach(path -> listOfExpectedFiles.add(new File(path.toUri())));
        listOfCarts.forEach(cart -> parser.writeToFile(cart));
        listOfCarts.forEach(cart -> listOfFilePaths.add(Paths.get("src", "main", "resources", cart.getCartName() + ".json")));
        listOfFilePaths.forEach(path -> listOfActualFiles.add(new File(path.toUri())));
        for (int i = 0; i < listOfExpectedFiles.size(); i++) {
            assertEquals(
                    FileUtils.readFileToString(listOfExpectedFiles.get(i), "utf-8"),
                    FileUtils.readFileToString(listOfActualFiles.get(i), "utf-8"));
        }
        listOfFilePaths.forEach(JsonParserTest::deleteTestFile);
    }

    @ParameterizedTest
    @DisplayName("readMultipleCardsFromFileTest() Test")
    @ValueSource(strings = {"readMultipleCardsFromFileTest.json"})
    @SneakyThrows
    void readMultipleCardsFromFileTest(String filename) {
        //init
        List<Cart> listOfCarts = new ArrayList<>();
        List<Cart> listOfCartsFromFiles = new ArrayList<>();
        List<Path> listOfFilePaths = new ArrayList<>();
        List<File> listOfActualFiles = new ArrayList<>();
        List<RealItem> realItemsExpectedList = new ArrayList<>();
        List<VirtualItem> virtualItemsExpectedList = new ArrayList<>();
        //generate data
        for (int i = 0; i < generateDataSetAmount(); i++) {
            Cart cart = new Cart(filename + i);
            RealItem realItem = createNewRealItem();
            VirtualItem virtualItem = createNewVirtualItem();
            cart.addRealItem(realItem);
            cart.addVirtualItem(virtualItem);
            listOfCarts.add(cart);
            realItemsExpectedList.add(realItem);
            virtualItemsExpectedList.add(virtualItem);
        }
        //write to JSON
        listOfCarts.forEach(cart -> parser.writeToFile(cart));
        listOfCarts.forEach(cart -> listOfFilePaths.add(Paths.get("src", "main", "resources", cart.getCartName() + ".json")));
        listOfFilePaths.forEach(path -> listOfActualFiles.add(new File(path.toUri())));
        //read from JSON & test
        listOfActualFiles.forEach(actualFile -> listOfCartsFromFiles.add(parser.readFromFile(actualFile)));
        for (int i = 0; i < listOfCartsFromFiles.size(); i++) {
            int finalI = i;
            assertAll("reading from JSON files",
                    () -> assertEquals(listOfCarts.get(finalI).getCartName(), listOfCartsFromFiles.get(finalI).getCartName()),
                    () -> assertTrue(isRealItemListsEqual(realItemsExpectedList, getListOfRealItems(listOfCartsFromFiles.get(finalI)))),
                    () -> assertTrue(isVirtualItemListsEqual(virtualItemsExpectedList, getListOfVirtualItems(listOfCartsFromFiles.get(finalI)))),
                    () -> assertEquals(calculateTotalPrice(listOfCarts.get(finalI)), listOfCartsFromFiles.get(finalI).getTotalPrice(), CALCULATION_ACCURACY));
        }
        listOfFilePaths.forEach(JsonParserTest::deleteTestFile);
    }

    @AfterEach
    void garbageCollector() {
        deleteTestFile(filepath);
        cart = null;
        Runtime.getRuntime().gc();
    }

    @SneakyThrows
    private static void deleteTestFile(Path filepath) {
        if (filepath != null) {
            Files.delete(filepath);
        }
    }
}
