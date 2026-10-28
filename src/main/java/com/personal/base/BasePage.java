package com.personal.base;

import com.personal.utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * BasePage encapsulates common WebDriver operations and explicit waits.
 * All Page Objects extend BasePage to reuse standardized interaction methods.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for the element to be clickable and performs a click.
     */
    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            jsClick(element);
        }
    }

    /**
     * Waits for the element located by locator to be clickable and performs a click.
     */
    public void click(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            WebElement el = driver.findElement(locator);
            jsClick(el);
        }
    }

    /**
     * Clicks an element using JavaScript Executor if standard click is intercepted.
     */
    public void jsClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Types text into a WebElement after waiting for visibility.
     */
    public void type(WebElement element, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOf(element));
        el.sendKeys(text);
    }

    /**
     * Clears existing content and types new text into a WebElement.
     */
    public void clearAndType(WebElement element, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOf(element));
        el.clear();
        el.sendKeys(text);
    }

    /**
     * Retrieves visible text from a WebElement.
     */
    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
    }

    /**
     * Retrieves visible text from an element located by By locator.
     */
    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }

    /**
     * Checks if a WebElement is displayed on the page without throwing exceptions.
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if an element located by By locator is displayed without blocking.
     */
    public boolean isElementDisplayed(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if a WebElement is enabled.
     */
    public boolean isElementEnabled(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the current page URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Returns the current page Title.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Scrolls the window until the element is in view.
     */
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    /**
     * Waits for element invisibility.
     */
    public boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Helper to wait for transient Base UI / Radix overlay backdrop to disappear.
     */
    public void waitForOverlayToDisappear() {
        try {
            waitForInvisibility(By.cssSelector("div[data-base-ui-inert]"));
        } catch (Exception ignored) {
        }
    }

    /**
     * Helper to select an option in a modern Radix/Shadcn custom select menu.
     * Clicks the trigger to open the options popover, then clicks the specified option.
     */
    public void selectRadixOption(WebElement triggerElement, By optionLocator) {
        click(triggerElement);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
    }

    /**
     * Helper to select the first available option in a Radix select dropdown.
     */
    public void selectFirstRadixOption(WebElement triggerElement, By optionsLocator) {
        click(triggerElement);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));
        if (!options.isEmpty()) {
            options.get(0).click();
        }
    }
}
