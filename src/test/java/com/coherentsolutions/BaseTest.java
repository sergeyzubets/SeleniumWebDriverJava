package com.coherentsolutions;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;

import static com.coherentsolutions.driver.Driver.*;

@Slf4j
public abstract class BaseTest {

    protected void openPage(String url) {
        getDriverInstance().getDriver().get(url);
        log.info("Open task page " + url);
    }

    @After
    public void cleanUp() {
        getDriverInstance().webDriverQuit();
    }
}