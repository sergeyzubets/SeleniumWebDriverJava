package com.coherentsolutions.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class EmailBoxPage extends BasePage {

    @FindBy(xpath = "//a[contains(@class, 'user-account')]")
    private WebElement accountName;

    @FindBy(xpath = "//span[contains(text(),'Log out')]")
    private WebElement logoutButton;

    public EmailBoxPage(WebDriver driver) {
        super(driver);
    }

    public String getAccountName() {
        String result = accountName.getText();
        log.info("Current account name is: " + result);
        return result;
    }

    public EmailBoxPage openUserMenu() {
        wait.until(ExpectedConditions.visibilityOf(accountName)).isDisplayed();
        accountName.click();
        log.info("openUserMenu.");
        return this;
    }

    @Step("Logout.")
    public MainPage clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton)).isDisplayed();
        logoutButton.click();
        log.info("Click logoutButton.");
        return new MainPage(driver);
    }

    @Step("Open EmailBox page.")
    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(accountName));
        log.info("EmailBoxPage is displayed.");
        return accountName.isDisplayed();
    }
}