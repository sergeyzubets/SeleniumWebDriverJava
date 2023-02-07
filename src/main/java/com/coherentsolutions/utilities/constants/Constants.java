package com.coherentsolutions.utilities.constants;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public interface Constants {

    interface Config {
        Duration IMPLICIT_WAIT_DURATION = Duration.ofSeconds(2);
        Duration EXPLICIT_WAIT_DURATION = Duration.ofSeconds(5);
        int SLEEP_IN_MILLISECONDS = 1000;
        String PROPERTIES_CONFIG_FILE = "config.properties";
        Path CHROME_DRIVER_LOCATION = Paths.get("src", "test", "resources", "webDrivers", "webDrivers/chromedriver", "v108", "chromedriver.exe");
        int RANDOM_ITEMS_COUNT = 3;
        int PERCENTAGE_MAX = 50;
        long WHILE_LIFETIME_SEC = 60;
        String SHOW_ENTRIES_VALUE = "10";
        int MIN_AGE = 50;
        int MAX_SALARY = 115_000;
    }

    interface Message {
        String LOGIN_IS_NOT_SUCCESSFUL = "Login is not successful.";
        String LOGIN_CURRENT_ACCOUNT = "Login in current account option is not used.";
        String MULTISELECT_FAIL = "Chosen options are not the same as the selected ones.";
        String PROMPT_ALERT_FAIL = "Entered and received values do not match.";
        String MISSED_FIRST_NAME = "First name is not displayed.";
        String MISSED_LAST_NAME = "Last name is not displayed.";
        String MISSED_PHOTO = "User photo is not displayed.";
        String DOWNLOAD_TEST_FAIL = "The page is not refreshed.";
        String SORTING_TEST_FAIL = "The page is not refreshed.";
    }
}