package com.trivago.automation.utilities;

import com.trivago.automation.driver.DriverContext;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = DriverContext.getConfig().getScreenshotsDirectory();
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS");

    public static String takeScreenshot(String testName, boolean includeTimestamp) {
        try {
            WebDriver driver = DriverContext.getDriver();

            File directory = new File(SCREENSHOT_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
                logger.info("Created screenshot directory: {}", SCREENSHOT_DIR);
            }

            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = includeTimestamp ? "_" + LocalDateTime.now().format(TIMESTAMP) : "";
            String fileName = testName.replaceAll("\\s+", "_") + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + File.separator + fileName;

            File destinationFile = new File(filePath);
            FileUtils.copyFile(sourceFile, destinationFile);

            logger.info("Screenshot saved: {}", filePath);
            return filePath;
        } catch (IOException e) {
            logger.error("Failed to take screenshot", e);
            return null;
        }
    }

    public static String takeScreenshot(String testName) {
        return takeScreenshot(testName, true);
    }

    public static byte[] getScreenshotAsBytes(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            logger.error("Failed to get screenshot bytes", e);
            return new byte[0];
        }
    }

    public static File takeScreenshotAsFile(String testName) {
        try {
            WebDriver driver = DriverContext.getDriver();
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File directory = new File(SCREENSHOT_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String timestamp = LocalDateTime.now().format(TIMESTAMP);
            String fileName = testName.replaceAll("\\s+", "_") + "_" + timestamp + ".png";
            File destinationFile = new File(SCREENSHOT_DIR + File.separator + fileName);

            FileUtils.copyFile(sourceFile, destinationFile);
            logger.info("Screenshot file created: {}", destinationFile.getAbsolutePath());

            return destinationFile;
        } catch (IOException e) {
            logger.error("Failed to create screenshot file", e);
            return null;
        }
    }

    public static String getScreenshotsDirPath() {
        return SCREENSHOT_DIR;
    }
}
