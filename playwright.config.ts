import { defineConfig, devices } from '@playwright/test';
import { ConfigReader } from './utils/ConfigReader';

const env = process.env.ENV || 'dev';
ConfigReader.load(env);

export default defineConfig({
  testDir: './tests',
  timeout: 30000,
  retries: 1,
  reporter: [['html', { open: 'never' }]],
  use: {
    baseURL: ConfigReader.get('baseUrl'),
    headless: process.env.CI ? true : false,
    screenshot: 'only-on-failure',
    video: 'off',
  },
  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] },
    },
  ],
});