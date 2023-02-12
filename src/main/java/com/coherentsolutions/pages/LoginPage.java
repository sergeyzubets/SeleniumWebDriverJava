package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class LoginPage extends BasePage {

    By loginPageHeader = By.cssSelector(".passp-add-account-page-title");
    By loginField = By.xpath("//input[@id='passp-field-login']");
    By loginButton = By.xpath("//button[@id='passp:sign-in']");

    public LoginPage() {
        if (isPageOpened()) {
            log.info("LoginPage is displayed.");
        }
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageHeader));
        return driver.findElement(loginPageHeader).isDisplayed();
    }

    public void fillInLoginField(String login) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginField));
        driver.findElement(loginField).sendKeys(login);
        log.info("Provided login: " + login);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        log.info("Click loginButton.");
    }
}