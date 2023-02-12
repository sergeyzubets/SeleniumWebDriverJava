package com.coherentsolutions.utilities;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public interface Constants {

    interface Config {
        Duration EXPLICIT_WAIT_DURATION = Duration.ofSeconds(10);
        Path PROPERTIES_CONFIG_FILE = Paths.get("src", "main", "resources", "config.properties");
        Path CHROME_DRIVER_LOCATION = Paths.get("src", "main", "resources", "webDrivers", "chromedriver", "v109", "chromedriver.exe");
        Path FIREFOX_DRIVER_LOCATION = Paths.get("src", "main", "resources", "webDrivers", "firefox", "geckodriver.exe");
        String UNKNOWN_BROWSER = "A config for the browser does not exist.";
    }

    interface TestErrorMessage {
        String LOGIN_IS_NOT_SUCCESSFUL = "Login is not successful.";
        String LOGOUT_IS_NOT_SUCCESSFUL = "Logout is not successful.";
    }
}