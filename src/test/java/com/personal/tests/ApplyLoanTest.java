package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.ApplyLoanPage;
import com.personal.pages.DashboardPage;
import com.personal.pages.LoginPage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ApplyLoanTest extends BaseTest {

    private ApplyLoanPage loan;

    @BeforeMethod
    public void setupLoan() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        loan = dashboard.navigateToApplyLoan();
        Assert.assertTrue(loan.isApplyLoanPageDisplayed(), "Apply Loan page should be visible");
    }

    @Test(priority = 1, description = "Verify Apply Loan page load")
    public void testApplyLoanPageLoaded() {
        Assert.assertTrue(loan.getPageHeading().toLowerCase().contains("loan"), "Heading should indicate Loan");
    }

    @Test(priority = 2, description = "Verify loan application submission")
    public void testApplyLoanFlow() {
        loan.clickOpenApplyLoan();
        Assert.assertTrue(loan.isApplyLoanDialogDisplayed(), "Apply Loan dialog should open");
        loan.selectLoanType(0);
        loan.enterLoanAmount(TestData.LOAN_AMOUNT);
        loan.selectLoanTerm(0);
        loan.selectDisbursalAccount(0);
        loan.enterPurpose("Home renovation project");
        loan.clickReviewLoan();
        Assert.assertTrue(loan.isConfirmationDialogDisplayed(), "Confirmation should appear");
        loan.clickConfirmLoan();
    }
}
