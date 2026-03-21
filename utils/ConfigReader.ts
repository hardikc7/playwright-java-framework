import * as fs from 'fs';
import * as path from 'path';

export class ConfigReader {
  private static config: Record<string, string> = {};

  static load(env: string = 'dev'): void {
    const filePath = path.resolve(__dirname, `../config/${env}.json`);
    const fileContent = fs.readFileSync(filePath, 'utf-8');
    this.config = JSON.parse(fileContent);
  }

  static get(key: string): string {
    const value = this.config[key];
    if (!value) {
      throw new Error(`Config key "${key}" not found`);
    }
    return value;
  }
}