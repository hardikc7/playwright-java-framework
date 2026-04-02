# Practice Test Login Page - Comprehensive Test Plan

## Application Overview

The Practice Test Login page (https://practicetestautomation.com/practice-test-login/) is a simple login form designed for automation practice. It contains a Username text field (id="username"), a Password field of type "password" (id="password", masked input), and a Submit button (id="submit"). The page displays a single error element (id="error") that is initially hidden via opacity:0 and becomes visible with opacity:1 when the "show" CSS class is applied after a failed login attempt. Valid credentials are username="student" and password="Password123". On successful login, the user is redirected to https://practicetestautomation.com/logged-in-successfully/ which shows a congratulations message and a "Log out" link. Both fields are not marked as HTML-required attributes, meaning the browser does not perform native validation - the application handles all validation. Authentication is case-sensitive for both username and password. The page also documents three test cases for users to practise against.

## Test Scenarios

### 1. Happy Path - Successful Login

**Seed:** `tests/seed.spec.ts`

#### 1.1. Login with valid credentials navigates to success page

**File:** `tests/login/happy-path.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The page title is 'Test Login | Practice Test Automation'
    - expect: The Username field, Password field, and Submit button are all visible
  2. Type 'student' into the Username field
    - expect: The Username field displays the text 'student'
  3. Type 'Password123' into the Password field
    - expect: The Password field accepts input and masks characters (shows dots or asterisks, not plain text)
  4. Click the Submit button
    - expect: The browser navigates away from the login page
    - expect: The URL changes to https://practicetestautomation.com/logged-in-successfully/
    - expect: The page title is 'Logged In Successfully | Practice Test Automation'
  5. Verify the content on the success page
    - expect: A heading 'Logged In Successfully' is displayed
    - expect: The text 'Congratulations student. You successfully logged in!' is visible on the page
    - expect: A 'Log out' link is displayed on the page

#### 1.2. Log out link on success page redirects back to login page

**File:** `tests/login/happy-path.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Enter 'student' in the Username field and 'Password123' in the Password field, then click Submit
    - expect: The user is redirected to https://practicetestautomation.com/logged-in-successfully/
  3. Click the 'Log out' link on the success page
    - expect: The browser navigates back to the login page
    - expect: The URL is https://practicetestautomation.com/practice-test-login/
    - expect: The Username field, Password field, and Submit button are all visible and empty
    - expect: No error message is displayed

#### 1.3. Login page initial state is correct

**File:** `tests/login/happy-path.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The page heading 'Test login' is visible
    - expect: The Username text field is empty and visible
    - expect: The Password field is empty, visible, and input is masked (type=password)
    - expect: The Submit button is present and enabled
    - expect: No error message is visible to the user (error element has opacity:0)
    - expect: The page shows the documented credentials: Username 'student', Password 'Password123'
    - expect: The page documents three test cases: positive login, negative username, negative password

### 2. Error Handling - Invalid Credentials

**Seed:** `tests/seed.spec.ts`

#### 2.1. Invalid username shows correct error message

**File:** `tests/login/error-handling.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads and the error message is not visible
  2. Type 'incorrectUser' into the Username field
    - expect: The Username field shows the text 'incorrectUser'
  3. Type 'Password123' into the Password field
    - expect: The Password field accepts the input
  4. Click the Submit button
    - expect: The page URL remains https://practicetestautomation.com/practice-test-login/
    - expect: The user is NOT redirected to the success page
    - expect: An error message becomes visible on the page
    - expect: The error message text reads exactly 'Your username is invalid!'
    - expect: The error element has the 'show' CSS class applied and opacity is 1

#### 2.2. Invalid password shows correct error message

**File:** `tests/login/error-handling.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads and the error message is not visible
  2. Type 'student' into the Username field
    - expect: The Username field shows 'student'
  3. Type 'incorrectPassword' into the Password field
    - expect: The Password field accepts the input
  4. Click the Submit button
    - expect: The page URL remains https://practicetestautomation.com/practice-test-login/
    - expect: The user is NOT redirected to the success page
    - expect: An error message becomes visible on the page
    - expect: The error message text reads exactly 'Your password is invalid!'
    - expect: The error element has the 'show' CSS class applied and opacity is 1

#### 2.3. Both fields empty shows username error message

**File:** `tests/login/error-handling.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads with both fields empty
  2. Leave the Username field empty (do not type anything)
    - expect: The Username field remains empty
  3. Leave the Password field empty (do not type anything)
    - expect: The Password field remains empty
  4. Click the Submit button
    - expect: The page URL remains https://practicetestautomation.com/practice-test-login/
    - expect: The user is NOT redirected to the success page
    - expect: An error message becomes visible on the page
    - expect: The error message text reads 'Your username is invalid!'
    - expect: The browser does NOT show native HTML5 validation popups (fields lack the 'required' attribute)

#### 2.4. Valid username with empty password shows password error

**File:** `tests/login/error-handling.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads with both fields empty
  2. Type 'student' into the Username field
    - expect: The Username field shows 'student'
  3. Leave the Password field empty
    - expect: The Password field remains empty
  4. Click the Submit button
    - expect: The page URL remains https://practicetestautomation.com/practice-test-login/
    - expect: The user is NOT redirected to the success page
    - expect: An error message becomes visible on the page
    - expect: The error message text reads 'Your password is invalid!'

### 3. Edge Cases - Input Validation and Boundary Conditions

**Seed:** `tests/seed.spec.ts`

#### 3.1. Username field is case-sensitive - uppercase first letter fails

**File:** `tests/login/edge-cases.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Type 'Student' (capital S) into the Username field
    - expect: The Username field displays 'Student'
  3. Type 'Password123' into the Password field
    - expect: The Password field accepts the input
  4. Click the Submit button
    - expect: The user is NOT logged in and NOT redirected to the success page
    - expect: The error message 'Your username is invalid!' is displayed
    - expect: This confirms authentication is case-sensitive for the username field

#### 3.2. Password field is case-sensitive - lowercase password fails

**File:** `tests/login/edge-cases.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Type 'student' into the Username field
    - expect: The Username field displays 'student'
  3. Type 'password123' (all lowercase) into the Password field
    - expect: The Password field accepts the input
  4. Click the Submit button
    - expect: The user is NOT logged in and NOT redirected to the success page
    - expect: The error message 'Your password is invalid!' is displayed
    - expect: This confirms authentication is case-sensitive for the password field

#### 3.3. Username field with leading and trailing whitespace fails

**File:** `tests/login/edge-cases.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Type ' student ' (with a space before and after) into the Username field
    - expect: The Username field displays the input with spaces
  3. Type 'Password123' into the Password field
    - expect: The Password field accepts the input
  4. Click the Submit button
    - expect: The user is NOT logged in
    - expect: An error message is displayed indicating the username is invalid

#### 3.4. Password field masks input characters

**File:** `tests/login/edge-cases.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Click on the Password field and type any characters
    - expect: The typed characters are NOT shown as plain text
    - expect: The input is masked (displayed as dots or asterisks)
    - expect: The Password field has type='password' attribute confirming masked input

#### 3.5. Submit button can be triggered via keyboard Enter key

**File:** `tests/login/edge-cases.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Type 'student' into the Username field
    - expect: The Username field shows 'student'
  3. Type 'Password123' into the Password field
    - expect: The Password field accepts the input
  4. Press the Enter key while focused on the Password field
    - expect: The form is submitted
    - expect: The user is redirected to https://practicetestautomation.com/logged-in-successfully/
    - expect: The success page is displayed with the congratulations message

#### 3.6. Special characters in username field fail authentication

**File:** `tests/login/edge-cases.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Type '<script>alert(1)</script>' into the Username field
    - expect: The field accepts the input as plain text without executing any script
  3. Type 'Password123' into the Password field
    - expect: The Password field accepts the input
  4. Click the Submit button
    - expect: The page remains on the login URL
    - expect: No script is executed
    - expect: An error message 'Your username is invalid!' is displayed
    - expect: The application handles the special characters safely

#### 3.7. Very long username input is handled gracefully

**File:** `tests/login/edge-cases.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Type a 500-character string of random alphanumeric characters into the Username field
    - expect: The field accepts the input without crashing or causing page errors
  3. Type 'Password123' into the Password field
    - expect: The Password field accepts the input
  4. Click the Submit button
    - expect: The page remains stable and does not crash
    - expect: An appropriate error message is displayed (expected: 'Your username is invalid!')
    - expect: The page URL remains on the login page

### 4. UI and Page Structure Verification

**Seed:** `tests/seed.spec.ts`

#### 4.1. Login page has all required UI elements

**File:** `tests/login/ui-verification.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The page heading 'Test login' (h2 level) is present and visible
    - expect: A Username label and text input field (id='username', type='text') are present
    - expect: A Password label and password input field (id='password', type='password') are present
    - expect: A Submit button (id='submit') is present and visible
    - expect: The top navigation bar contains links: Home, Practice, Courses, Blog, Contact
    - expect: The site logo 'Practice Test Automation' is displayed in the header
    - expect: The footer contains copyright text and links to Privacy Policy
    - expect: The page lists documented test case instructions for Positive LogIn, Negative username, and Negative password tests

#### 4.2. Error message element is hidden on initial page load

**File:** `tests/login/ui-verification.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads with no previous interaction
  2. Inspect the error element (id='error') without interacting with the form
    - expect: The error element exists in the DOM
    - expect: The error element is not visually visible to the user (opacity is 0)
    - expect: The error element does NOT have the 'show' CSS class applied
    - expect: The error message text 'Your username is invalid!' is present in the DOM but not displayed

#### 4.3. Success page contains all expected elements after login

**File:** `tests/login/ui-verification.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Enter credentials 'student' / 'Password123' and click Submit
    - expect: The user is redirected to the logged-in-successfully page
  3. Verify all elements on the success page
    - expect: Page URL is exactly https://practicetestautomation.com/logged-in-successfully/
    - expect: Page title is 'Logged In Successfully | Practice Test Automation'
    - expect: An h1 heading 'Logged In Successfully' is displayed
    - expect: A bold paragraph contains 'Congratulations student. You successfully logged in!'
    - expect: A 'Log out' link is present and clickable
    - expect: The Log out link href points back to the login page URL
    - expect: The site navigation bar is still visible with correct links
    - expect: The page footer is visible

#### 4.4. Skip to content link is accessible

**File:** `tests/login/ui-verification.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully
  2. Identify the 'Press Enter to skip to content' link at the top of the page
    - expect: A skip-to-content link exists that targets '#main-container'
    - expect: The link is accessible via keyboard navigation (Tab key from the top of the page)

### 5. Navigation and Page Flow

**Seed:** `tests/seed.spec.ts`

#### 5.1. Header navigation links work correctly from login page

**File:** `tests/login/navigation.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads successfully with the navigation bar
  2. Click the 'Home' link in the navigation bar
    - expect: The browser navigates to https://practicetestautomation.com/
  3. Navigate back to the login page and click the 'Practice' link
    - expect: The browser navigates to https://practicetestautomation.com/practice/
  4. Navigate back to the login page and click the 'Courses' link
    - expect: The browser navigates to https://practicetestautomation.com/courses/
  5. Navigate back to the login page and click the 'Blog' link
    - expect: The browser navigates to https://practicetestautomation.com/blog/
  6. Navigate back to the login page and click the 'Contact' link
    - expect: The browser navigates to https://practicetestautomation.com/contact/

#### 5.2. Site logo navigates to homepage from login page

**File:** `tests/login/navigation.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads with the site logo in the header
  2. Click the 'Practice Test Automation' logo/image in the header
    - expect: The browser navigates to https://practicetestautomation.com/
    - expect: The home page is displayed

#### 5.3. Footer Privacy Policy link is functional

**File:** `tests/login/navigation.spec.ts`

**Steps:**
  1. Navigate to https://practicetestautomation.com/practice-test-login/
    - expect: The login page loads with the footer visible
  2. Click the 'Privacy Policy' link in the footer
    - expect: The browser navigates to https://practicetestautomation.com/privacy-policy/
    - expect: The Privacy Policy page is displayed

#### 5.4. Directly navigating to success page URL without login

**File:** `tests/login/navigation.spec.ts`

**Steps:**
  1. Open a fresh browser and navigate directly to https://practicetestautomation.com/logged-in-successfully/
    - expect: The page loads at the given URL
    - expect: Note the behaviour: observe whether the page is accessible without authentication or if the user is redirected to the login page
