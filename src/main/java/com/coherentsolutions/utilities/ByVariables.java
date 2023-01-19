package com.coherentsolutions.utilities;

import org.openqa.selenium.By;

public interface ByVariables {

    interface mainPage {
        By LOGIN_BUTTON_ON_MAIN_PAGE = By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/button[1]");
    }

    interface loginPage {
        By LOGIN_FIELD = By.xpath("//input[@id='passp-field-login']");
        By LOGIN_BUTTON_ON_LOGIN_PAGE = By.xpath("//button[@id='passp:sign-in']");
        By PASSWORD_FIELD = By.xpath("//input[@id='passp-field-passwd']");
    }

    interface emailBoxPage {
        By ACCOUNT_NAME = By.xpath("//body/div[@id='js-apps-container']/div[2]/div[7]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/a[1]/span[1]");
    }
}