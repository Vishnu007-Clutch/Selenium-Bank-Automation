package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.DashboardPage;
import com.personal.pages.LoginPage;
import com.personal.pages.ProfilePage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ProfileTest extends BaseTest {

    private ProfilePage profile;

    @BeforeMethod
    public void setupProfile() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        profile = dashboard.navigateToProfile();
        Assert.assertTrue(profile.isProfilePageDisplayed(), "Profile page should be visible");
    }

    @Test(priority = 1, description = "Verify editing and saving personal profile details")
    public void testEditProfile() {
        profile.clickEditProfile();
        profile.updatePersonalInfo(TestData.PROFILE_FIRST_NAME, TestData.PROFILE_LAST_NAME, TestData.PROFILE_PHONE, TestData.PROFILE_ADDRESS);
        profile.clickSaveProfile();
        Assert.assertTrue(profile.isProfileSaveSuccessDisplayed(), "Success banner should be visible");
    }

    @Test(priority = 2, description = "Verify password mismatch validation error")
    public void testPasswordMismatch() {
        profile.changePassword("bank_sauce", "NewPass123!", "MismatchPass456!");
        Assert.assertTrue(profile.isPasswordChangeErrorDisplayed(), "Error banner should be displayed");
    }
}
