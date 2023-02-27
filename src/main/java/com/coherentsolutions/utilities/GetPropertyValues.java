package com.coherentsolutions.utilities;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import static com.coherentsolutions.utilities.Constants.Config.PROPERTIES_CONFIG_FILE;

@Slf4j
public class GetPropertyValues {

    public static String getPropertyValue(String property) {
        String value = null;
        InputStream inputStream;

        try {
            Properties properties = new Properties();
            inputStream = Files.newInputStream(PROPERTIES_CONFIG_FILE);

            properties.load(inputStream);
            value = properties.getProperty(property);
            inputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        if (value == null) {
            log.error("Value for property " + property + " is null.");
        }

        return value;
    }
}