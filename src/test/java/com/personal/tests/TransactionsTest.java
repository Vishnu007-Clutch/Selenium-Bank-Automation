package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.DashboardPage;
import com.personal.pages.LoginPage;
import com.personal.pages.TransactionsPage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TransactionsTest extends BaseTest {

    private TransactionsPage transactions;

    @BeforeMethod
    public void setupTransactions() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        transactions = dashboard.navigateToTransactions();
        Assert.assertTrue(transactions.isTransactionsPageDisplayed(), "Transactions page should be visible");
    }

    @Test(priority = 1, description = "Verify Transactions list loaded")
    public void testTransactionsList() {
        Assert.assertTrue(transactions.getTransactionRowsCount() > 0, "Transactions rows should be listed");
        Assert.assertFalse(transactions.getFirstTransactionDescription().isEmpty(), "Description should not be empty");
    }

    @Test(priority = 2, description = "Verify search filter and statement download")
    public void testSearchAndDownload() {
        transactions.enterSearchQuery("Payroll");
        transactions.clickClearFilters();
        Assert.assertTrue(transactions.isDownloadButtonEnabled(), "Download button should be enabled");
        transactions.clickDownloadTransactions();
    }
}
