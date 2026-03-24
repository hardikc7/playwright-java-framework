---
description: Run Playwright tests for a specific feature or all tests.
argument-hint: [feature_name|all]
---

Run Playwright tests and report results clearly.

If $ARGUMENTS is empty or equals "all":
  Run: npx playwright test

If $ARGUMENTS is a feature name (e.g. "login", "element", "infiniteScroll"):
  Run: npx playwright test tests/$0.spec.ts

Report total passed, failed, and skipped.
If any tests fail, list each failing test name and its error.
End with a summary line: Results: X passed | Y failed | Z skipped
