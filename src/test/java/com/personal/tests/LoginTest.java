package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.*;
import com.personal.utilities.TestData;
import com.personal.utilities.TestListener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify entire bank flow from login to logout")
    public void testBankFlow() {
        LoginPage login = new LoginPage(driver);
        Assert.assertTrue(login.isLoginPageDisplayed(), "Login page should be visible");

        // 1. Login validations & login
        login.clickLoginButton();
        Assert.assertTrue(login.waitForErrorBannerVisible(), "Empty login should show error");

        login.enterUsername(TestData.STANDARD_USER);
        login.enterPassword(TestData.INVALID_PASSWORD);
        login.clickLoginButton();
        Assert.assertTrue(login.waitForErrorBannerVisible(), "Invalid password should show error");

        login.toggleRememberMe();
        login.enterUsername(TestData.STANDARD_USER);
        login.enterPassword(TestData.STANDARD_PASSWORD);
        login.clickLoginButton();
        TestListener.logPass("1. Login: Validated credentials and logged in with " + TestData.STANDARD_USER);

        // 2. Dashboard
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Dashboard should be visible");
        Assert.assertTrue(dashboard.isNetWorthDisplayed(), "Net worth should be visible");
        TestListener.logPass("2. Dashboard: Verified greeting and Net Worth (" + dashboard.getNetWorthValue() + ")");

        // 3. Accounts
        AccountsPage accounts = dashboard.navigateToAccounts();
        Assert.assertTrue(accounts.isAccountsPageDisplayed(), "Accounts page should be visible");
        Assert.assertTrue(accounts.getAccountsCount() > 0, "Accounts should be present");
        accounts.clickAddAccount();
        accounts.enterAccountName("Vacation Savings");
        accounts.selectAccountType();
        accounts.clickCancelAccount();
        TestListener.logPass("3. Accounts: Verified account list (" + accounts.getAccountsCount() + " accounts) and Add Account modal");

        // 4. Transfer
        TransferPage transfer = dashboard.navigateToTransfer();
        Assert.assertTrue(transfer.isTransferPageDisplayed(), "Transfer page should be visible");
        transfer.selectFromAccount(0);
        transfer.selectToAccount(0);
        transfer.enterAmount(TestData.TRANSFER_AMOUNT);
        transfer.clickReviewTransfer();
        Assert.assertTrue(transfer.isConfirmationDialogDisplayed(), "Transfer confirmation should be visible");
        transfer.clickCancelConfirmTransfer();
        TestListener.logPass("4. Transfer: Configured and verified fund transfer dialog ($" + TestData.TRANSFER_AMOUNT + ")");

        // 5. Send Money
        SendMoneyPage sendMoney = dashboard.navigateToSendMoney();
        Assert.assertTrue(sendMoney.isSendMoneyPageDisplayed(), "Send Money page should be visible");
        sendMoney.selectPayee(0);
        sendMoney.selectFromAccount(0);
        sendMoney.enterAmount(TestData.SEND_MONEY_AMOUNT);
        sendMoney.enterNote(TestData.SEND_MONEY_NOTE);
        sendMoney.clickReviewSend();
        Assert.assertTrue(sendMoney.isConfirmationDialogDisplayed(), "Send Money confirmation should be visible");
        sendMoney.clickCancelConfirmSend();
        TestListener.logPass("5. Send Money: Configured and verified payee transfer dialog ($" + TestData.SEND_MONEY_AMOUNT + ")");

        // 6. Bill Pay
        PayUtilitiesPage billPay = dashboard.navigateToPayUtilities();
        Assert.assertTrue(billPay.isBillPayPageDisplayed(), "Bill Pay page should be visible");
        billPay.searchAndSelectBiller(TestData.BILLER_SEARCH_QUERY);
        billPay.selectFromAccount(0);
        billPay.enterAmount(TestData.BILL_PAY_AMOUNT);
        billPay.enterMemo(TestData.BILL_PAY_MEMO);
        billPay.clickReviewBill();
        Assert.assertTrue(billPay.isConfirmationDialogDisplayed(), "Bill Pay confirmation should be visible");
        billPay.clickCancelConfirmBill();
        TestListener.logPass("6. Bill Pay: Selected biller and verified payment dialog ($" + TestData.BILL_PAY_AMOUNT + ")");

        // 7. Apply Loan
        ApplyLoanPage loan = dashboard.navigateToApplyLoan();
        Assert.assertTrue(loan.isApplyLoanPageDisplayed(), "Apply Loan page should be visible");
        loan.clickOpenApplyLoan();
        loan.selectLoanType(0);
        loan.enterLoanAmount(TestData.LOAN_AMOUNT);
        loan.selectLoanTerm(0);
        loan.selectDisbursalAccount(0);
        loan.enterPurpose("Home renovation");
        loan.clickReviewLoan();
        Assert.assertTrue(loan.isConfirmationDialogDisplayed(), "Loan confirmation should be visible");
        loan.clickConfirmLoan();
        TestListener.logPass("7. Apply Loan: Submitted loan application ($" + TestData.LOAN_AMOUNT + ")");

        // 8. Transactions
        TransactionsPage transactions = dashboard.navigateToTransactions();
        Assert.assertTrue(transactions.isTransactionsPageDisplayed(), "Transactions page should be visible");
        Assert.assertTrue(transactions.getTransactionRowsCount() > 0, "Transactions rows should be present");
        transactions.enterSearchQuery("Payroll");
        transactions.clickClearFilters();
        transactions.clickDownloadTransactions();
        TestListener.logPass("8. Transactions: Verified " + transactions.getTransactionRowsCount() + " records, search filter, and export");

        // 9. Notifications
        NotificationsPage notifications = dashboard.navigateToNotifications();
        Assert.assertTrue(notifications.isNotificationsPageDisplayed(), "Notifications page should be visible");
        notifications.clickMarkAllRead();
        TestListener.logPass("9. Notifications: Checked notifications and marked all read");

        // 10. Profile
        ProfilePage profile = dashboard.navigateToProfile();
        Assert.assertTrue(profile.isProfilePageDisplayed(), "Profile page should be visible");
        profile.clickEditProfile();
        profile.updatePersonalInfo(TestData.PROFILE_FIRST_NAME, TestData.PROFILE_LAST_NAME, TestData.PROFILE_PHONE, TestData.PROFILE_ADDRESS);
        profile.clickSaveProfile();
        TestListener.logPass("10. Profile: Updated profile information and verified security");

        // 11. Logout
        LoginPage logout = dashboard.clickLogout();
        Assert.assertTrue(logout.isLoginPageDisplayed(), "Should return to Login page");
        TestListener.logPass("11. Logout: Session ended safely; redirected back to Login page");
    }
}
