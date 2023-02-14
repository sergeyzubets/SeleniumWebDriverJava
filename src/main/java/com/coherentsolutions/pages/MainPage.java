package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class MainPage extends BasePage {
    By loginButton = By.cssSelector(".Button2_view_default ");
    By mainPageHeader = By.id("root");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPageHeader));
        log.info("MainPage is displayed.");
        return driver.findElement(mainPageHeader).isDisplayed();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        log.info("Click loginButton.");
    }
}