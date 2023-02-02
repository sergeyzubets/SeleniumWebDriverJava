package com.coherentsolutions.utilities;

import lombok.SneakyThrows;
import org.apache.logging.log4j.*;

import java.io.InputStream;
import java.nio.file.*;
import java.util.Properties;

import static com.coherentsolutions.utilities.constants.Constants.Config.*;

public class GetPropertyValues {
    private static final Logger LOGGER = LogManager.getLogger();

    @SneakyThrows
    public static String getPropertyValue(String property) {
        String value = null;

        try {
            Properties properties = new Properties();
            InputStream inputStream = Files.newInputStream(Paths.get("src", "main", "resources", PROPERTIES_CONFIG_FILE));

            properties.load(inputStream);
            value = properties.getProperty(property);
        } catch (NullPointerException | NoSuchFileException e) {
            LOGGER.error(e.getMessage());
        }

        return value;
    }
}