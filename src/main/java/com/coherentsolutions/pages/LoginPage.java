package com.coherentsolutions.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class LoginPage extends BasePage {

    @FindBy(css = ".passp-add-account-page-title")
    private WebElement loginPageHeader;

    @FindBy(xpath = "//input[@id='passp-field-login']")
    private WebElement loginField;

    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillInLoginField(String login) {
        wait.until(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(login);
        log.info("Provided login: " + login);
        return this;
    }

    public PasswordPage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        log.info("Click loginButton.");
        return new PasswordPage(driver);
    }

    @Step("Open login page.")
    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(loginPageHeader));
        log.info("LoginPage is displayed.");
        return loginPageHeader.isDisplayed();
    }
}