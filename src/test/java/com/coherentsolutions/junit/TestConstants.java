package com.coherentsolutions.junit;

public interface TestConstants {

    interface ShopConstants {
        double CALCULATION_ACCURACY = 0.000_000_000_001d;
        String EMPTY_OR_NULL_NAME = "The name cannot be empty or null.";
        String ZERO_OR_NEGATIVE_PRICE = "The price cannot be negative or equal to 0.";
        String ZERO_OR_NEGATIVE_WEIGHT = "The weight cannot be negative or equal to 0.";
        String ZERO_OR_NEGATIVE_SIZE_ON_DISK = "The size on disk cannot be negative or equal to 0.";
        String VALUES_DO_NOT_MATCH = " values do not match.";
        double EMPTY_CART_TOTAL = 0.0;
        String FILE_DOES_NOT_EXIST = "The file does not exist.";
        String FILE_IS_EMPTY = "The file does not contain any data.";
        String FILES_ARE_NOT_THE_SAME = "The written and actual files are not the same.";
    }

    interface ParserConstants {
        //changing the constants requires appropriate changes in read*.json files
        String REAL_ITEM_NAME_FOR_PARSING = "realItem 1";
        String VIRTUAL_ITEM_NAME_FOR_PARSING = "virtual 1";
        double ITEM_PRICE = 10;
        double VIRTUAL_ITEM_WEIGHT = 0.1;
        double VIRTUAL_ITEM_SIZE_ON_DISK = 0.2;
    }
}
