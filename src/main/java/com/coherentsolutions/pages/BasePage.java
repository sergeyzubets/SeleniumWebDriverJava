package com.coherentsolutions.pages;

import com.coherentsolutions.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = Driver.getDriverInstance().getDriver();
        wait = Driver.getDriverInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    protected abstract boolean isPageOpened();
}