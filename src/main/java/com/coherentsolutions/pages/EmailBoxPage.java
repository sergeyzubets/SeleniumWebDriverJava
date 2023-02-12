package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class EmailBoxPage extends BasePage {

    By accountName = By.xpath("//a[contains(@class, 'user-account')]");
    By logoutButton = By.xpath("//span[contains(text(),'Log out')]");

    public EmailBoxPage() {
        if (isPageOpened()) {
            log.info("EmailBoxPage is displayed.");
        }
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountName));
        return driver.findElement(accountName).isDisplayed();
    }

    public String getAccountName() {
        String result = null;
        if (isPageOpened()) {
            result = driver.findElement(accountName).getText();
            log.info("Current account name is: " + result);
        }
        return result;
    }

    public void openUserMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountName)).isDisplayed();
        driver.findElement(accountName).click();
        log.info("openUserMenu.");
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
        driver.findElement(logoutButton).click();
        log.info("Click logoutButton.");
    }
}