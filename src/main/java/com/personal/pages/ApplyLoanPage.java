package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ApplyLoanPage extends BasePage {

    @FindBy(css = "[data-testid='apply-loan-page']")
    private WebElement applyLoanPageContainer;

    @FindBy(css = "[data-testid='apply-loan-page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='open-apply-loan-btn']")
    private WebElement openApplyLoanBtn;

    // Apply Loan Dialog
    @FindBy(css = "[data-testid='apply-loan-dialog']")
    private WebElement applyLoanDialog;

    @FindBy(css = "[data-testid='apply-loan-form']")
    private WebElement applyLoanForm;

    @FindBy(css = "[data-testid='loan-type-select']")
    private WebElement loanTypeSelectTrigger;

    @FindBy(css = "[data-testid='loan-amount-input']")
    private WebElement loanAmountInput;

    @FindBy(css = "textarea[name='loan_purpose_field'], textarea[placeholder*='What will this loan be used for']")
    private WebElement purposeInput;

    @FindBy(css = "[data-testid='loan-term-select']")
    private WebElement loanTermSelectTrigger;

    @FindBy(css = "[data-testid='loan-account-select']")
    private WebElement loanAccountSelectTrigger;

    @FindBy(css = "[data-testid='review-loan-btn']")
    private WebElement reviewLoanBtn;

    @FindBy(css = "[data-testid='cancel-loan-btn']")
    private WebElement cancelLoanBtn;

    // Confirm Dialog
    @FindBy(css = "[data-testid='loan-confirm-dialog']")
    private WebElement confirmDialog;

    @FindBy(css = "[data-testid='loan-confirm-summary']")
    private WebElement confirmSummary;

    @FindBy(css = "[data-testid='confirm-loan-btn']")
    private WebElement confirmLoanBtn;

    @FindBy(css = "[data-testid='cancel-confirm-loan-btn']")
    private WebElement cancelConfirmLoanBtn;

    @FindBy(css = "[data-testid='apply-loan-error-message']")
    private WebElement errorMessageBanner;

    // Loan History Section
    @FindBy(css = "[data-testid='loan-history-section']")
    private WebElement loanHistorySection;

    @FindBy(css = "[data-testid='loan-search-input']")
    private WebElement loanSearchInput;

    @FindBy(css = "[data-testid='loan-clear-filters-btn']")
    private WebElement loanClearFiltersBtn;

    @FindBy(css = "[data-testid='loan-history-row']")
    private List<WebElement> loanHistoryRows;

    private final By loanTypeOptionsLocator = By.cssSelector("[data-testid='loan-type-option']");
    private final By loanTermOptionsLocator = By.cssSelector("[data-testid='loan-term-option']");
    private final By loanAccountOptionsLocator = By.cssSelector("[data-testid='loan-account-option']");
    private final By confirmDialogLocator = By.cssSelector("[data-testid='loan-confirm-dialog']");
    private final By errorBannerLocator = By.cssSelector("[data-testid='apply-loan-error-message']");

    public ApplyLoanPage(WebDriver driver) {
        super(driver);
    }

    public boolean isApplyLoanPageDisplayed() {
        return isElementDisplayed(applyLoanPageContainer) || isElementDisplayed(pageTitle);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public void clickOpenApplyLoan() {
        click(openApplyLoanBtn);
    }

    public boolean isApplyLoanDialogDisplayed() {
        return isElementDisplayed(By.cssSelector("[data-testid='apply-loan-dialog']"));
    }

    public void selectLoanType(int index) {
        click(loanTypeSelectTrigger);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loanTypeOptionsLocator));
        if (index < options.size()) {
            options.get(index).click();
        }
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
    }

    public void enterLoanAmount(String amount) {
        clearAndType(loanAmountInput, amount);
    }

    public void enterPurpose(String purpose) {
        clearAndType(purposeInput, purpose);
    }

    public void selectLoanTerm(int index) {
        click(loanTermSelectTrigger);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loanTermOptionsLocator));
        if (index < options.size()) {
            options.get(index).click();
        }
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
    }

    public void selectDisbursalAccount(int index) {
        click(loanAccountSelectTrigger);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loanAccountOptionsLocator));
        if (index < options.size()) {
            options.get(index).click();
        }
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
    }

    public void clickReviewLoan() {
        scrollToElement(reviewLoanBtn);
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
        try {
            click(reviewLoanBtn);
        } catch (Exception e) {
            jsClick(reviewLoanBtn);
        }
    }

    public boolean isConfirmationDialogDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDialogLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getConfirmationSummary() {
        return getText(confirmSummary);
    }

    public void clickConfirmLoan() {
        try {
            click(confirmLoanBtn);
        } catch (Exception e) {
            jsClick(confirmLoanBtn);
        }
    }

    public void clickCancelConfirmLoan() {
        click(cancelConfirmLoanBtn);
        try {
            waitForInvisibility(confirmDialogLocator);
        } catch (Exception ignored) {
        }
    }

    public void clickCancelLoan() {
        click(cancelLoanBtn);
        try {
            waitForInvisibility(By.cssSelector("[data-testid='apply-loan-dialog']"));
        } catch (Exception ignored) {
        }
    }

    public boolean isLoanHistoryDisplayed() {
        return isElementDisplayed(loanHistorySection);
    }

    public int getLoanHistoryRowsCount() {
        return loanHistoryRows.size();
    }

    public void searchLoanHistory(String query) {
        clearAndType(loanSearchInput, query);
    }

    public void clickClearFilters() {
        click(loanClearFiltersBtn);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorBannerLocator);
    }

    public String getErrorMessage() {
        return getText(errorMessageBanner);
    }
}
