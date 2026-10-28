package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.ForgotPasswordPage;
import com.personal.pages.LoginPage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {

    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;

    @BeforeMethod
    public void setupForgotPasswordPage() {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = loginPage.clickForgotPassword();
        Assert.assertTrue(forgotPasswordPage.isForgotPasswordPageDisplayed(),
                "Forgot Password page must be displayed");
    }

    @Test(priority = 1, description = "Verify Forgot Password page layout and card display")
    public void testForgotPasswordPageLoaded() {
        Assert.assertTrue(forgotPasswordPage.isForgotPasswordPageDisplayed(),
                "Forgot Password page container should be visible");
    }

    @Test(priority = 2, description = "Verify submitting password reset request with valid email")
    public void testRequestPasswordReset() {
        forgotPasswordPage.enterEmail(TestData.FORGOT_PASSWORD_EMAIL);
        forgotPasswordPage.clickSubmit();

        Assert.assertTrue(forgotPasswordPage.isSuccessMessageDisplayed(),
                "Success message should be displayed after requesting password reset");
    }

    @Test(priority = 3, description = "Verify navigation back to login page from Forgot Password")
    public void testBackToLoginLink() {
        LoginPage returnedLoginPage = forgotPasswordPage.clickBackToLogin();
        Assert.assertTrue(returnedLoginPage.isLoginPageDisplayed(),
                "Login page should be displayed after clicking Back to login");
    }
}
