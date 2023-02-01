package com.coherentsolutions.utilities;

import org.testng.annotations.DataProvider;

import static com.coherentsolutions.utilities.GetPropertyValues.getPropertyValue;

public class DataProviders {

    @DataProvider(name = "loginTest")
    public static Object[][] loginTest() {
        return new Object[][]{
                {getPropertyValue("login.page.url"), getPropertyValue("user1"), getPropertyValue("password1")},
                {getPropertyValue("login.page.url"), getPropertyValue("user2"), getPropertyValue("password2")}};
    }

    @DataProvider(name = "multiSelectList")
    public static Object[][] multiSelectList() {
        return new Object[][]{
                {getPropertyValue("multi.select.list.url")}};
    }

    @DataProvider(name = "alerts")
    public static Object[][] alerts() {
        return new Object[][]{
                {getPropertyValue("alert.url"), "accept"},
                {getPropertyValue("alert.url"), "dismiss"}};
    }

    @DataProvider(name = "dynamicData")
    public static Object[][] dynamicData() {
        return new Object[][]{
                {getPropertyValue("dynamic.data.url")}};
    }

    @DataProvider(name = "progressBar")
    public static Object[][] progressBar() {
        return new Object[][]{
                {getPropertyValue("progress.bar.url")}};
    }

    @DataProvider(name = "sortAndSearch")
    public static Object[][] sortAndSearch() {
        return new Object[][]{
                {getPropertyValue("sort.and.search.url")}};
    }
}
