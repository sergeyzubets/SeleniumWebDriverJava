package com.coherentsolutions.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class MainPage extends BasePage {

    @FindBy(css = "[class*='Button2_view_default Button2_size_m']")
    private WebElement loginButton;

    @FindBy(id = "root")
    private WebElement mainPageHeader;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        log.info("Click loginButton.");
        return new LoginPage(driver);
    }

    @Step("Open main page.")
    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(mainPageHeader));
        log.info("MainPage is displayed.");
        return mainPageHeader.isDisplayed();
    }
}