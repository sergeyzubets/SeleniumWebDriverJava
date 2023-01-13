package com.coherentsolutions.junit.shop;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import shop.VirtualItem;

import java.util.stream.Stream;

import static com.coherentsolutions.junit.TestConstants.ShopConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static utilities.GenerateTestData.*;

public class VirtualItemTest {

    private static Stream<Arguments> provideParameters() {
        String name = getRandomString();
        double price = getRandomDouble();
        double sizeOnDisk = getRandomDouble();

        return Stream.of(
                Arguments.of(name, price, sizeOnDisk),
                Arguments.of("", -10, 0),
                Arguments.of(" ", 0, -10),
                Arguments.of(null, 10, 10));
    }

    @ParameterizedTest
    @Tag("Smoke")
    @MethodSource("provideParameters")
    @DisplayName("VirtualItem Test")
    public void newVirtualItem(String name, double price, double sizeOnDisk) {
        VirtualItem virtualItem = createNewVirtualItem(name, price, sizeOnDisk);

        assertAll("VirtualItem Name Test",
                () -> assertNotNull(virtualItem.getName(), EMPTY_OR_NULL_NAME),
                () -> assertTrue(virtualItem.getName().trim().length() > 0, EMPTY_OR_NULL_NAME),
                () -> assertEquals(name, virtualItem.getName(), "VirtualItem name" + VALUES_DO_NOT_MATCH));

        assertAll("VirtualItem Price Test",
                () -> assertTrue(virtualItem.getPrice() > 0, ZERO_OR_NEGATIVE_PRICE),
                () -> assertEquals(price, virtualItem.getPrice(), CALCULATION_ACCURACY, "VirtualItem price" + VALUES_DO_NOT_MATCH));

        assertAll("VirtualItem SizeOnDisk Test",
                () -> assertTrue(virtualItem.getSizeOnDisk() > 0, ZERO_OR_NEGATIVE_SIZE_ON_DISK),
                () -> assertEquals(sizeOnDisk, virtualItem.getSizeOnDisk(), CALCULATION_ACCURACY, "VirtualItem sizeOnDisk" + VALUES_DO_NOT_MATCH));
    }
}
