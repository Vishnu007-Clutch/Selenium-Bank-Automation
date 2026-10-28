package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    @FindBy(css = "[data-testid='bank-login-page']")
    private WebElement loginPageContainer;

    @FindBy(css = "[data-testid='login-page-title']")
    private WebElement loginTitle;

    @FindBy(css = "[data-testid='login-username-input']")
    private WebElement usernameInput;

    @FindBy(css = "[data-testid='login-password-input']")
    private WebElement passwordInput;

    @FindBy(css = "[data-testid='login-remember-me-checkbox']")
    private WebElement rememberMeCheckbox;

    @FindBy(css = "[data-testid='login-submit-btn']")
    private WebElement loginSubmitBtn;

    @FindBy(css = "[data-testid='login-error-banner']")
    private WebElement errorBanner;

    @FindBy(css = "[data-testid='login-error-message']")
    private WebElement errorMessage;

    @FindBy(css = "[data-testid='forgot-password-link']")
    private WebElement forgotPasswordLink;

    @FindBy(css = "[data-testid='test-credentials-panel']")
    private WebElement testCredentialsPanel;

    private final By errorBannerLocator = By.cssSelector("[data-testid='login-error-banner']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginPageContainer) || isElementDisplayed(usernameInput);
    }

    public String getPageHeading() {
        return getText(loginTitle);
    }

    public void enterUsername(String username) {
        clearAndType(usernameInput, username);
    }

    public void enterPassword(String password) {
        clearAndType(passwordInput, password);
    }

    public void toggleRememberMe() {
        click(rememberMeCheckbox);
    }

    public boolean isRememberMeSelected() {
        @SuppressWarnings("deprecation")
		String dataState = rememberMeCheckbox.getAttribute("data-state");
        @SuppressWarnings("deprecation")
		String ariaChecked = rememberMeCheckbox.getAttribute("aria-checked");
        return "checked".equalsIgnoreCase(dataState) || "true".equalsIgnoreCase(ariaChecked) || rememberMeCheckbox.isSelected();
    }

    public void clickLoginButton() {
        click(loginSubmitBtn);
    }

    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage(driver);
    }

    public boolean isErrorBannerDisplayed() {
        return isElementDisplayed(errorBannerLocator);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public ForgotPasswordPage clickForgotPassword() {
        click(forgotPasswordLink);
        return new ForgotPasswordPage(driver);
    }

    public boolean isUsernameInputDisplayed() {
        return isElementDisplayed(usernameInput);
    }

    public boolean isPasswordInputDisplayed() {
        return isElementDisplayed(passwordInput);
    }

    public boolean isRememberMeCheckboxDisplayed() {
        return isElementDisplayed(rememberMeCheckbox);
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginSubmitBtn);
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return isElementDisplayed(forgotPasswordLink);
    }

    public void clearCredentials() {
        usernameInput.clear();
        passwordInput.clear();
    }

    public boolean waitForErrorBannerVisible() {
        return isElementDisplayed(errorBannerLocator);
    }

    public boolean isTestCredentialsPanelDisplayed() {
        return isElementDisplayed(testCredentialsPanel);
    }
}
