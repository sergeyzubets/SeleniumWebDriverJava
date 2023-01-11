package utilities;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.logging.log4j.*;
import shop.*;

import java.io.*;
import java.nio.file.*;

public class GenerateTestData {
    private static final double TAX = 0.2;

    public static RealItem createNewRealItem(String name, double price, double weight) {
        RealItem realItem = new RealItem();
        realItem.setName(name);
        realItem.setPrice(price);
        realItem.setWeight(weight);
        return realItem;
    }

    public static VirtualItem createNewVirtualItem(String name, double price, double sizeOnDisk) {
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(name);
        virtualItem.setPrice(price);
        virtualItem.setSizeOnDisk(sizeOnDisk);
        return virtualItem;
    }

    @SneakyThrows
    public static void createTestJSONFile(String filename, Cart cart) {
        final Logger logger = LogManager.getLogger();
        Path filePath = Paths.get("src", "test", "resources", filename + ".json");

        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write(new Gson().toJson(cart));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static double getTotalAfterAddingItem(double realItemPrice, double virtualItemPrice) {
        return (1 + TAX) * realItemPrice + (1 + TAX) * virtualItemPrice;
    }

    public static String getRandomString() {
        return new Faker().name().fullName();
    }

    public static double getRandomDouble() {
        return new Faker().random().nextDouble();
    }
}
