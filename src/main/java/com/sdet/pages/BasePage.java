package com.sdet.pages;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    // ✅ Navigate to URL
    public void goToUrl(String url) {
        page.navigate(url);
    }

    // ✅ Click element
    public void click(String selector) {
        page.click(selector);
    }

    // ✅ Type text
    public void fill(String selector, String text) {
        page.fill(selector, text);
    }

    // ✅ Get text
    public String getText(String selector) {
        return page.textContent(selector);
    }

    // ✅ Check if visible
    public boolean isVisible(String selector) {
        return page.isVisible(selector);
    }

    // ✅ Get page title
    public String getTitle() {
        return page.title();
    }

    // ✅ Take screenshot
    public void takeScreenshot(String path) {
        page.screenshot(new Page.ScreenshotOptions()
            .setPath(java.nio.file.Paths.get(path)));
    }
}