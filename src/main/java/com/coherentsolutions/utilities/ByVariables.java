package com.coherentsolutions.utilities;

import org.openqa.selenium.By;

public interface ByVariables {

    interface MainPage {
        By LOGIN_BUTTON_ON_MAIN_PAGE = By.xpath("//button[span[text()='Log in']]");
    }

    interface LoginPage {
        By LOGIN_FIELD = By.xpath("//input[@id='passp-field-login']");
        By LOGIN_BUTTON_ON_LOGIN_PAGE = By.xpath("//button[@id='passp:sign-in']");
        By PASSWORD_FIELD = By.xpath("//input[@id='passp-field-passwd']");
    }

    interface EmailBoxPage {
        By ACCOUNT_NAME = By.xpath("//a[contains(@class, 'user-account')]");
    }
}