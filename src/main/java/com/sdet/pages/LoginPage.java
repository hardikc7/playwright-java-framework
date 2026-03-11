package com.sdet.pages;

import com.microsoft.playwright.Page;
import com.sdet.utils.ConfigReader;

public class LoginPage extends BasePage {

    // ✅ Playwright uses CSS selectors by default
    private final String usernameField = "#username";
    private final String passwordField = "#password";
    private final String loginButton   = "button[type='submit']";
    private final String flashMessage  = "#flash";

    public LoginPage(Page page) {
        super(page);
    }

    public void goToLoginPage() {
        goToUrl(ConfigReader.get("baseUrl"));
    }

    public void enterUsername(String username) {
        fill(usernameField, username);
    }

    public void enterPassword(String password) {
        fill(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isLoginSuccessful() {
        return page.url().contains("/secure");
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}