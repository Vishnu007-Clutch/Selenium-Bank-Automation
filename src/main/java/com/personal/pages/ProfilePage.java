package com.personal.pages;

import com.personal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {

    @FindBy(css = "[data-testid='profile-page']")
    private WebElement profilePageContainer;

    @FindBy(css = "[data-testid='profile-page-title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='personal-info-section']")
    private WebElement personalInfoSection;

    @FindBy(css = "[data-testid='edit-profile-btn']")
    private WebElement editProfileBtn;

    @FindBy(css = "[data-testid='profile-first-name-input']")
    private WebElement firstNameInput;

    @FindBy(css = "[data-testid='profile-last-name-input']")
    private WebElement lastNameInput;

    @FindBy(css = "[data-testid='profile-email-input']")
    private WebElement emailInput;

    @FindBy(css = "[data-testid='profile-phone-input']")
    private WebElement phoneInput;

    @FindBy(css = "[data-testid='profile-address-input']")
    private WebElement addressInput;

    @FindBy(css = "[data-testid='save-profile-btn']")
    private WebElement saveProfileBtn;

    @FindBy(css = "[data-testid='cancel-edit-profile-btn']")
    private WebElement cancelEditProfileBtn;

    @FindBy(css = "[data-testid='profile-save-success']")
    private WebElement profileSaveSuccess;

    // Security & Change Password
    @FindBy(css = "[data-testid='current-password-input']")
    private WebElement currentPasswordInput;

    @FindBy(css = "[data-testid='new-password-input']")
    private WebElement newPasswordInput;

    @FindBy(css = "[data-testid='confirm-password-input']")
    private WebElement confirmPasswordInput;

    @FindBy(css = "[data-testid='save-password-btn']")
    private WebElement savePasswordBtn;

    @FindBy(css = "[data-testid='password-change-success']")
    private WebElement passwordChangeSuccess;

    @FindBy(css = "[data-testid='password-change-error']")
    private WebElement passwordChangeError;

    private final By profileSuccessLocator = By.cssSelector("[data-testid='profile-save-success']");
    private final By passwordSuccessLocator = By.cssSelector("[data-testid='password-change-success']");
    private final By passwordErrorLocator = By.cssSelector("[data-testid='password-change-error']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isProfilePageDisplayed() {
        return isElementDisplayed(profilePageContainer) || isElementDisplayed(pageTitle);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public void clickEditProfile() {
        click(editProfileBtn);
    }

    public void updatePersonalInfo(String firstName, String lastName, String phone, String address) {
        clearAndType(firstNameInput, firstName);
        clearAndType(lastNameInput, lastName);
        clearAndType(phoneInput, phone);
        clearAndType(addressInput, address);
    }

    public void clickSaveProfile() {
        click(saveProfileBtn);
    }

    public void clickCancelEditProfile() {
        click(cancelEditProfileBtn);
    }

    public boolean isProfileSaveSuccessDisplayed() {
        return isElementDisplayed(profileSuccessLocator);
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        clearAndType(currentPasswordInput, currentPassword);
        clearAndType(newPasswordInput, newPassword);
        clearAndType(confirmPasswordInput, confirmPassword);
        click(savePasswordBtn);
    }

    public boolean isPasswordChangeSuccessDisplayed() {
        return isElementDisplayed(passwordSuccessLocator);
    }

    public boolean isPasswordChangeErrorDisplayed() {
        return isElementDisplayed(passwordErrorLocator);
    }

    public String getPasswordChangeErrorMessage() {
        return getText(passwordChangeError);
    }
}
