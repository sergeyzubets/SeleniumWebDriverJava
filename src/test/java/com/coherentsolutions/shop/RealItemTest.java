package com.coherentsolutions.shop;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import shop.RealItem;
import utilities.DataProviders;

import static utilities.GenerateTestData.*;
import static utilities.TestConstants.ShopConstants.*;

public class RealItemTest {

    private static final Logger logger = LogManager.getLogger();

    @Test(groups = {"Smoke", "Regression"}, dataProvider = "itemDataProvider", dataProviderClass = DataProviders.class)
    public void newRealItem(String name, double price, double weight) {
        RealItem realItem = createNewRealItem(name, price, weight);
        SoftAssert softAssert = new SoftAssert();

        try {
            softAssert.assertNotNull(realItem.getName(), EMPTY_OR_NULL_NAME);
            softAssert.assertTrue(realItem.getName().trim().length() > 0, EMPTY_OR_NULL_NAME);
            softAssert.assertEquals(realItem.getName(), name, "RealItem name" + VALUES_DO_NOT_MATCH);

            softAssert.assertTrue(realItem.getPrice() > 0, ZERO_OR_NEGATIVE_PRICE);
            softAssert.assertEquals(realItem.getPrice(), price, CALCULATION_ACCURACY, "RealItem price" + VALUES_DO_NOT_MATCH);

            softAssert.assertTrue(realItem.getWeight() > 0, ZERO_OR_NEGATIVE_WEIGHT);
            softAssert.assertEquals(realItem.getWeight(), weight, CALCULATION_ACCURACY, "RealItem weight" + VALUES_DO_NOT_MATCH);

            softAssert.assertAll("RealItem Test with valid and invalid name, price, weight values");
        } catch (NullPointerException e) {
            logger.error(e);
            Assert.fail();
        }
    }
}