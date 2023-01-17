package com.coherentsolutions.shop;

import org.testng.annotations.*;
import shop.RealItem;
import utilities.DataProviders;

import static org.testng.Assert.*;
import static utilities.GenerateTestData.*;
import static utilities.TestConstants.ShopConstants.*;

public class RealItemTest {

    @Test(dataProvider = "itemDataProvider", dataProviderClass = DataProviders.class)
    public void newRealItem(String name, double price, double weight) {
        RealItem realItem = createNewRealItem(name, price, weight);

        assertNotNull(realItem.getName(), EMPTY_OR_NULL_NAME);
        assertTrue(realItem.getName().trim().length() > 0, EMPTY_OR_NULL_NAME);
        assertEquals(realItem.getName(), name, "RealItem name" + VALUES_DO_NOT_MATCH);

        assertTrue(realItem.getPrice() > 0, ZERO_OR_NEGATIVE_PRICE);
        assertEquals(realItem.getPrice(), price, CALCULATION_ACCURACY, "RealItem price" + VALUES_DO_NOT_MATCH);

        assertTrue(realItem.getWeight() > 0, ZERO_OR_NEGATIVE_WEIGHT);
        assertEquals(realItem.getWeight(), weight, CALCULATION_ACCURACY, "RealItem weight" + VALUES_DO_NOT_MATCH);
    }
}