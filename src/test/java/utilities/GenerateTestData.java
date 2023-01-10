package utilities;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import shop.Cart;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static utilities.TestConstants.ShopConstants.*;

public class GenerateTestData {
    static Faker faker = new Faker();
    static Gson gson = new Gson();

    public static String generateRandomString() {
        return faker.name().fullName();
    }

    public static double generateRandomDouble() {
        return faker.random().nextDouble();
    }

    public static int generateRandomBoundedInt() {
        return faker.random().nextInt(MIN_ITERATIONS_COUNT, MAX_ITERATIONS_COUNT);
    }

    public static int generateDataSetAmount() {
        return faker.random().nextInt(MIN_CARTS_COUNT, MAX_CARTS_COUNT);
    }

    @SneakyThrows
    public static void generateTestJSONFile(String filename, Cart cart) {
        Path filePath = Paths.get("src", "test", "resources", filename);
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write(gson.toJson(cart));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
