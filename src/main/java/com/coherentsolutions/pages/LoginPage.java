package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class LoginPage extends BasePage {
    By loginPageHeader = By.cssSelector(".passp-add-account-page-title");
    By loginField = By.xpath("//input[@id='passp-field-login']");
    By loginButton = By.xpath("//button[@id='passp:sign-in']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageHeader));
        log.info("LoginPage is displayed.");
        return driver.findElement(loginPageHeader).isDisplayed();
    }

    public LoginPage fillInLoginField(String login) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginField));
        driver.findElement(loginField).sendKeys(login);
        log.info("Provided login: " + login);
        return this;
    }

    public PasswordPage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        log.info("Click loginButton.");
        return new PasswordPage(driver);
    }
}