package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NotificationsPage extends BasePage {

    @FindBy(css = "[data-testid='notifications-page']")
    private WebElement notificationsPageContainer;

    @FindBy(css = "[data-testid='notifications-page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='unread-count-text']")
    private WebElement unreadCountText;

    @FindBy(css = "[data-testid='mark-all-read-btn']")
    private WebElement markAllReadBtn;

    @FindBy(css = "[data-testid='notifications-list']")
    private WebElement notificationsList;

    @FindBy(css = "[data-testid='notification-item']")
    private List<WebElement> notificationItems;

    @FindBy(css = "[data-testid='notification-title']")
    private List<WebElement> notificationTitles;

    @FindBy(css = "[data-testid='mark-read-btn']")
    private List<WebElement> markReadButtons;

    public NotificationsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNotificationsPageDisplayed() {
        return isElementDisplayed(notificationsPageContainer) || isElementDisplayed(pageTitle);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public String getUnreadCountText() {
        if (isElementDisplayed(unreadCountText)) {
            return getText(unreadCountText);
        }
        return "";
    }

    public int getNotificationCount() {
        return notificationItems.size();
    }

    public void clickMarkAllRead() {
        click(markAllReadBtn);
    }

    public String getFirstNotificationTitle() {
        if (!notificationTitles.isEmpty()) {
            return getText(notificationTitles.get(0));
        }
        return "";
    }

    public void clickMarkFirstNotificationRead() {
        if (!markReadButtons.isEmpty()) {
            click(markReadButtons.get(0));
        }
    }
}
