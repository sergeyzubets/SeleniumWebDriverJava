package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class EmailBoxPage extends BasePage {
    By accountName = By.xpath("//a[contains(@class, 'user-account')]");
    By logoutButton = By.xpath("//span[contains(text(),'Log out')]");

    public EmailBoxPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountName));
        log.info("EmailBoxPage is displayed.");
        return driver.findElement(accountName).isDisplayed();
    }

    public String getAccountName() {
        String result = driver.findElement(accountName).getText();
        log.info("Current account name is: " + result);
        return result;
    }

    public EmailBoxPage openUserMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountName)).isDisplayed();
        driver.findElement(accountName).click();
        log.info("openUserMenu.");
        return this;
    }

    public MainPage clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
        driver.findElement(logoutButton).click();
        log.info("Click logoutButton.");
        return new MainPage(driver);
    }
}