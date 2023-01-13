package com.coherentsolutions.junit.shop;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import shop.RealItem;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static com.coherentsolutions.junit.TestConstants.ShopConstants.*;
import static utilities.GenerateTestData.*;

public class RealItemTest {

    private static Stream<Arguments> provideParameters() {
        String name = getRandomString();
        double price = getRandomDouble();
        double weight = getRandomDouble();

        return Stream.of(
          Arguments.of(name, price, weight),
          Arguments.of("", -10, 0),
          Arguments.of(" ", 0, -10),
          Arguments.of(null, 10, 10));
    }

    @ParameterizedTest
    @Tag("Smoke")
    @MethodSource("provideParameters")
    @DisplayName("RealItem Test")
    public void newRealItem(String name, double price, double weight) {
        RealItem realItem = createNewRealItem(name, price, weight);

        assertAll("RealItem Name test",
                () -> assertNotNull(realItem.getName(), EMPTY_OR_NULL_NAME),
                () -> assertTrue(realItem.getName().trim().length() > 0, EMPTY_OR_NULL_NAME),
                () -> assertEquals(name, realItem.getName(), "RealItem name" + VALUES_DO_NOT_MATCH));

        assertAll("RealItem Price Test",
                () -> assertTrue(realItem.getPrice() > 0, ZERO_OR_NEGATIVE_PRICE),
                () -> assertEquals(price, realItem.getPrice(), CALCULATION_ACCURACY, "RealItem price" + VALUES_DO_NOT_MATCH));

        assertAll("RealItem Weight Test",
                () -> assertTrue(realItem.getWeight() > 0, ZERO_OR_NEGATIVE_WEIGHT),
                () -> assertEquals(weight, realItem.getWeight(), CALCULATION_ACCURACY, "RealItem weight" + VALUES_DO_NOT_MATCH));
    }
}
