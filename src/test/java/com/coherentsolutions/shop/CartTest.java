package com.coherentsolutions.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import shop.*;
import utilities.DataProviders;

import static org.testng.Assert.assertEquals;
import static utilities.GenerateTestData.*;
import static utilities.TestConstants.ShopConstants.*;

public class CartTest {

    private static final Logger logger = LogManager.getLogger();
    private Cart cart;

    private RealItem realItem;
    private double realItemPrice;

    private VirtualItem virtualItem;
    private double virtualItemPrice;

    @BeforeMethod(groups = {"Smoke", "Regression"})
    public void setUpCart() {
        String cartName = getRandomString();
        cart = new Cart(cartName);

        realItemPrice = getRandomDouble();
        realItem = createNewRealItem(getRandomString(), realItemPrice, getRandomDouble());

        virtualItemPrice = getRandomDouble();
        virtualItem = createNewVirtualItem(getRandomString(), virtualItemPrice, getRandomDouble());
    }

    @Test(groups = {"Smoke", "Regression"}, dataProvider = "cartDataProvider", dataProviderClass = DataProviders.class)
    public void newCart(String cartName) {
        Cart cart = new Cart(cartName);
        SoftAssert softAssert = new SoftAssert();

        try {
            softAssert.assertNotNull(cart.getCartName(), EMPTY_OR_NULL_NAME);
            softAssert.assertTrue(cart.getCartName().trim().length() > 0, EMPTY_OR_NULL_NAME);
            softAssert.assertEquals(cart.getCartName(), cartName, "cartName" + VALUES_DO_NOT_MATCH);
            softAssert.assertEquals(cart.getTotalPrice(), EMPTY_CART_TOTAL, CALCULATION_ACCURACY,
                    "total" + VALUES_DO_NOT_MATCH);

            softAssert.assertAll("Cart Test with valid and invalid cartName values");

        } catch (NullPointerException e) {
            logger.error(e);
            Assert.fail();
        }
    }

    @Test(groups = {"Smoke", "Regression"})
    @Parameters("tax")
    public void addRealItemToCart(double tax) {
        cart.addRealItem(realItem);
        double expectedTotal = getTotalAfterAddingItem(realItemPrice, 0, tax);
        assertEquals(cart.getTotalPrice(), expectedTotal, CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }

    @Test(groups = "Regression")
    @Parameters("tax")
    public void deleteRealItem(double tax) {
        RealItem realItemToDelete = createNewRealItem(getRandomString(), getRandomDouble(), getRandomDouble());
        cart.addRealItem(realItem);
        double expectedTotal = getTotalAfterAddingItem(realItemPrice, 0, tax);

        cart.addRealItem(realItemToDelete);
        cart.deleteRealItem(realItemToDelete);

        assertEquals(cart.getTotalPrice(), expectedTotal, CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }

    @Test(groups = {"Smoke", "Regression"})
    @Parameters("tax")
    public void addVirtualItemToCart(double tax) {
        cart.addVirtualItem(virtualItem);
        double expectedTotal = getTotalAfterAddingItem(0, virtualItemPrice, tax);
        assertEquals(cart.getTotalPrice(), expectedTotal, CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }

    @Test(groups = "Regression")
    @Parameters("tax")
    public void deleteVirtualItem(double tax) {
        VirtualItem virtualItemToDelete = createNewVirtualItem(getRandomString(), getRandomDouble(), getRandomDouble());
        cart.addVirtualItem(virtualItem);
        double expectedTotal = getTotalAfterAddingItem(0, virtualItemPrice, tax);

        cart.addVirtualItem(virtualItemToDelete);
        cart.deleteVirtualItem(virtualItemToDelete);

        assertEquals(cart.getTotalPrice(), expectedTotal, CALCULATION_ACCURACY,
                "total" + VALUES_DO_NOT_MATCH);
    }
}