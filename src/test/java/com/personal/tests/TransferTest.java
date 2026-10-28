package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.DashboardPage;
import com.personal.pages.LoginPage;
import com.personal.pages.TransferPage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TransferTest extends BaseTest {

    private TransferPage transfer;

    @BeforeMethod
    public void setupTransfer() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        transfer = dashboard.navigateToTransfer();
        Assert.assertTrue(transfer.isTransferPageDisplayed(), "Transfer page should be visible");
    }

    @Test(priority = 1, description = "Verify Transfer page load")
    public void testTransferPageLoaded() {
        Assert.assertTrue(transfer.getPageHeading().toLowerCase().contains("transfer"), "Heading should indicate Transfer");
    }

    @Test(priority = 2, description = "Verify transfer review and cancellation")
    public void testTransferFundsFlow() {
        transfer.selectFromAccount(0);
        transfer.selectToAccount(0);
        transfer.enterAmount(TestData.TRANSFER_AMOUNT);
        transfer.clickReviewTransfer();
        Assert.assertTrue(transfer.isConfirmationDialogDisplayed(), "Confirmation should appear");
        transfer.clickCancelConfirmTransfer();
    }
}
