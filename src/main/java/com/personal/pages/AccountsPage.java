package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsPage extends BasePage {

    @FindBy(css = "[data-testid='accounts-page']")
    private WebElement accountsPageContainer;

    @FindBy(css = "[data-testid='accounts-page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='accounts-table']")
    private WebElement accountsTable;

    @FindBy(css = "[data-testid='account-row']")
    private List<WebElement> accountRows;

    @FindBy(css = "[data-testid='account-row-name']")
    private List<WebElement> accountRowNames;

    @FindBy(css = "[data-testid='account-row-balance']")
    private List<WebElement> accountRowBalances;

    @FindBy(css = "[data-testid='account-row-type-badge']")
    private List<WebElement> accountTypeBadges;

    @FindBy(css = "[data-testid='add-account-btn']")
    private WebElement addAccountBtn;

    // Add Account Dialog Elements
    @FindBy(css = "[data-testid='account-form']")
    private WebElement accountForm;

    @FindBy(css = "[data-testid='account-form-dialog-title']")
    private WebElement accountFormTitle;

    @FindBy(css = "[data-testid='account-form-name-input']")
    private WebElement accountNameInput;

    @FindBy(css = "[data-testid='account-form-type-select']")
    private WebElement accountTypeSelectTrigger;

    @FindBy(css = "[data-testid='account-form-accept-terms-checkbox']")
    private WebElement acceptTermsCheckbox;

    @FindBy(css = "[data-testid='save-account-form-btn']")
    private WebElement saveAccountBtn;

    @FindBy(css = "[data-testid='cancel-account-form-btn']")
    private WebElement cancelAccountBtn;

    @FindBy(css = "[data-testid='account-form-error-message']")
    private WebElement formErrorMessage;

    private final By accountTypeOptionsLocator = By.cssSelector("[data-testid='account-form-type-option']");
    private final By formErrorLocator = By.cssSelector("[data-testid='account-form-error-message']");

    public AccountsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountsPageDisplayed() {
        return isElementDisplayed(accountsPageContainer) || isElementDisplayed(pageTitle);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public int getAccountsCount() {
        return accountRows.size();
    }

    public String getFirstAccountName() {
        if (!accountRowNames.isEmpty()) {
            return getText(accountRowNames.get(0));
        }
        return "";
    }

    public String getFirstAccountBalance() {
        if (!accountRowBalances.isEmpty()) {
            return getText(accountRowBalances.get(0));
        }
        return "";
    }

    public String getFirstAccountType() {
        if (!accountTypeBadges.isEmpty()) {
            return getText(accountTypeBadges.get(0));
        }
        return "";
    }

    public void clickAddAccount() {
        click(addAccountBtn);
    }

    public boolean isAddAccountModalDisplayed() {
        return isElementDisplayed(By.cssSelector("[data-testid='account-form']"));
    }

    public void enterAccountName(String name) {
        clearAndType(accountNameInput, name);
    }

    public void selectAccountType() {
        selectFirstRadixOption(accountTypeSelectTrigger, accountTypeOptionsLocator);
    }

    public void acceptTerms() {
        click(acceptTermsCheckbox);
    }

    public void clickSaveAccount() {
        click(saveAccountBtn);
    }

    public void clickCancelAccount() {
        click(cancelAccountBtn);
        try {
            waitForInvisibility(By.cssSelector("[data-testid='account-form']"));
        } catch (Exception ignored) {
        }
    }

    public boolean isFormErrorDisplayed() {
        return isElementDisplayed(formErrorLocator);
    }

    public String getFormErrorMessage() {
        return getText(formErrorMessage);
    }

    public void createNewAccount(String name) {
        clickAddAccount();
        enterAccountName(name);
        selectAccountType();
        acceptTerms();
        clickSaveAccount();
    }
}
