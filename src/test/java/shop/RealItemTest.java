package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utilities.GenerateTestData;
import utilities.ItemFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.TestConstants.ShopConstants.CALCULATION_ACCURACY;

public class RealItemTest {
    private RealItem realItem;
    private String name;
    private double price;
    private double weight;

    @BeforeEach
    void setUpRealItem() {
        name = GenerateTestData.generateRandomString();
        price = GenerateTestData.generateRandomDouble();
        weight = GenerateTestData.generateRandomDouble();
        realItem = ItemFactory.createNewRealItem(name, price, weight);
    }

    @Test
    @Tag("Smoke")
    @DisplayName("New RealItem Test")
    void newRealItem() {
        assertAll("New RealItem Test",
                () -> assertEquals(name, realItem.getName()),
                () -> assertEquals(price, realItem.getPrice(), CALCULATION_ACCURACY),
                () -> assertEquals(weight, realItem.getWeight(), CALCULATION_ACCURACY));
    }

    @Test
    @DisplayName("New RealItem Validation Test")
    void newRealItemValidation() {
        realItem = ItemFactory.createNewRealItem(null, 0, -100);
        assertAll("New RealItem Validation Test",
                () -> assertFalse(realItem.getName().isEmpty()),
                () -> assertTrue(realItem.getName().length() > 0),
                () -> assertTrue(realItem.getPrice() > 0),
                () -> assertTrue(realItem.getWeight() > 0));
    }
}
