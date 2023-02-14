package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class PasswordPage extends BasePage {
    By loginPageHeader = By.cssSelector(".passp-title");
    By loginButton = By.xpath("//button[@id='passp:sign-in']");
    By passwordField = By.id("passp-field-passwd");

    public PasswordPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageHeader));
        log.info("PasswordPage is displayed.");
        return driver.findElement(loginPageHeader).isDisplayed();
    }

    public PasswordPage fillInPasswordField(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
        log.info("Provided password: " + password);
        return this;
    }

    public EmailBoxPage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        log.info("Click loginButton.");
        return new EmailBoxPage(driver);
    }
}