package com.coherentsolutions.utilities;

import org.openqa.selenium.By;

public interface ByVariables {

    interface EmailLogin {
        interface MainPage {
            By LOGIN_BUTTON_ON_MAIN_PAGE = By.xpath("//button[span[text()='Log in']]");
        }

        interface LoginPage {
            By LOGIN_FIELD = By.xpath("//input[@id='passp-field-login']");
            By LOGIN_BUTTON_ON_LOGIN_PAGE = By.xpath("//button[@id='passp:sign-in']");
            By PASSWORD_FIELD = By.xpath("//input[@id='passp-field-passwd']");
            By CURRENT_ACCOUNT_VALUE = By.cssSelector(".CurrentAccount");
            By LOGIN_TO_ANOTHER_ACCOUNT_BUTTON = By.cssSelector(".AddAccountButton-text");
        }

        interface EmailBoxPage {
            By ACCOUNT_NAME = By.xpath("//a[contains(@class, 'user-account')]");
            By LOGOUT_BUTTON = By.xpath("//span[contains(text(),'Log out')]");
        }
    }

    interface MultiSelect {
        By MULTI_SELECT_LIST = By.cssSelector("[id='multi-select'] option");
        By GET_SELECTED_ITEMS_BUTTON = By.id("printAll");
        By GET_LIST_OF_SELECTED_ITEMS = By.cssSelector(".getall-selected");
    }

    interface Alert {
        By CONFIRM_BOX_BUTTON = By.cssSelector("[onclick='myConfirmFunction()']");
        By CONFIRM_RESULT = By.id("confirm-demo");
        By PROMPT_BOX_BUTTON = By.cssSelector("[onclick='myPromptFunction()']");
        By PROMPT_RESULT = By.id("prompt-demo");
    }

    interface DynamicData {
        By GET_NEW_USER_BUTTON = By.id("save");
        By NEW_USER_DETAILS = By.id("loading");
        By NEW_USER_PHOTO = By.cssSelector("div[id='loading'] > img");
    }

    interface ProgressBar {
        By DOWNLOAD_BUTTON = By.id("cricle-btn");
        By DOWNLOAD_PROCESS_VALUE = By.cssSelector(".percenttext");
    }

    interface SortAnsSearch {
        By SHOW_ENTRIES_DROPDOWN_LIST = By.cssSelector("[name='example_length']");
        By NEXT_BUTTON = By.id("example_next");
        By DISABLED_NEXT_BUTTON = By.cssSelector("[class='paginate_button next disabled']");
        By EMPLOYEES_TABLE = By.cssSelector("[role='row'] > td");
    }
}