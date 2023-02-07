package com.coherentsolutions;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static com.coherentsolutions.utilities.driver.Driver.*;

@Log4j2
public abstract class BaseTest {

    protected void openPage(String url) {
        getDriverInstance().getDriver().get(url);
        log.info("Open task page " + url);
    }

    protected WebDriver getDriver() {
        return getDriverInstance().getDriver();
    }

    protected WebDriverWait getWait() {
        return getDriverInstance().getWait();
    }

    @AfterSuite(alwaysRun = true)
    protected void cleanUp() {
        getDriverInstance().webDriverQuit();
    }
}