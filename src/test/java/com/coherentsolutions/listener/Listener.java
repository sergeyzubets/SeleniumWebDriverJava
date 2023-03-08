package com.coherentsolutions.listener;

import com.coherentsolutions.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class Listener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(iTestResult.getMethod().getConstructorOrMethod().getName() + " failed. See the screenshot attached.");
        isTestFailed = true;
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        isTestFailed = false;
    }
}