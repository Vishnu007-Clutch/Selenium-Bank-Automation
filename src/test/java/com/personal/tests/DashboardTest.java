package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.*;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DashboardTest extends BaseTest {

    private DashboardPage dashboard;

    @BeforeMethod
    public void setupDashboard() {
        LoginPage login = new LoginPage(driver);
        dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Dashboard should be visible");
    }

    @Test(priority = 1, description = "Verify dashboard overview and stat cards")
    public void testDashboardStats() {
        Assert.assertTrue(dashboard.getWelcomeMessage().length() > 0, "Welcome message should appear");
        Assert.assertTrue(dashboard.isNetWorthDisplayed(), "Net worth should be visible");
        Assert.assertTrue(dashboard.getStatCardsCount() > 0, "Stat cards should be present");
        Assert.assertTrue(dashboard.isQuickActionsSectionDisplayed(), "Quick actions should be visible");
    }

    @Test(priority = 2, description = "Verify quick action navigation shortcuts")
    public void testQuickActions() {
        TransferPage transfer = dashboard.clickQuickActionTransfer();
        Assert.assertTrue(transfer.isTransferPageDisplayed(), "Transfer page should open");

        SendMoneyPage sendMoney = dashboard.clickQuickActionSendMoney();
        Assert.assertTrue(sendMoney.isSendMoneyPageDisplayed(), "Send Money page should open");

        PayUtilitiesPage billPay = dashboard.clickQuickActionBillPay();
        Assert.assertTrue(billPay.isBillPayPageDisplayed(), "Bill Pay page should open");

        ApplyLoanPage loan = dashboard.clickQuickActionApplyLoan();
        Assert.assertTrue(loan.isApplyLoanPageDisplayed(), "Apply Loan page should open");
    }

    @Test(priority = 3, description = "Verify sidebar navigation and logout")
    public void testSidebarNavAndLogout() {
        AccountsPage accounts = dashboard.navigateToAccounts();
        Assert.assertTrue(accounts.isAccountsPageDisplayed(), "Accounts page should open");

        TransactionsPage transactions = dashboard.navigateToTransactions();
        Assert.assertTrue(transactions.isTransactionsPageDisplayed(), "Transactions page should open");

        LoginPage login = dashboard.clickLogout();
        Assert.assertTrue(login.isLoginPageDisplayed(), "Should return to Login page");
    }
}
