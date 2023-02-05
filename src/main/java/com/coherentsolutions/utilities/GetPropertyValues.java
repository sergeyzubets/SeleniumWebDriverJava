package com.coherentsolutions.utilities;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.InputStream;
import java.nio.file.*;
import java.util.Properties;

import static com.coherentsolutions.utilities.constants.Constants.Config.*;

@Log4j2
public class GetPropertyValues {

    @SneakyThrows
    public static String getPropertyValue(String property) {
        String value = null;

        try {
            Properties properties = new Properties();
            InputStream inputStream = Files.newInputStream(Paths.get("src", "main", "resources", PROPERTIES_CONFIG_FILE));

            properties.load(inputStream);
            value = properties.getProperty(property);
        } catch (NoSuchFileException e) {
            log.error(e.getMessage());
        }

        if (value == null) {
            log.error("Value for property " + property + " is null.");
            throw new Exception();
        }

        return value;
    }
}