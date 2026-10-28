package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class TransactionsPage extends BasePage {

    @FindBy(css = "[data-testid='transactions-page']")
    private WebElement transactionsPageContainer;

    @FindBy(css = "[data-testid='transactions-page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='all-transactions-filter-bar']")
    private WebElement filterBar;

    @FindBy(css = "[data-testid='all-txn-search-input']")
    private WebElement searchInput;

    @FindBy(css = "[data-testid='clear-all-txn-filters-btn']")
    private WebElement clearFiltersBtn;

    @FindBy(css = "[data-testid='all-transactions-table']")
    private WebElement transactionsTable;

    @FindBy(css = "[data-testid='all-txn-row']")
    private List<WebElement> transactionRows;

    @FindBy(css = "[data-testid='all-txn-description']")
    private List<WebElement> transactionDescriptions;

    @FindBy(css = "[data-testid='all-txn-pagination-controls']")
    private WebElement paginationControls;

    @FindBy(css = "[data-testid='all-txn-pagination-info']")
    private WebElement paginationInfo;

    @FindBy(css = "[data-testid='download-all-transactions-btn']")
    private WebElement downloadTransactionsBtn;

    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTransactionsPageDisplayed() {
        return isElementDisplayed(transactionsPageContainer) || isElementDisplayed(pageTitle);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public boolean isFilterBarDisplayed() {
        return isElementDisplayed(filterBar);
    }

    public void enterSearchQuery(String query) {
        clearAndType(searchInput, query);
    }

    public void clickClearFilters() {
        click(clearFiltersBtn);
    }

    public int getTransactionRowsCount() {
        return transactionRows.size();
    }

    public String getFirstTransactionDescription() {
        if (!transactionDescriptions.isEmpty()) {
            return getText(transactionDescriptions.get(0));
        }
        return "";
    }

    public boolean isPaginationDisplayed() {
        return isElementDisplayed(paginationControls);
    }

    public String getPaginationInfoText() {
        if (isElementDisplayed(paginationInfo)) {
            return getText(paginationInfo);
        }
        return "";
    }

    public void clickDownloadTransactions() {
        click(downloadTransactionsBtn);
    }

    public boolean isDownloadButtonEnabled() {
        return isElementEnabled(downloadTransactionsBtn);
    }
}
