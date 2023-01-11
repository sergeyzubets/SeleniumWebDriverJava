package com.coherentsolutions.junit.shop;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.GenerateTestData.*;
import static com.coherentsolutions.junit.TestConstants.ShopConstants.*;

public class CartTest {

    private Cart cart;

    private RealItem realItem;
    private double realItemPrice;

    private VirtualItem virtualItem;
    private double virtualItemPrice;

    @BeforeEach
    public void setUpCart() {
        String cartName = getRandomString();
        cart = new Cart(cartName);

        realItemPrice = getRandomDouble();
        realItem = createNewRealItem(getRandomString(), realItemPrice, getRandomDouble());

        virtualItemPrice = getRandomDouble();
        virtualItem = createNewVirtualItem(getRandomString(), virtualItemPrice, getRandomDouble());
    }

    private static Stream<Arguments> provideCartName() {
        return Stream.of(
                Arguments.of(getRandomString()),
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of((Object) null));
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Add RealItem to a Cart Test")
    public void addRealItemToCart() {
        cart.addRealItem(realItem);
        assertEquals(getTotalAfterAddingItem(realItemPrice, 0), cart.getTotalPrice(), CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }

    @ParameterizedTest
    @Tag("Smoke")
    @MethodSource("provideCartName")
    @DisplayName("New Cart Test")
    public void newCart(String cartName) {
        Cart cart = new Cart(cartName);

        assertAll("New Cart Test",
                () -> assertNotNull(cart.getCartName(), EMPTY_OR_NULL_NAME),
                () -> assertTrue(cart.getCartName().trim().length() > 0, EMPTY_OR_NULL_NAME),
                () -> assertEquals(cartName, cart.getCartName(), "cartName" + VALUES_DO_NOT_MATCH),
                () -> assertEquals(EMPTY_CART_TOTAL, cart.getTotalPrice(), CALCULATION_ACCURACY,
                        "total" + VALUES_DO_NOT_MATCH));
    }

    @Test
    @DisplayName("Delete RealItem from a Cart Test")
    public void deleteRealItem() {
        RealItem realItemToDelete = createNewRealItem(getRandomString(), getRandomDouble(), getRandomDouble());
        cart.addRealItem(realItem);
        double totalWithoutDeleteItem = getTotalAfterAddingItem(realItemPrice, 0);

        cart.addRealItem(realItemToDelete);
        cart.deleteRealItem(realItemToDelete);

        assertEquals(totalWithoutDeleteItem, cart.getTotalPrice(), CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Add VirtualItem to a Cart Test")
    public void addVirtualItemToCart() {
        cart.addVirtualItem(virtualItem);
        assertEquals(getTotalAfterAddingItem(0, virtualItemPrice), cart.getTotalPrice(), CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }

    @Test
    @DisplayName("Delete VirtualItem from a Cart Test")
    public void deleteVirtualItem() {
        VirtualItem virtualItemToDelete = createNewVirtualItem(getRandomString(), getRandomDouble(), getRandomDouble());
        cart.addVirtualItem(virtualItem);
        double totalWithoutDeleteItem = getTotalAfterAddingItem(0, virtualItemPrice);

        cart.addVirtualItem(virtualItemToDelete);
        cart.deleteVirtualItem(virtualItemToDelete);

        assertEquals(totalWithoutDeleteItem, cart.getTotalPrice(), CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }
}
