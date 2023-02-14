package com.coherentsolutions.pages;

import com.coherentsolutions.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = Driver.getDriverInstance().getWait();
    }

    protected abstract boolean isPageOpened();
}