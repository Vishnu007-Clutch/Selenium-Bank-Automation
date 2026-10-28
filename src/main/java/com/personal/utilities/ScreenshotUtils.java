package com.personal.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots of test execution and failures.
 */
public class ScreenshotUtils {

    private static final String SCREENSHOTS_DIR = "screenshots";

    /**
     * Captures a screenshot and saves it to the screenshots directory with a timestamp.
     *
     * @param driver         the active WebDriver instance
     * @param screenshotName the base name for the screenshot file
     * @return the relative file path of the captured screenshot
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) {
            System.err.println("Driver is null. Cannot capture screenshot.");
            return null;
        }

        try {
            // Ensure screenshots directory exists
            Path dirPath = Paths.get(SCREENSHOTS_DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String sanitizedName = screenshotName.replaceAll("[^a-zA-Z0-9_-]", "_");
            String fileName = sanitizedName + "_" + timestamp + ".png";
            Path targetPath = dirPath.resolve(fileName);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), targetPath);

            System.out.println("Screenshot captured: " + targetPath.toAbsolutePath());
            return targetPath.toString();
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
