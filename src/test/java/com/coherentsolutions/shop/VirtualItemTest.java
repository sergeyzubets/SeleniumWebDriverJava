package com.coherentsolutions.shop;

import org.testng.annotations.*;
import shop.VirtualItem;
import utilities.DataProviders;

import static org.testng.Assert.*;
import static utilities.GenerateTestData.*;
import static utilities.TestConstants.ShopConstants.*;

public class VirtualItemTest {

    @Test(dataProvider = "itemDataProvider", dataProviderClass = DataProviders.class)
    public void newVirtualItem(String name, double price, double sizeOnDisk) {
        VirtualItem virtualItem = createNewVirtualItem(name, price, sizeOnDisk);

        assertNotNull(virtualItem.getName(), EMPTY_OR_NULL_NAME);
        assertTrue(virtualItem.getName().trim().length() > 0, EMPTY_OR_NULL_NAME);
        assertEquals(virtualItem.getName(), name, "VirtualItem name" + VALUES_DO_NOT_MATCH);

        assertTrue(virtualItem.getPrice() > 0, ZERO_OR_NEGATIVE_PRICE);
        assertEquals(virtualItem.getPrice(), price, CALCULATION_ACCURACY, "VirtualItem price" + VALUES_DO_NOT_MATCH);

        assertTrue(virtualItem.getSizeOnDisk() > 0, ZERO_OR_NEGATIVE_SIZE_ON_DISK);
        assertEquals(virtualItem.getSizeOnDisk(), sizeOnDisk, CALCULATION_ACCURACY, "VirtualItem sizeOnDisk" + VALUES_DO_NOT_MATCH);
    }
}