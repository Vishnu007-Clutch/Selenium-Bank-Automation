package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class PayUtilitiesPage extends BasePage {

    @FindBy(css = "[data-testid='bill-pay-page']")
    private WebElement billPayPageContainer;

    @FindBy(css = "[data-testid='bill-pay-page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='bill-pay-form']")
    private WebElement billPayForm;

    @FindBy(css = "[data-testid='biller-search-input']")
    private WebElement billerSearchInput;

    @FindBy(css = "[data-testid='biller-search-results']")
    private WebElement billerSearchResults;

    @FindBy(css = "[data-testid='biller-selected-summary']")
    private WebElement billerSelectedSummary;

    @FindBy(css = "[data-testid='add-biller-btn']")
    private WebElement addBillerBtn;

    // Add Biller Dialog
    @FindBy(css = "[data-testid='add-biller-dialog']")
    private WebElement addBillerDialog;

    @FindBy(css = "[data-testid='add-biller-name-input']")
    private WebElement addBillerNameInput;

    @FindBy(css = "[data-testid='save-add-biller-btn']")
    private WebElement saveAddBillerBtn;

    @FindBy(css = "[data-testid='cancel-add-biller-btn']")
    private WebElement cancelAddBillerBtn;

    // Payment Form Fields
    @FindBy(css = "[data-testid='bill-pay-from-select']")
    private WebElement billPayFromSelectTrigger;

    @FindBy(css = "[data-testid='bill-amount-input']")
    private WebElement billAmountInput;

    @FindBy(css = "[data-testid='bill-payment-date-input']")
    private WebElement paymentDateInput;

    @FindBy(css = "[data-testid='bill-memo-input']")
    private WebElement billMemoInput;

    @FindBy(css = "[data-testid='review-bill-btn']")
    private WebElement reviewBillBtn;

    // Confirmation Modal
    @FindBy(css = "[data-testid='bill-pay-confirm-dialog']")
    private WebElement confirmDialog;

    @FindBy(css = "[data-testid='bill-confirm-summary']")
    private WebElement confirmSummary;

    @FindBy(css = "[data-testid='confirm-bill-btn']")
    private WebElement confirmBillBtn;

    @FindBy(css = "[data-testid='cancel-confirm-bill-btn']")
    private WebElement cancelConfirmBillBtn;

    @FindBy(css = "[data-testid='bill-pay-error']")
    private WebElement errorMessageBanner;

    private final By billerOptionsLocator = By.cssSelector("[data-testid='biller-option']");
    private final By fromOptionsLocator = By.cssSelector("[data-testid='bill-pay-from-option']");
    private final By confirmDialogLocator = By.cssSelector("[data-testid='bill-pay-confirm-dialog']");
    private final By errorBannerLocator = By.cssSelector("[data-testid='bill-pay-error']");

    public PayUtilitiesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBillPayPageDisplayed() {
        return isElementDisplayed(billPayPageContainer) || isElementDisplayed(pageTitle);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public void searchAndSelectBiller(String billerName) {
        clearAndType(billerSearchInput, billerName);
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(billerOptionsLocator));
        if (!results.isEmpty()) {
            results.get(0).click();
        }
    }

    public boolean isBillerSelected() {
        return isElementDisplayed(billerSelectedSummary);
    }

    public void selectFromAccount(int index) {
        click(billPayFromSelectTrigger);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fromOptionsLocator));
        if (index < options.size()) {
            options.get(index).click();
        }
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
    }

    public void enterAmount(String amount) {
        clearAndType(billAmountInput, amount);
    }

    public void enterMemo(String memo) {
        clearAndType(billMemoInput, memo);
    }

    public void clickReviewBill() {
        scrollToElement(reviewBillBtn);
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
        try {
            click(reviewBillBtn);
        } catch (Exception e) {
            jsClick(reviewBillBtn);
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

    public void clickConfirmBill() {
        try {
            click(confirmBillBtn);
        } catch (Exception e) {
            jsClick(confirmBillBtn);
        }
    }

    public void clickCancelConfirmBill() {
        click(cancelConfirmBillBtn);
        try {
            waitForInvisibility(confirmDialogLocator);
        } catch (Exception ignored) {
        }
    }

    public void clickAddBiller() {
        click(addBillerBtn);
    }

    public boolean isAddBillerDialogDisplayed() {
        return isElementDisplayed(By.cssSelector("[data-testid='add-biller-dialog']"));
    }

    public void enterNewBillerName(String name) {
        clearAndType(addBillerNameInput, name);
    }

    public void clickSaveNewBiller() {
        click(saveAddBillerBtn);
    }

    public void clickCancelNewBiller() {
        click(cancelAddBillerBtn);
        try {
            waitForInvisibility(By.cssSelector("[data-testid='add-biller-dialog']"));
        } catch (Exception ignored) {
        }
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorBannerLocator);
    }

    public String getErrorMessage() {
        return getText(errorMessageBanner);
    }
}
