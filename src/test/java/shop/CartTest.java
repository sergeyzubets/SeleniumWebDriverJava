package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.GenerateTestData.*;
import static utilities.ItemFactory.*;
import static utilities.TestConstants.ShopConstants.*;

public class CartTest {
    private Cart cart;
    private RealItem realItem;
    private VirtualItem virtualItem;
    private double total;
    private String cartName;
    private String itemName;
    private double price;
    private double weight;
    private double setSizeOnDisk;

    @BeforeEach
    void setUpCart() {
        cartName = generateRandomString();
        itemName = generateRandomString();
        price = generateRandomDouble();
        weight = generateRandomDouble();
        setSizeOnDisk = generateRandomDouble();
        cart = new Cart(cartName);
        realItem = createNewRealItem(itemName, price, weight);
        virtualItem = createNewVirtualItem(itemName, price, setSizeOnDisk);
    }

    @Test
    @Tag("Smoke")
    @DisplayName("New Cart Test")
    void newCart() {
        assertAll("New Cart Test",
                () -> assertEquals(cartName, cart.getCartName()),
                () -> assertEquals(0, getListOfRealItems(cart).size()),
                () -> assertEquals(0, getListOfVirtualItems(cart).size()),
                () -> assertEquals(0, cart.getTotalPrice(), CALCULATION_ACCURACY));
    }

    @Test
    @DisplayName("New Cart Validation Test")
    void newCartValidation() {
        Cart cart = new Cart(null);
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        total = calculateTotalPrice(cart);
        assertAll("New Cart Validation Test",
                () -> assertFalse(cart.getCartName().isEmpty()),
                () -> assertEquals(1, getListOfRealItems(cart).size()),
                () -> assertEquals(1, getListOfVirtualItems(cart).size()),
                () -> assertEquals(total, cart.getTotalPrice(), CALCULATION_ACCURACY));
    }

    @Test
    @Tag("Smoke")
    @DisplayName("addRealItem() Test")
    void addRealItem() {
        cart.addRealItem(realItem);
        total = calculateTotalPrice(cart);
        assertAll("Add new Real Item",
                () -> assertEquals(cartName, cart.getCartName()),
                () -> assertEquals(itemName, getListOfRealItems(cart).get(0).getName()),
                () -> assertEquals(price, getListOfRealItems(cart).get(0).getPrice(), CALCULATION_ACCURACY),
                () -> assertEquals(weight, getListOfRealItems(cart).get(0).getWeight(), CALCULATION_ACCURACY),
                () -> assertEquals(total, cart.getTotalPrice(), CALCULATION_ACCURACY));
    }

    @Test
    @Tag("Smoke")
    @DisplayName("addVirtualItem() Test")
    void addVirtualItem() {
        cart.addVirtualItem(virtualItem);
        total = calculateTotalPrice(cart);
        assertAll("Add new Virtual Item",
                () -> assertEquals(cartName, cart.getCartName()),
                () -> assertEquals(itemName, getListOfVirtualItems(cart).get(0).getName()),
                () -> assertEquals(price, getListOfVirtualItems(cart).get(0).getPrice(), CALCULATION_ACCURACY),
                () -> assertEquals(setSizeOnDisk, getListOfVirtualItems(cart).get(0).getSizeOnDisk(), CALCULATION_ACCURACY),
                () -> assertEquals(total, cart.getTotalPrice(), CALCULATION_ACCURACY));
    }

    @Test
    @Tag("Smoke")
    @DisplayName("deleteRealItem() Test")
    void deleteRealItem() {
        for (int i = 0; i < generateRandomBoundedInt(); i++) {
            cart.addRealItem(createNewRealItem());
        }
        RealItem realItemToDelete = getRealItemToDelete(cart);
        cart.deleteRealItem(realItemToDelete);
        assertAll("Remove a Real Item from the Cart",
                () -> assertNull(getListOfRealItems(cart).stream()
                        .filter(realItem -> realItem.equals(realItemToDelete))
                        .findAny()
                        .orElse(null)),
                () -> assertEquals(calculateTotalPrice(cart), cart.getTotalPrice()));
    }

    @Test
    @Tag("Smoke")
    @DisplayName("deleteVirtualItem() Test")
    void deleteVirtualItem() {
        for (int i = 0; i < generateRandomBoundedInt(); i++) {
            cart.addVirtualItem(createNewVirtualItem());
        }
        VirtualItem virtualItemToDelete = getVirtualItemToDelete(cart);
        cart.deleteVirtualItem(virtualItemToDelete);
        assertAll("Remove a Virtual Item from the Cart",
                () -> assertNull(getListOfVirtualItems(cart).stream()
                        .filter(virtualItem -> virtualItem.equals(virtualItemToDelete))
                        .findAny()
                        .orElse(null)),
                () -> assertEquals(calculateTotalPrice(cart), cart.getTotalPrice()));
    }

    @AfterEach
    void garbageCollector() {
        cart = null;
        Runtime.getRuntime().gc();
    }
}
