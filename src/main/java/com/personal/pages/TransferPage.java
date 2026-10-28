package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class TransferPage extends BasePage {

    @FindBy(css = "[data-testid='transfer-page']")
    private WebElement transferPageContainer;

    @FindBy(css = "[data-testid='transfer-page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='transfer-form']")
    private WebElement transferForm;

    @FindBy(css = "[data-testid='transfer-from-select']")
    private WebElement transferFromSelectTrigger;

    @FindBy(css = "[data-testid='transfer-to-select']")
    private WebElement transferToSelectTrigger;

    @FindBy(css = "[data-testid='transfer-amount-input']")
    private WebElement transferAmountInput;

    @FindBy(css = "[data-testid='transfer-available-balance']")
    private WebElement availableBalanceText;

    @FindBy(css = "[data-testid='review-transfer-btn']")
    private WebElement reviewTransferBtn;

    // Confirmation dialog
    @FindBy(css = "[data-testid='transfer-confirm-dialog']")
    private WebElement confirmDialog;

    @FindBy(css = "[data-testid='transfer-confirm-title']")
    private WebElement confirmTitle;

    @FindBy(css = "[data-testid='transfer-confirm-summary']")
    private WebElement confirmSummary;

    @FindBy(css = "[data-testid='confirm-transfer-btn']")
    private WebElement confirmTransferBtn;

    @FindBy(css = "[data-testid='cancel-confirm-transfer-btn']")
    private WebElement cancelConfirmTransferBtn;

    // Error / Notice elements
    @FindBy(css = "[data-testid='transfer-error-message']")
    private WebElement errorMessageBanner;

    @FindBy(css = "[data-testid='transfer-frozen-banner']")
    private WebElement frozenBanner;

    private final By fromOptionsLocator = By.cssSelector("[data-testid='transfer-from-option']");
    private final By toOptionsLocator = By.cssSelector("[data-testid='transfer-to-option']");
    private final By confirmDialogLocator = By.cssSelector("[data-testid='transfer-confirm-dialog']");
    private final By errorBannerLocator = By.cssSelector("[data-testid='transfer-error-message']");

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTransferPageDisplayed() {
        return isElementDisplayed(transferPageContainer) || isElementDisplayed(pageTitle);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public void selectFromAccount(int index) {
        click(transferFromSelectTrigger);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fromOptionsLocator));
        if (index < options.size()) {
            options.get(index).click();
        }
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
    }

    public void selectToAccount(int index) {
        click(transferToSelectTrigger);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(toOptionsLocator));
        if (index < options.size()) {
            options.get(index).click();
        }
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
    }

    public void enterAmount(String amount) {
        clearAndType(transferAmountInput, amount);
    }

    public String getAvailableBalance() {
        if (isElementDisplayed(availableBalanceText)) {
            return getText(availableBalanceText);
        }
        return "";
    }

    public void clickReviewTransfer() {
        scrollToElement(reviewTransferBtn);
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
        try {
            click(reviewTransferBtn);
        } catch (Exception e) {
            jsClick(reviewTransferBtn);
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

    public void clickConfirmTransfer() {
        try {
            click(confirmTransferBtn);
        } catch (Exception e) {
            jsClick(confirmTransferBtn);
        }
    }

    public void clickCancelConfirmTransfer() {
        click(cancelConfirmTransferBtn);
        try {
            waitForInvisibility(confirmDialogLocator);
        } catch (Exception ignored) {
        }
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorBannerLocator);
    }

    public String getErrorMessage() {
        return getText(errorMessageBanner);
    }

    public boolean isFrozenAccountBannerDisplayed() {
        return isElementDisplayed(frozenBanner);
    }

    public void performTransfer(int fromIdx, int toIdx, String amount) {
        selectFromAccount(fromIdx);
        selectToAccount(toIdx);
        enterAmount(amount);
        clickReviewTransfer();
        clickConfirmTransfer();
    }
}
