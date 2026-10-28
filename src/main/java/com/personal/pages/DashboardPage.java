package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {

    @FindBy(css = "[data-testid='bank-dashboard-page']")
    private WebElement dashboardContainer;

    @FindBy(css = "[data-testid='dashboard-welcome-message']")
    private WebElement welcomeMessage;

    @FindBy(css = "[data-testid='stat-card']")
    private List<WebElement> statCards;

    @FindBy(css = "[data-testid='stat-card-net-worth-value']")
    private WebElement netWorthValue;

    @FindBy(css = "[data-testid='quick-actions-section']")
    private WebElement quickActionsSection;

    @FindBy(css = "[data-testid='quick-actions-section'] a[href*='/bank/transfer']")
    private WebElement quickActionTransfer;

    @FindBy(css = "[data-testid='quick-actions-section'] a[href*='/bank/send-money']")
    private WebElement quickActionSendMoney;

    @FindBy(css = "[data-testid='quick-actions-section'] a[href*='/bank/bill-pay']")
    private WebElement quickActionBillPay;

    @FindBy(css = "[data-testid='quick-actions-section'] a[href*='/bank/apply-loan']")
    private WebElement quickActionApplyLoan;

    @FindBy(css = "[data-testid='recent-transactions-section']")
    private WebElement recentTransactionsSection;

    @FindBy(css = "[data-testid='recent-transactions-table']")
    private WebElement recentTransactionsTable;

    @FindBy(css = "[data-testid='recent-txn-row']")
    private List<WebElement> recentTransactionRows;

    @FindBy(css = "[data-testid='view-all-transactions-btn'] a, [data-testid='view-all-transactions-btn']")
    private WebElement viewAllTransactionsBtn;

    // Topbar elements
    @FindBy(css = "[data-testid='topbar-logout-btn']")
    private WebElement logoutBtn;

    @FindBy(css = "[data-testid='nav-notifications-link']")
    private WebElement topbarNotificationsLink;

    // Sidebar navigation links
    @FindBy(css = "[data-testid='sidebar-main-nav'] a[href*='/bank/dashboard'], [data-testid='bank-sidebar'] a[href*='/bank/dashboard']")
    private WebElement navDashboard;

    @FindBy(css = "[data-testid='sidebar-main-nav'] a[href*='/bank/accounts'], [data-testid='bank-sidebar'] a[href*='/bank/accounts']")
    private WebElement navAccounts;

    @FindBy(css = "[data-testid='sidebar-main-nav'] a[href*='/bank/transfer'], [data-testid='bank-sidebar'] a[href*='/bank/transfer']")
    private WebElement navTransfer;

    @FindBy(css = "[data-testid='sidebar-main-nav'] a[href*='/bank/send-money'], [data-testid='bank-sidebar'] a[href*='/bank/send-money']")
    private WebElement navSendMoney;

    @FindBy(css = "[data-testid='sidebar-main-nav'] a[href*='/bank/bill-pay'], [data-testid='bank-sidebar'] a[href*='/bank/bill-pay']")
    private WebElement navBillPay;

    @FindBy(css = "[data-testid='sidebar-main-nav'] a[href*='/bank/transactions'], [data-testid='bank-sidebar'] a[href*='/bank/transactions']")
    private WebElement navTransactions;

    @FindBy(css = "[data-testid='sidebar-main-nav'] a[href*='/bank/apply-loan'], [data-testid='bank-sidebar'] a[href*='/bank/apply-loan']")
    private WebElement navApplyLoan;

    @FindBy(css = "[data-testid='sidebar-account-nav'] a[href*='/bank/notifications'], [data-testid='bank-sidebar'] a[href*='/bank/notifications']")
    private WebElement navNotifications;

    @FindBy(css = "[data-testid='sidebar-account-nav'] a[href*='/bank/profile'], [data-testid='bank-sidebar'] a[href*='/bank/profile']")
    private WebElement navProfile;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardContainer);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    public boolean isNetWorthDisplayed() {
        return isElementDisplayed(netWorthValue);
    }

    public String getNetWorthValue() {
        return getText(netWorthValue);
    }

    public int getStatCardsCount() {
        return statCards.size();
    }

    public boolean isQuickActionsSectionDisplayed() {
        return isElementDisplayed(quickActionsSection);
    }

    public boolean isRecentTransactionsSectionDisplayed() {
        return isElementDisplayed(recentTransactionsSection);
    }

    public int getRecentTransactionsCount() {
        return recentTransactionRows.size();
    }

    // Quick Action shortcuts
    public TransferPage clickQuickActionTransfer() {
        click(quickActionTransfer);
        return new TransferPage(driver);
    }

    public SendMoneyPage clickQuickActionSendMoney() {
        click(quickActionSendMoney);
        return new SendMoneyPage(driver);
    }

    public PayUtilitiesPage clickQuickActionBillPay() {
        click(quickActionBillPay);
        return new PayUtilitiesPage(driver);
    }

    public ApplyLoanPage clickQuickActionApplyLoan() {
        click(quickActionApplyLoan);
        return new ApplyLoanPage(driver);
    }

    public void clickViewAllTransactions() {
        scrollToElement(viewAllTransactionsBtn);
        jsClick(viewAllTransactionsBtn);
        wait.until(d -> !d.getCurrentUrl().endsWith("/bank/dashboard"));
    }

    // Sidebar navigation methods
    public AccountsPage navigateToAccounts() {
        try {
            click(navAccounts);
        } catch (Exception e) {
            driver.get("https://qaplayground.com/bank/accounts");
        }
        return new AccountsPage(driver);
    }

    public TransferPage navigateToTransfer() {
        try {
            click(navTransfer);
        } catch (Exception e) {
            driver.get("https://qaplayground.com/bank/transfer");
        }
        return new TransferPage(driver);
    }

    public SendMoneyPage navigateToSendMoney() {
        try {
            click(navSendMoney);
        } catch (Exception e) {
            driver.get("https://qaplayground.com/bank/send-money");
        }
        return new SendMoneyPage(driver);
    }

    public PayUtilitiesPage navigateToPayUtilities() {
        try {
            click(navBillPay);
        } catch (Exception e) {
            driver.get("https://qaplayground.com/bank/bill-pay");
        }
        return new PayUtilitiesPage(driver);
    }

    public ApplyLoanPage navigateToApplyLoan() {
        try {
            click(navApplyLoan);
        } catch (Exception e) {
            driver.get("https://qaplayground.com/bank/apply-loan");
        }
        return new ApplyLoanPage(driver);
    }

    public TransactionsPage navigateToTransactions() {
        try {
            click(navTransactions);
        } catch (Exception e) {
            driver.get("https://qaplayground.com/bank/transactions");
        }
        return new TransactionsPage(driver);
    }

    public NotificationsPage navigateToNotifications() {
        try {
            click(navNotifications);
        } catch (Exception e) {
            driver.get("https://qaplayground.com/bank/notifications");
        }
        return new NotificationsPage(driver);
    }

    public ProfilePage navigateToProfile() {
        try {
            click(navProfile);
        } catch (Exception e) {
            driver.get("https://qaplayground.com/bank/profile");
        }
        return new ProfilePage(driver);
    }

    public LoginPage clickLogout() {
        click(logoutBtn);
        return new LoginPage(driver);
    }
}
