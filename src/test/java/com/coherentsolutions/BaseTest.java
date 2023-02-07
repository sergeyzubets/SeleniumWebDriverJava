package com.coherentsolutions;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;

import static com.coherentsolutions.utilities.driver.Driver.*;

@Log4j2
public abstract class BaseTest {

    protected void openPage(String url) {
        getDriverInstance().getDriver().get(url);
        log.info("Open task page " + url);
    }

    @AfterSuite(alwaysRun = true)
    protected void cleanUp() {
        webDriverQuit();
    }
}