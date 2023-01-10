package shop;

import org.junit.jupiter.api.*;
import utilities.GenerateTestData;
import utilities.ItemFactory;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.TestConstants.ShopConstants.CALCULATION_ACCURACY;

public class VirtualItemTest {
    private VirtualItem virtualItem;
    private String name;
    private double price;
    private double sizeOnDisk;

    @BeforeEach
    void setUpVirtualItem() {
        name = GenerateTestData.generateRandomString();
        price = GenerateTestData.generateRandomDouble();
        sizeOnDisk = GenerateTestData.generateRandomDouble();
        virtualItem = ItemFactory.createNewVirtualItem(name, price, sizeOnDisk);
    }

    @Test
    @Tag("Smoke")
    @DisplayName("New VirtualItem Test")
    void newVirtualItem() {
        assertAll("New VirtualItem Test",
                () -> assertEquals(name, virtualItem.getName()),
                () -> assertEquals(price, virtualItem.getPrice(), CALCULATION_ACCURACY),
                () -> assertEquals(sizeOnDisk, virtualItem.getSizeOnDisk(), CALCULATION_ACCURACY));
    }

    @Test
    @DisplayName("New VirtualItem Validation Test")
    void newVirtualItemValidation() {
        virtualItem = ItemFactory.createNewVirtualItem(null, -10, 0);
        assertAll("New VirtualItem Validation Test",
                () -> assertFalse(virtualItem.getName().isEmpty()),
                () -> assertTrue(virtualItem.getName().length() > 0),
                () -> assertTrue(virtualItem.getPrice() > 0),
                () -> assertTrue(virtualItem.getSizeOnDisk() > 0));
    }
}
