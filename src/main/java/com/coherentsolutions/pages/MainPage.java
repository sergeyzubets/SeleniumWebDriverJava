package com.coherentsolutions.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class MainPage extends BasePage {

    @FindBy(css = ".Button2_view_default ")
    private WebElement loginButton;

    @FindBy(id = "root")
    private WebElement mainPageHeader;

    public MainPage() {
        if (isPageOpened()) {
            log.info("MainPage is displayed.");
        }
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(mainPageHeader));
        return mainPageHeader.isDisplayed();
    }

    public LoginPage clickLoginButton() {
        if (isPageOpened()) {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            loginButton.click();
            log.info("Click loginButton.");
        }
        return new LoginPage();
    }
}