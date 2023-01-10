package utilities;

public interface TestConstants {

    interface ShopConstants {
        double CALCULATION_ACCURACY = 0.000_000_000_001d;
        double TAX = 0.2;
        int MIN_ITERATIONS_COUNT = 1;   //do not use a value less than 1 due to 0 does not make sense
        int MAX_ITERATIONS_COUNT = 10;
        int MIN_CARTS_COUNT = 5;   //do not use a value less than 1 due to 0 does not make sense
        int MAX_CARTS_COUNT = 10;
    }

    interface ParserConstants {
        //
    }
}
