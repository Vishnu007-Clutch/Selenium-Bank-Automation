package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.AccountsPage;
import com.personal.pages.DashboardPage;
import com.personal.pages.LoginPage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AccountsTest extends BaseTest {

    private AccountsPage accounts;

    @BeforeMethod
    public void setupAccounts() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        accounts = dashboard.navigateToAccounts();
        Assert.assertTrue(accounts.isAccountsPageDisplayed(), "Accounts page should be visible");
    }

    @Test(priority = 1, description = "Verify Accounts page heading and account row details")
    public void testAccountsList() {
        Assert.assertTrue(accounts.getPageHeading().contains("Accounts"), "Heading should contain Accounts");
        Assert.assertTrue(accounts.getAccountsCount() > 0, "Account table should have accounts");
        Assert.assertFalse(accounts.getFirstAccountName().isEmpty(), "Account name should not be empty");
        Assert.assertTrue(accounts.getFirstAccountBalance().contains("$"), "Balance should contain '$'");
    }

    @Test(priority = 2, description = "Verify opening and cancelling Add Account modal")
    public void testAddAccountModal() {
        accounts.clickAddAccount();
        Assert.assertTrue(accounts.isAddAccountModalDisplayed(), "Modal should be displayed");
        accounts.enterAccountName("Vacation Fund");
        accounts.selectAccountType();
        accounts.clickCancelAccount();
    }
}
