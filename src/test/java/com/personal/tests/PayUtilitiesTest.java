package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.DashboardPage;
import com.personal.pages.LoginPage;
import com.personal.pages.PayUtilitiesPage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PayUtilitiesTest extends BaseTest {

    private PayUtilitiesPage billPay;

    @BeforeMethod
    public void setupBillPay() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        billPay = dashboard.navigateToPayUtilities();
        Assert.assertTrue(billPay.isBillPayPageDisplayed(), "Bill Pay page should be visible");
    }

    @Test(priority = 1, description = "Verify Bill Pay page load")
    public void testBillPayPageLoaded() {
        Assert.assertTrue(billPay.getPageHeading().toLowerCase().contains("bill"), "Heading should indicate Bill Pay");
    }

    @Test(priority = 2, description = "Verify bill search, payment review, and cancel")
    public void testBillPaymentFlow() {
        billPay.searchAndSelectBiller(TestData.BILLER_SEARCH_QUERY);
        Assert.assertTrue(billPay.isBillerSelected(), "Biller should be selected");
        billPay.selectFromAccount(0);
        billPay.enterAmount(TestData.BILL_PAY_AMOUNT);
        billPay.enterMemo(TestData.BILL_PAY_MEMO);
        billPay.clickReviewBill();
        Assert.assertTrue(billPay.isConfirmationDialogDisplayed(), "Confirmation should appear");
        billPay.clickCancelConfirmBill();
    }
}
