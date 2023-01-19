package com.coherentsolutions;

import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;

public class Main {
    public static void main(String[] args) {

        getPropertyValue("loginPageUrl");
        System.out.println("result: " + getPropertyValue(null));
        System.out.println("result: " + getPropertyValue("login.page.url"));

    }
}
