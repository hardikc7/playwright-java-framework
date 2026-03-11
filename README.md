# Playwright Java Automation Framework

Production-grade test automation framework built with Playwright Java and TestNG following Page Object Model design pattern.

## Tech Stack
- Java 17
- Playwright 1.42.0
- TestNG 7.9.0
- Maven
- ExtentReports 5.1.1
- JSON Simple (test data)

## Framework Architecture
```
src/
├── main/java/com/sdet/
│   ├── pages/
│   │   ├── BasePage.java         # Parent class — all Playwright actions
│   │   └── LoginPage.java        # Login page object
│   └── utils/
│       ├── BrowserManager.java   # ThreadLocal browser management
│       ├── ConfigReader.java     # Reads config.properties
│       └── TestDataReader.java   # Reads JSON test data files
└── test/
    ├── java/com/sdet/
    │   ├── utils/
    │   │   ├── ExtentReportManager.java  # HTML report setup
    │   │   └── TestListener.java         # TestNG lifecycle hooks
    │   ├── LoginTest.java                # Data-driven login tests
    │   └── NetworkInterceptTest.java     # Network intercept tests
    └── resources/
        ├── config.properties             # Browser, baseUrl config
        └── testdata/
            └── loginData.json            # External test data
```

## Key Features
- **ThreadLocal BrowserManager** — thread-safe parallel execution
- **Browser switching** — Chrome, Firefox, Safari via config.properties
- **Page Object Model** — clean separation of page logic and tests
- **Data-driven testing** — JSON external test data via DataProvider
- **Network intercept** — mock APIs, block requests, log calls
- **ExtentReports** — dark theme HTML report with pass/fail details
- **Zero hardcoded values** — all config and test data externalized

## Setup & Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Clone and Run
```bash
git clone https://github.com/hardikc7/playwright-java-framework.git
cd playwright-java-framework
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
mvn test
```

### Switch Browser
Edit `src/test/resources/config.properties`:
```properties
browser=firefox   # chromium / firefox / webkit
```

## Test Results
```
Tests run: 7
Failures:  0
Errors:    0
```

## Test Scenarios

### Login Tests (Data-Driven)
| Test Case | Username | Expected | Status |
|-----------|----------|----------|--------|
| Valid login | tomsmith | Redirect to /secure | ✅ Pass |
| Invalid username | wronguser | "username is invalid" | ✅ Pass |
| Empty credentials | (empty) | "username is invalid" | ✅ Pass |

### Network Intercept Tests
| Test | What it does | Status |
|------|-------------|--------|
| testBlockImages | Blocks all image requests | ✅ Pass |
| testMockApiResponse | Returns fake JSON response | ✅ Pass |
| testLogNetworkCalls | Logs all requests and responses | ✅ Pass |
| testModifyRequestHeaders | Injects custom headers | ✅ Pass |

## Network Intercept — 3 Patterns
```java
// Block request completely
route.abort();

// Return fake response
route.fulfill(new Route.FulfillOptions()
    .setStatus(200)
    .setBody("{\"name\": \"Hardik\"}"));

// Modify and pass through
route.resume(new Route.ResumeOptions()
    .setHeaders(modifiedHeaders));
```

## Playwright vs Selenium

| | Playwright | Selenium |
|--|-----------|---------|
| Waits | Auto-wait built in | Explicit waits required |
| Locators | CSS strings | By.id(), By.xpath() |
| Network intercept | Built in | Not supported |
| Browser support | Chrome, Firefox, Safari | Chrome, Firefox, Edge |
| Speed | Faster | Slower |
| Setup | Simple | WebDriverManager needed |

## Design Decisions
- **ThreadLocal** over static Page — safe for TestNG parallel execution
- **BrowserContext per test** — isolated session, no cookie leakage
- **BasePage** handles all Playwright calls — pages stay clean
- **getFlashMessage()** handles both success and error — no duplicate locators
- **JSON test data** — non-technical team members can update without code changes