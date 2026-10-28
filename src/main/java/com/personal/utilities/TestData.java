package com.personal.utilities;

/**
 * Centralized test data constants for positive and negative test scenarios.
 */
public class TestData {

    // Valid Test Users
    public static final String STANDARD_USER = "standard_user";
    public static final String STANDARD_PASSWORD = "bank_sauce";
    public static final String LOCKED_USER = "locked_user";
    public static final String FROZEN_USER = "frozen_user";
    public static final String OVERDRAFT_USER = "overdraft_user";

    // Invalid & Edge Case Login Data
    public static final String INVALID_USERNAME = "nonexistent_user_999";
    public static final String INVALID_PASSWORD = "wrong_password_123";
    public static final String EMPTY_STRING = "";

    
    public static final String MSG_REQUIRED_USERNAME = "Please enter your username.";
    public static final String MSG_REQUIRED_PASSWORD = "Please enter your password.";
    public static final String MSG_INVALID_CREDENTIALS = "The username or password you entered is incorrect.";
    public static final String MSG_LOCKED_ACCOUNT = "Your account has been suspended. Please contact support.";
    public static final String MSG_TRANSFER_SAME_ACCOUNTS = "From and To accounts must be different.";
    public static final String MSG_PAST_DATE = "Payment date cannot be in the past.";

    // Transfer Test Data
    public static final String TRANSFER_AMOUNT = "150.00";
    public static final String TRANSFER_LARGE_AMOUNT = "99999999.00";

    // Send Money Test Data
    public static final String SEND_MONEY_AMOUNT = "50.00";
    public static final String SEND_MONEY_NOTE = "Reimbursement for team lunch";
    public static final String NEW_PAYEE_NAME = "Alice Johnson";
    public static final String NEW_PAYEE_BANK = "Metro Global Bank";
    public static final String NEW_PAYEE_ROUTING = "123456789";
    public static final String NEW_PAYEE_ACCOUNT = "9876543210";

    // Bill Pay Test Data
    public static final String BILLER_SEARCH_QUERY = "Electric";
    public static final String BILLER_NAME = "City Electric Co.";
    public static final String BILL_PAY_AMOUNT = "65.50";
    public static final String BILL_PAY_MEMO = "Monthly electric utility bill";
    public static final String NEW_BILLER_NAME = "Apex Clean Power";

    // Apply Loan Test Data
    public static final String LOAN_AMOUNT = "5000";
    public static final String LOAN_TYPE_PERSONAL = "Personal";

    // Account Creation Test Data
    public static final String NEW_ACCOUNT_NAME = "Vacation Savings";

    // Profile Test Data
    public static final String PROFILE_FIRST_NAME = "Alex";
    public static final String PROFILE_LAST_NAME = "Morgan";
    public static final String PROFILE_EMAIL = "alex.morgan@testbank.com";
    public static final String PROFILE_PHONE = "555-012-3456";
    public static final String PROFILE_ADDRESS = "742 Evergreen Terrace, Springfield";

    // Forgot Password Test Data
    public static final String FORGOT_PASSWORD_EMAIL = "standard_user@qaplayground.com";
}
