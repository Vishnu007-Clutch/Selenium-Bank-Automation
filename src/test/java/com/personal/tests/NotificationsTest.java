package com.personal.tests;

import com.personal.base.BaseTest;
import com.personal.pages.DashboardPage;
import com.personal.pages.LoginPage;
import com.personal.pages.NotificationsPage;
import com.personal.utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class NotificationsTest extends BaseTest {

    private NotificationsPage notifications;

    @BeforeMethod
    public void setupNotifications() {
        LoginPage login = new LoginPage(driver);
        DashboardPage dashboard = login.login(TestData.STANDARD_USER, TestData.STANDARD_PASSWORD);
        notifications = dashboard.navigateToNotifications();
        Assert.assertTrue(notifications.isNotificationsPageDisplayed(), "Notifications page should be visible");
    }

    @Test(priority = 1, description = "Verify Notifications list and mark all read")
    public void testNotificationsListAndMarkRead() {
        Assert.assertTrue(notifications.getNotificationCount() > 0, "Notifications should be listed");
        Assert.assertFalse(notifications.getFirstNotificationTitle().isEmpty(), "Alert title should be displayed");
        notifications.clickMarkAllRead();
    }
}
