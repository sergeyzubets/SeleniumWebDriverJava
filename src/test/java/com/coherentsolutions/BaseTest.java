package com.coherentsolutions;

import com.coherentsolutions.utilities.driver.Driver;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;

@Log4j2
public abstract class BaseTest extends Driver {

    protected void openPage(String url) {
        getWebDriver().get(url);
        log.info("Open task page " + url);
    }

    @AfterSuite(alwaysRun = true)
    protected void cleanUp() {
        webDriverQuit();
    }
}