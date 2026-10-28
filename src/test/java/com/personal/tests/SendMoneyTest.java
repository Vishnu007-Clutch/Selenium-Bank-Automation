package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.DashboardPage;
import com.personal.pages.LoginPage;
import com.personal.pages.SendMoneyPage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SendMoneyTest extends BaseTest {

    private SendMoneyPage sendMoney;

    @BeforeMethod
    public void setupSendMoney() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        sendMoney = dashboard.navigateToSendMoney();
        Assert.assertTrue(sendMoney.isSendMoneyPageDisplayed(), "Send Money page should be visible");
    }

    @Test(priority = 1, description = "Verify Send Money page load")
    public void testSendMoneyPageLoaded() {
        Assert.assertTrue(sendMoney.getPageHeading().toLowerCase().contains("send"), "Heading should indicate Send Money");
    }

    @Test(priority = 2, description = "Verify send money flow and review dialog")
    public void testSendMoneyFlow() {
        sendMoney.selectPayee(0);
        sendMoney.selectFromAccount(0);
        sendMoney.enterAmount(TestData.SEND_MONEY_AMOUNT);
        sendMoney.enterNote(TestData.SEND_MONEY_NOTE);
        sendMoney.clickReviewSend();
        Assert.assertTrue(sendMoney.isConfirmationDialogDisplayed(), "Confirmation should appear");
        sendMoney.clickCancelConfirmSend();
    }
}
