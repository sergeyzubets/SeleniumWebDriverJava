package com.coherentsolutions.utilities;

import com.github.javafaker.Faker;

import java.util.*;

import static com.coherentsolutions.utilities.constants.Constants.Config.RANDOM_ITEMS_COUNT;

public class UtilMethods {
    private static final Faker FAKER = new Faker();

    public static List<Integer> getThreeUniqueInt(int size) {
        Set<Integer> integerSet = new HashSet<>();

        do {
            integerSet.add(new Random().nextInt(size));
        }
        while (integerSet.size() < RANDOM_ITEMS_COUNT);
        return new ArrayList<>(integerSet);
    }

    public static String getRandomString() {
        return FAKER.name().name();
    }

    public static int convertSalaryToInt(String salary) {
        salary = salary.replace("$", "")
                .replace(",", "")
                .replace("/y", "");
        return Integer.parseInt(salary);
    }
}