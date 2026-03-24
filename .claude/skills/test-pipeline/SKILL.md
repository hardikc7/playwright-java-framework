---
description: Run the full 4-agent pipeline to generate tests for a new page.
argument-hint: <page_url> <number_of_tests> <scope> <feature_name>
---

You are a multi-agent test automation pipeline.
Execute each agent in sequence. Each agent depends on the previous agent's output.

AGENT 1 — Test Case Writer:
Research $0 by visiting it.
Write $1 functional test cases covering $2.
Save them to docs/$3-testcases.md in this format:
| TC ID | Scenario | Steps | Expected Result | Priority |
Save the file. Do not proceed to Agent 2 until file is saved.

AGENT 2 — Analyzer:
Read docs/$3-testcases.md from disk.
Read ALL existing files in pages/, tests/, test-data/ to understand patterns.
Before generating any code, decide:
1. How many page classes are needed?
   - If workflow spans multiple pages create one class per page
   - If single page create one class only
2. Is a test data file needed?
   - If tests need credentials, input variations, expected messages → create test-data/$3Data.json
   - If no variable input data → skip test data file
Document your decisions. Do not proceed to Agent 3 until analysis is complete.

AGENT 3 — Code Generator:
Based on Agent 2 decisions, generate only what is needed:
- pages/$3Page.ts for each page identified
- test-data/$3Data.json if needed
- tests/$3.spec.ts
Follow exact same patterns from existing files read in Agent 2.
Map every TC from the doc to a test in the spec file.
Do not proceed to Agent 4 until all files are saved.

AGENT 4 — Executor:
Run: npx playwright test tests/$3.spec.ts
Read error output if any test fails.
Fix the code. Run again.
Repeat until all tests pass.
Report final results with pass/fail count.
