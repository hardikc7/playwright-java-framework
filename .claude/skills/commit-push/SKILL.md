---
description: Stage relevant project files, commit with the provided message, and push to main.
argument-hint: <commit message>
---

Stage, commit, and push changes to main.

1. Run git status to identify modified and untracked files.
   Do NOT stage: node_modules/, playwright-report/, test-results/, .env, .DS_Store.

2. Stage only relevant project files under: pages/, tests/, test-data/, docs/, config/, utils/, and root config files.

3. Commit with this exact format:
   git commit -m "$(cat <<'EOF'
   $ARGUMENTS

   Co-Authored-By: Claude Sonnet 4.6 <noreply@anthropic.com>
   EOF
   )"

4. Run: git push origin main

5. Report the short commit hash, message used, and push result.
