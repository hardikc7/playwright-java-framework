package com.sdet;

import com.microsoft.playwright.Page;
import com.sdet.pages.LoginPage;
import com.sdet.utils.BrowserManager;
import com.sdet.utils.TestDataReader;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    private Page page;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        page = BrowserManager.getPage();
        loginPage = new LoginPage(page);
        loginPage.goToLoginPage();
    }

    // ✅ Reads from JSON file
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return TestDataReader.getLoginData(
            "src/test/resources/testdata/loginData.json");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username,
                          String password,
                          String expectedMessage,
                          boolean shouldPass) {

        loginPage.login(username, password);
        String message = loginPage.getFlashMessage();

        if (shouldPass) {
            Assert.assertTrue(
                message.contains(expectedMessage),
                "Valid login failed — Got: " + message);
        } else {
            Assert.assertTrue(
                message.contains(expectedMessage),
                "Error message wrong — Got: " + message);
        }
    }

    @AfterMethod
    public void tearDown() {
        BrowserManager.closeBrowser();
    }
}