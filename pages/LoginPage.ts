import { Page } from '@playwright/test';
import { BasePage } from './BasePage';

export class LoginPage extends BasePage {
    constructor(page: Page) {
        super(page);
    }
    private readonly urlPath: string = '/practice-test-login/';
    private emailInput: string = '#username';
    private passwordInput: string = '#password';
    private loginButton: string = '#submit';
    private logoutLink: string = '.wp-block-button__link';
    private errorMessage: string = '#error';
    private successMessage: string = '.post-title';
    
    async navigateTo(): Promise<void> {
        await this.goToUrl(this.urlPath);
    }

    async login(email: string, password: string): Promise<void> {
        await this.fill(this.emailInput, email);
        await this.fill(this.passwordInput, password);
        await this.click(this.loginButton);
    }

    async clickLogout(): Promise<void> {
        await this.click(this.logoutLink);
    }

    async isLoginSuccessful(): Promise<boolean> {
        return await this.isVisible(this.logoutLink);
    }

    async isLogoutSuccessful(): Promise<boolean> {
        return await this.isVisible(this.loginButton);
    }

    async getErrorMessage(): Promise<string> {
        return await this.getText(this.errorMessage);
    }
}