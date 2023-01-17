package com.coherentsolutions.shop;

import org.testng.annotations.*;
import shop.*;
import utilities.DataProviders;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;
import static utilities.GenerateTestData.*;
import static utilities.TestConstants.ShopConstants.*;

public class CartTest {
    private Cart cart;

    private RealItem realItem;
    private double realItemPrice;

    private VirtualItem virtualItem;
    private double virtualItemPrice;

    @BeforeMethod
    public void setUpCart() {
        String cartName = getRandomString();
        cart = new Cart(cartName);

        realItemPrice = getRandomDouble();
        realItem = createNewRealItem(getRandomString(), realItemPrice, getRandomDouble());

        virtualItemPrice = getRandomDouble();
        virtualItem = createNewVirtualItem(getRandomString(), virtualItemPrice, getRandomDouble());
    }

    @Test(dataProvider = "cartDataProvider", dataProviderClass = DataProviders.class)
    public void newCart(String cartName) {
        Cart cart = new Cart(cartName);
        assertNotNull(cart.getCartName(), EMPTY_OR_NULL_NAME);
        assertTrue(cart.getCartName().trim().length() > 0, EMPTY_OR_NULL_NAME);
        assertEquals(cart.getCartName(), cartName, "cartName" + VALUES_DO_NOT_MATCH);
        assertEquals(cart.getTotalPrice(), EMPTY_CART_TOTAL, CALCULATION_ACCURACY, "total" + VALUES_DO_NOT_MATCH);
    }

    @Test
    public void addRealItemToCart() {
        cart.addRealItem(realItem);
        double expectedTotal = getTotalAfterAddingItem(realItemPrice, 0, TAX);
        assertEquals(cart.getTotalPrice(), expectedTotal, CALCULATION_ACCURACY, "total" + VALUES_DO_NOT_MATCH);
    }

    @Test
    public void deleteRealItem() {
        RealItem realItemToDelete = createNewRealItem(getRandomString(), getRandomDouble(), getRandomDouble());
        cart.addRealItem(realItem);
        double expectedTotal = getTotalAfterAddingItem(realItemPrice, 0, TAX);

        cart.addRealItem(realItemToDelete);
        cart.deleteRealItem(realItemToDelete);

        assertEquals(cart.getTotalPrice(), expectedTotal, CALCULATION_ACCURACY, "total" + VALUES_DO_NOT_MATCH);
    }

    @Test
    public void addVirtualItemToCart() {
        cart.addVirtualItem(virtualItem);
        double expectedTotal = getTotalAfterAddingItem(0, virtualItemPrice, TAX);
        assertEquals(cart.getTotalPrice(), expectedTotal, CALCULATION_ACCURACY, "total" + VALUES_DO_NOT_MATCH);
    }

    @Test
    public void deleteVirtualItem() {
        VirtualItem virtualItemToDelete = createNewVirtualItem(getRandomString(), getRandomDouble(), getRandomDouble());
        cart.addVirtualItem(virtualItem);
        double expectedTotal = getTotalAfterAddingItem(0, virtualItemPrice, TAX);

        cart.addVirtualItem(virtualItemToDelete);
        cart.deleteVirtualItem(virtualItemToDelete);

        assertEquals(cart.getTotalPrice(), expectedTotal, CALCULATION_ACCURACY, "total" + VALUES_DO_NOT_MATCH);
    }
}