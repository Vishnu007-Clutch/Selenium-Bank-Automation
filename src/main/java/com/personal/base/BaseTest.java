package com.personal.base;

import com.personal.utilities.ConfigReader;
import com.personal.utilities.ScreenshotUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.personal.utilities.TestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

/**
 * BaseTest initializes WebDriver, loads base URLs, and manages browser lifecycle.
 * All TestNG test classes extend BaseTest.
 */
@Listeners(TestListener.class)
public class BaseTest {

    static {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger rootLogger = java.util.logging.LogManager.getLogManager().getLogger("");
        if (rootLogger != null) {
            rootLogger.setLevel(java.util.logging.Level.SEVERE);
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    handler.setLevel(java.util.logging.Level.SEVERE);
                }
            }
        }
    }

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getBrowser();
        boolean isHeadless = ConfigReader.isHeadless();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            } else {
                options.addArguments("--start-maximized");
            }

            driver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        if (!ConfigReader.isHeadless()) {
            driver.manage().window().maximize();
        }

        // Navigate to the base URL
        driver.get(ConfigReader.getUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            // Take screenshot if test failed
            if (result.getStatus() == ITestResult.FAILURE) {
                ScreenshotUtils.captureScreenshot(driver, "FAILED_" + result.getName());
            }
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
