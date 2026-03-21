import { Page } from "@playwright/test";

export class BasePage {
    protected page: Page;
    constructor(page: Page) {
        this.page = page;
    }
    async goToUrl(url: string): Promise<void> {
        await this.page.goto(url);
    }
    async click(selector: string): Promise<void> {
        await this.page.locator(selector).click();
    }
    async fill(selector: string, value: string): Promise<void> {
        await this.page.locator(selector).fill(value);
    }
    async getText(selector: string): Promise<string> {
        return await this.page.locator(selector).innerText();
    }

    async isVisible(selector: string): Promise<boolean> {
        return await this.page.locator(selector).isVisible();
    }

    async getTitle(): Promise<string> {
        return await this.page.title();
    }

    async takeScreenshot(name: string): Promise<void> {
        await this.page.screenshot({ path: `screenshots/${name}.png` });
    }
}