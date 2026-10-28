package com.personal.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

/**
 * ExtentManager initializes and manages ExtentReports instance and Spark Reporter configuration.
 */
public class ExtentManager {

    private static ExtentReports extent;
    private static final String REPORT_DIR = "reports";
    private static final String REPORT_FILE = "reports/ExtentReport.html";

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            createReportDirectory();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_FILE);

            // Report appearance and styling
            sparkReporter.config().setDocumentTitle("QA Playground Bank Automation Report");
            sparkReporter.config().setReportName("Bank Application Test Execution Report");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // System environment details
            extent.setSystemInfo("Application", "QA Playground Bank Demo");
            extent.setSystemInfo("Environment", "QA / Staging");
            extent.setSystemInfo("Browser", ConfigReader.getBrowser().toUpperCase());
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Test User", ConfigReader.getStandardUsername());
        }
        return extent;
    }

    private static void createReportDirectory() {
        File dir = new File(REPORT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
