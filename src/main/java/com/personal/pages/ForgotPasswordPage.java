package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage {

    @FindBy(css = "[data-testid='forgot-password-page']")
    private WebElement forgotPasswordPageContainer;

    @FindBy(css = "[data-testid='forgot-password-card']")
    private WebElement forgotPasswordCard;

    @FindBy(css = "[data-testid='forgot-password-form']")
    private WebElement forgotPasswordForm;

    @FindBy(css = "[data-testid='forgot-email-input']")
    private WebElement emailInput;

    @FindBy(css = "[data-testid='forgot-password-submit-btn']")
    private WebElement submitBtn;

    @FindBy(css = "[data-testid='forgot-password-success'], [data-testid='forgot-password-success-msg']")
    private WebElement successBanner;

    @FindBy(css = "[data-testid='forgot-password-error']")
    private WebElement errorBanner;

    @FindBy(css = "[data-testid='back-to-login-link']")
    private WebElement backToLoginLink;

    private final By successBannerLocator = By.cssSelector("[data-testid='forgot-password-success'], [data-testid='forgot-password-success-msg']");
    private final By errorBannerLocator = By.cssSelector("[data-testid='forgot-password-error']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public boolean isForgotPasswordPageDisplayed() {
        return isElementDisplayed(forgotPasswordPageContainer) || isElementDisplayed(forgotPasswordCard);
    }

    public void enterEmail(String email) {
        clearAndType(emailInput, email);
    }

    public void clickSubmit() {
        click(submitBtn);
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successBannerLocator);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorBannerLocator);
    }

    public LoginPage clickBackToLogin() {
        click(backToLoginLink);
        return new LoginPage(driver);
    }
}
