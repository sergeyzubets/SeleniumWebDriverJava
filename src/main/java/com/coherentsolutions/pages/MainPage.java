package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class MainPage extends BasePage {

    By loginButton = By.cssSelector(".Button2_view_default ");
    By mainPageHeader = By.id("root");

    public MainPage() {
        if (isPageOpened()) {
            log.info("MainPage is displayed.");
        }
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPageHeader));
        return driver.findElement(mainPageHeader).isDisplayed();
    }

    public void clickLoginButton() {
        if (isPageOpened()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
            driver.findElement(loginButton).click();
            log.info("Click loginButton.");
        }
    }
}