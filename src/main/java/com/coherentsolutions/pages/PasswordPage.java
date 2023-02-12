package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class PasswordPage extends LoginPage {

    @FindBy(css = ".passp-title")
    private WebElement loginPageHeader;

    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement loginButton;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    public PasswordPage() {
        if (isPageOpened()) {
            log.info("PasswordPage is displayed.");
        }
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(loginPageHeader));
        return loginPageHeader.isDisplayed();
    }

    public PasswordPage fillInPasswordField(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        log.info("Provided password: " + password);
        return this;
    }

    public EmailBoxPage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        log.info("Click loginButton.");
        return new EmailBoxPage();
    }
}