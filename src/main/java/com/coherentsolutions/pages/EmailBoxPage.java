package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class EmailBoxPage extends PasswordPage {

    @FindBy(xpath = "//a[contains(@class, 'user-account')]")
    private WebElement accountName;

    @FindBy(xpath = "//span[contains(text(),'Log out')]")
    private WebElement logoutButton;

    public EmailBoxPage() {
        if (isPageOpened()) {
            log.info("EmailBoxPage is displayed.");
        }
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(accountName));
        return accountName.isDisplayed();
    }

    public String getAccountName() {
        String result = null;
        if (isPageOpened()) {
            result = accountName.getText();
            log.info("Current account name is: " + result);
        }
        return result;
    }

    public EmailBoxPage openUserMenu() {
        wait.until(ExpectedConditions.visibilityOf(accountName)).isDisplayed();
        accountName.click();
        log.info("openUserMenu.");
        return this;
    }

    public MainPage clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton)).isDisplayed();
        logoutButton.click();
        log.info("Click logoutButton.");
        return new MainPage();
    }
}