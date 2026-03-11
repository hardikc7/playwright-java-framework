package com.sdet.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // ✅ Test name + data set parameter
        String testName = result.getMethod().getMethodName();
        Object[] params = result.getParameters();
        if (params != null && params.length > 0) {
            testName = testName + " — " + params[0];
        }
        ExtentTest test = ExtentReportManager
            .getInstance()
            .createTest(testName);
        ExtentReportManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest()
            .log(Status.PASS, "Test Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getTest()
            .log(Status.FAIL, "Test Failed ❌")
            .log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest()
            .log(Status.SKIP, "Test Skipped ⚠️");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush();
    }
}