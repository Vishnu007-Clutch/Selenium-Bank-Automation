# QA Playground Bank Automation Framework

A production-grade, beginner-friendly Selenium WebDriver automation testing framework designed for the **QA Playground Bank Demo Application** (`https://qaplayground.com/bank/login`).

Built with **Java**, **Selenium WebDriver**, **TestNG**, **Maven**, and the **Page Object Model (POM)** architectural pattern.

---

## 1. Technology Stack

* **Automation Framework:** Selenium WebDriver 4.29.0
* **Programming Language:** Java (JDK 17+)
* **Test Runner & Assertions:** TestNG 7.10.2
* **Build & Dependency Management:** Apache Maven
* **Browser Driver Management:** WebDriverManager 5.9.3
* **Design Pattern:** Page Object Model (POM) with PageFactory
* **Target Browser:** Google Chrome (Configurable Headed & Headless modes)
* **IDE Compatibility:** Eclipse IDE & IntelliJ IDEA

---

## 2. Project Architecture & Directory Structure

```text
QAPlaygroundBankAutomation/
│
├── pom.xml                               # Maven project dependencies & Surefire configuration
├── testng.xml                            # Master TestNG test suite XML
├── .project                              # Eclipse project metadata
├── .classpath                            # Eclipse classpath metadata
├── README.md                             # Comprehensive project documentation
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── personal/
│   │               ├── base/
│   │               │   ├── BasePage.java            # Reusable Selenium interactions & wait helpers
│   │               │   └── BaseTest.java            # Driver setup, teardown & screenshot hooks
│   │               │
│   │               ├── pages/
│   │               │   ├── LoginPage.java           # Authentication & test credentials
│   │               │   ├── DashboardPage.java       # Central hub, quick actions & navigation
│   │               │   ├── AccountsPage.java        # Accounts table & Add Account dialog
│   │               │   ├── TransferPage.java        # Funds transfer between user accounts
│   │               │   ├── SendMoneyPage.java       # Payee selection, transmission & Add Payee
│   │               │   ├── PayUtilitiesPage.java    # Biller search & utility bill settlement
│   │               │   ├── ApplyLoanPage.java       # Loan application modal & loan history
│   │               │   ├── TransactionsPage.java    # Transaction search, filters & download
│   │               │   ├── NotificationsPage.java   # System alerts & read status management
│   │               │   ├── ProfilePage.java         # Personal info & security password change
│   │               │   └── ForgotPasswordPage.java  # Password reset flow from login
│   │               │
│   │               └── utilities/
│   │                   ├── ConfigReader.java        # Reads config.properties
│   │                   ├── WaitUtils.java           # Explicit wait helper routines
│   │                   ├── ScreenshotUtils.java     # Automated capture on test failure
│   │                   └── TestData.java            # Centralized test values & messages
│   │
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── personal/
│       │           └── tests/
│       │               ├── LoginTest.java           # Authentication test scenarios
│       │               ├── DashboardTest.java       # Dashboard layout & navigation verification
│       │               ├── AccountsTest.java        # Account list & creation validations
│       │               ├── TransferTest.java        # Fund transfer positive & negative tests
│       │               ├── SendMoneyTest.java       # Payee transactions & modal tests
│       │               ├── PayUtilitiesTest.java    # Biller payments & validations
│       │               ├── ApplyLoanTest.java       # Loan applications & history filtering
│       │               ├── TransactionsTest.java    # History search & download verification
│       │               ├── NotificationsTest.java   # Notification alerts & mark read
│       │               ├── ProfileTest.java         # Profile updates & password mismatch test
│       │               └── ForgotPasswordTest.java  # Password reset request verification
│       │
│       └── resources/
│           └── config.properties                    # Environment & runtime configurations
│
└── screenshots/                                     # Automatically generated failure screenshots
```

---

## 3. High-Level Navigation Flow

```text
                     QA Playground Bank
                              │
                              ▼
                         Login Page
                              │
                              ▼
                        Dashboard Page
                              │
         ┌──────────────┬─────┴────────┬──────────────┐
         ▼              ▼              ▼              ▼
     Accounts       Transfer       Send Money    Pay Utilities
   (AccountsPage) (TransferPage) (SendMoneyPage) (PayUtilitiesPage)
         │              │              │              │
         ├──────────────┴──────────────┴──────────────┤
         │
         ┌──────────────┬──────────────┬──────────────┐
         ▼              ▼              ▼              ▼
    Apply Loan    Transactions   Notifications     Profile
  (ApplyLoanPage) (TxnsPage)    (NotifsPage)    (ProfilePage)
```

---

## 4. Key Package Responsibilities

### `com.personal.base`
* **`BasePage.java`**: Foundation for all Page Objects. Contains reusable element interactions (`click`, `jsClick`, `type`, `clearAndType`, `getText`, `isElementDisplayed`), explicit waits via `WebDriverWait`, and custom helpers to interact with modern **Radix UI Select** dropdowns.
* **`BaseTest.java`**: Manages browser lifecycle using TestNG `@BeforeMethod` and `@AfterMethod`. Configures ChromeOptions (headless toggle, maximized, sandbox flags) and automatically takes screenshots of failed test methods into `screenshots/`.

### `com.personal.pages`
* Strictly encapsulates **locators** and **page-specific actions**.
* Follows the **Page Object Model**: zero test assertions inside Page Objects.
* Returns new Page Object instances upon navigation (e.g., `loginPage.login(...)` returns `DashboardPage`).

### `com.personal.tests`
* Contains only **TestNG test classes**.
* Extends `BaseTest` for automatic browser initialization and teardown.
* Calls Page Object methods and uses standard TestNG assertions (`Assert.assertTrue`, `Assert.assertEquals`, `Assert.assertFalse`).
* Never contains raw `driver.findElement(By...)` calls.

### `com.personal.utilities`
* **`ConfigReader.java`**: Reads property keys from `src/test/resources/config.properties`.
* **`WaitUtils.java`**: Explicit wait utility for elements visibility, clickability, presence, and DOM ready state.
* **`ScreenshotUtils.java`**: Takes full-page screenshots using `TakesScreenshot` and writes them with formatted timestamps.
* **`TestData.java`**: Central repository for test users, invalid credentials, error message constants, and form data.

---

## 5. Automated Pages and Features

| Page / Feature | Page Object | Test Class | Key Scenarios Covered |
| :--- | :--- | :--- | :--- |
| **Login** | `LoginPage.java` | `LoginTest.java` | Valid login, invalid user, invalid password, empty fields, locked account check, Remember Me toggle, Forgot Password link |
| **Dashboard** | `DashboardPage.java` | `DashboardTest.java` | Welcome greeting, Net worth card, Stat cards, Quick Actions shortcuts, Recent transactions, Sidebar navigation, Topbar logout |
| **Accounts** | `AccountsPage.java` | `AccountsTest.java` | Accounts list, Account balances and badges, Open/Cancel Add Account modal, Empty name validation, Account creation |
| **Transfer** | `TransferPage.java` | `TransferTest.java` | Transfer funds between accounts, Review & Confirm modal, Same source & destination validation, Empty amount validation, Cancel confirmation |
| **Send Money** | `SendMoneyPage.java` | `SendMoneyTest.java` | Select payee, Transfer funds, Add Payee modal, Empty amount validation, Cancel confirmation |
| **Pay Utilities** | `PayUtilitiesPage.java` | `PayUtilitiesTest.java` | Biller search, Utility bill payment flow, Review & Confirm dialog, Add Biller modal, Empty amount validation |
| **Apply Loan** | `ApplyLoanPage.java` | `ApplyLoanTest.java` | Open loan modal, Loan type & term selection, Review & Confirm, Loan history search & filter, Cancel modal |
| **Transactions** | `TransactionsPage.java` | `TransactionsTest.java` | All transactions table, Search transactions by keyword, Clear filters, Pagination controls, Download statement button |
| **Notifications** | `NotificationsPage.java` | `NotificationsTest.java` | Notifications list, Unread count text, Mark single notification read, Mark all as read |
| **User Profile** | `ProfilePage.java` | `ProfileTest.java` | Personal info display, Edit profile form, Save profile changes, Change password mismatch validation |
| **Forgot Password** | `ForgotPasswordPage.java` | `ForgotPasswordTest.java` | Password reset request with email, Confirmation alert, Back to Login link |

---

## 6. Configuration Guide

Edit `src/test/resources/config.properties` to configure test behavior:

```properties
# Target application URL
url=https://qaplayground.com/bank/login

# Browser choice (chrome)
browser=chrome

# Run headless (true for CI/headless mode, false to view Chrome UI)
headless=true

# Timeouts in seconds
implicit.wait=10
explicit.wait=15

# Standard Test Credentials
standard.username=standard_user
standard.password=bank_sauce

# Other Demo Accounts
locked.username=locked_user
locked.password=bank_sauce
frozen.username=frozen_user
frozen.password=bank_sauce
admin.username=admin_user
admin.password=admin_sauce
```

---

## 7. How to Setup and Run in Eclipse IDE

1. **Open Eclipse IDE**.
2. Select **File → Import...**
3. Choose **Maven → Existing Maven Projects**, then click **Next**.
4. Browse to the root directory `QAPlaygroundBankAutomation` (where `pom.xml` is located) and click **Finish**.
5. Wait for Maven to download dependencies and build the workspace.
6. To run all tests:
   * Right-click `testng.xml` → **Run As → TestNG Suite**.
7. To run a specific test:
   * Navigate to `src/test/java/com/personal/tests/` → Right-click `LoginTest.java` → **Run As → TestNG Test**.

---

## 8. How to Run from Command Line (Maven CLI)

### Run Entire Test Suite (via `testng.xml`)
```bash
mvn clean test
```

### Run an Individual Test Class
```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=TransferTest
mvn test -Dtest=DashboardTest
```

### Run Specific Test Methods
```bash
mvn test -Dtest=LoginTest#testSuccessfulLogin
```

### Toggle Headless Mode via CLI
Change `headless=false` in `src/test/resources/config.properties` or override via Maven system properties.

---

## 9. Failure Handling & Screenshots

* When any test method fails, the `@AfterMethod` listener in `BaseTest.java` triggers `ScreenshotUtils.captureScreenshot(driver, testName)`.
* Screenshots are automatically saved to the `screenshots/` directory with a timestamped filename:
  ```text
  screenshots/FAILED_testInvalidPassword_20261006_231500_123.png
  ```

---

## 10. Key Automation Highlights for Interviews

1. **Pure POM Architecture:** Complete separation of Page Objects and Test classes ensures maintenance is localized when UI changes occur.
2. **Handling Radix UI / Dynamic Select Elements:** Modern web apps built with Tailwind and React Radix primitives don't use legacy `<select>` tags. The framework provides a clean helper in `BasePage` (`selectRadixOption`) that clicks the trigger and selects items cleanly.
3. **Robust Explicit Waits:** Tests use `WebDriverWait` and `ExpectedConditions` rather than brittle `Thread.sleep()` pauses.
4. **Data-Driven & Externalized Configurations:** All credentials, messages, and timeouts are externalized into `config.properties` and `TestData.java`.
5. **CI/CD Ready:** Configurable headless Chrome execution and Maven Surefire TestNG runner make it ready for GitHub Actions or Jenkins pipelines.
