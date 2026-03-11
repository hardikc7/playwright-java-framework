
package com.sdet.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserManager {

    private static ThreadLocal<Playwright> playwright 
        = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser 
        = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context 
        = new ThreadLocal<>();
    private static ThreadLocal<Page> page 
        = new ThreadLocal<>();

    // ✅ Launch browser and create page
    public static Page getPage() {
        if (playwright.get() == null) {
            playwright.set(Playwright.create());

            // ✅ Read browser from config — not hardcoded
            String browserName = ConfigReader.get("browser");
            BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions()
                    .setHeadless(false);

            Browser br;
            if (browserName.equalsIgnoreCase("firefox")) {
                br = playwright.get().firefox().launch(options);
            } else if (browserName.equalsIgnoreCase("webkit")) {
                br = playwright.get().webkit().launch(options);
            } else {
                // default → chromium
                br = playwright.get().chromium().launch(options);
            }

            browser.set(br);
            context.set(browser.get().newContext());
            page.set(context.get().newPage());
        }
        return page.get();
    }

    // ✅ Close everything cleanly
    public static void closeBrowser() {
        if (page.get() != null) page.get().close();
        if (context.get() != null) context.get().close();
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();

        page.remove();
        context.remove();
        browser.remove();
        playwright.remove();
    }
}
