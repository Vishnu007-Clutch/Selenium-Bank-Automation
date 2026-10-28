package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SendMoneyPage extends BasePage {

    @FindBy(css = "[data-testid='send-money-page']")
    private WebElement sendMoneyPageContainer;

    @FindBy(css = "[data-testid='send-money-page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='send-money-form']")
    private WebElement sendMoneyForm;

    @FindBy(css = "[data-testid='payee-select']")
    private WebElement payeeSelectTrigger;

    @FindBy(css = "[data-testid='payee-selected-summary']")
    private WebElement payeeSelectedSummary;

    @FindBy(css = "[data-testid='add-payee-btn']")
    private WebElement addPayeeBtn;

    // Add Payee Dialog
    @FindBy(css = "[data-testid='add-payee-dialog']")
    private WebElement addPayeeDialog;

    @FindBy(css = "[data-testid='add-payee-name-input']")
    private WebElement addPayeeNameInput;

    @FindBy(css = "[data-testid='add-payee-bank-input']")
    private WebElement addPayeeBankInput;

    @FindBy(css = "[data-testid='add-payee-routing-input']")
    private WebElement addPayeeRoutingInput;

    @FindBy(css = "[data-testid='add-payee-account-input']")
    private WebElement addPayeeAccountInput;

    @FindBy(css = "[data-testid='save-payee-btn'], [data-testid='save-add-payee-btn']")
    private WebElement savePayeeBtn;

    @FindBy(css = "[data-testid='cancel-add-payee-btn']")
    private WebElement cancelAddPayeeBtn;

  
    @FindBy(css = "[data-testid='send-from-account-select']")
    private WebElement sendFromAccountSelectTrigger;

    @FindBy(css = "[data-testid='send-amount-input']")
    private WebElement sendAmountInput;

    @FindBy(css = "[data-testid='send-note-input']")
    private WebElement sendNoteInput;

    @FindBy(css = "[data-testid='review-send-btn']")
    private WebElement reviewSendBtn;


    @FindBy(css = "[data-testid='send-money-confirm-dialog']")
    private WebElement confirmDialog;

    @FindBy(css = "[data-testid='send-confirm-summary']")
    private WebElement confirmSummary;

    @FindBy(css = "[data-testid='confirm-send-btn']")
    private WebElement confirmSendBtn;

    @FindBy(css = "[data-testid='cancel-confirm-send-btn']")
    private WebElement cancelConfirmSendBtn;

    @FindBy(css = "[data-testid='send-money-error']")
    private WebElement errorMessageBanner;

    @FindBy(css = "[data-testid='send-money-frozen-banner']")
    private WebElement frozenBanner;

    private final By payeeOptionsLocator = By.cssSelector("[data-testid='payee-select-option']");
    private final By fromOptionsLocator = By.cssSelector("[data-testid='send-from-option']");
    private final By confirmDialogLocator = By.cssSelector("[data-testid='send-money-confirm-dialog']");
    private final By errorBannerLocator = By.cssSelector("[data-testid='send-money-error']");

    public SendMoneyPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSendMoneyPageDisplayed() {
        return isElementDisplayed(sendMoneyPageContainer) || isElementDisplayed(pageTitle);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public void selectPayee(int index) {
        click(payeeSelectTrigger);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(payeeOptionsLocator));
        if (index < options.size()) {
            options.get(index).click();
        }
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
    }

    public void selectFromAccount(int index) {
        click(sendFromAccountSelectTrigger);
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
        clearAndType(sendAmountInput, amount);
    }

    public void enterNote(String note) {
        clearAndType(sendNoteInput, note);
    }

    public void clickReviewSend() {
        scrollToElement(reviewSendBtn);
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
        try {
            click(reviewSendBtn);
        } catch (Exception e) {
            jsClick(reviewSendBtn);
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

    public void clickConfirmSend() {
        try {
            click(confirmSendBtn);
        } catch (Exception e) {
            jsClick(confirmSendBtn);
        }
    }

    public void clickCancelConfirmSend() {
        click(cancelConfirmSendBtn);
        try {
            waitForInvisibility(confirmDialogLocator);
        } catch (Exception ignored) {
        }
    }

    public void clickAddPayee() {
        click(addPayeeBtn);
    }

    public boolean isAddPayeeModalDisplayed() {
        return isElementDisplayed(By.cssSelector("[data-testid='add-payee-dialog']"));
    }

    public void fillNewPayeeDetails(String name, String bank, String routing, String account) {
        clearAndType(addPayeeNameInput, name);
        clearAndType(addPayeeBankInput, bank);
        clearAndType(addPayeeRoutingInput, routing);
        clearAndType(addPayeeAccountInput, account);
    }

    public void clickSaveNewPayee() {
        click(savePayeeBtn);
    }

    public void clickCancelNewPayee() {
        click(cancelAddPayeeBtn);
        try {
            waitForInvisibility(By.cssSelector("[data-testid='add-payee-dialog']"));
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
