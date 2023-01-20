package com.coherentsolutions.utilities;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface Constants {

    interface Config {
        int TIME_OUT_IN_SECONDS = 20;
        String PROPERTIES_CONFIG_FILE = "config.properties";
        Path CHROME_DRIVER_LOCATION = Paths.get("src", "test", "resources", "webDrivers", "chromedriver", "v108", "chromedriver.exe");
    }

    interface ErrorMessage {
        String LOGIN_IS_NOT_SUCCESSFUL = "Login is not successful.";
        String PROPERTY_DOES_NOT_EXIST = "The property does not exist.";
        String VALUE_DOES_NOT_EXIST = "The value does not exist.";
    }
}
