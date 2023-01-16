package com.coherentsolutions.shop;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import shop.VirtualItem;
import utilities.DataProviders;

import static utilities.GenerateTestData.*;
import static utilities.TestConstants.ShopConstants.*;

public class VirtualItemTest {
    private static final Logger logger = LogManager.getLogger();

    @Test(groups = {"Smoke", "Regression"}, dataProvider = "itemDataProvider", dataProviderClass = DataProviders.class)
    public void newVirtualItem(String name, double price, double sizeOnDisk) {
        VirtualItem virtualItem = createNewVirtualItem(name, price, sizeOnDisk);
        SoftAssert softAssert = new SoftAssert();

        try {
            softAssert.assertNotNull(virtualItem.getName(), EMPTY_OR_NULL_NAME);
            softAssert.assertTrue(virtualItem.getName().trim().length() > 0, EMPTY_OR_NULL_NAME);
            softAssert.assertEquals(virtualItem.getName(), name, "VirtualItem name" + VALUES_DO_NOT_MATCH);

            softAssert.assertTrue(virtualItem.getPrice() > 0, ZERO_OR_NEGATIVE_PRICE);
            softAssert.assertEquals(virtualItem.getPrice(), price, CALCULATION_ACCURACY, "VirtualItem price" + VALUES_DO_NOT_MATCH);

            softAssert.assertTrue(virtualItem.getSizeOnDisk() > 0, ZERO_OR_NEGATIVE_SIZE_ON_DISK);
            softAssert.assertEquals(virtualItem.getSizeOnDisk(), sizeOnDisk, CALCULATION_ACCURACY, "VirtualItem sizeOnDisk" + VALUES_DO_NOT_MATCH);

            softAssert.assertAll("VirtualItem Test with valid and invalid name, price, sizeOnDisk values");
        } catch (NullPointerException e) {
            logger.error(e);
            Assert.fail();
        }
    }
}