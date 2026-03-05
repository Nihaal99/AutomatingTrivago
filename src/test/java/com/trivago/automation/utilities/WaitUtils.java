package com.trivago.automation.utilities;

import com.trivago.automation.driver.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WaitUtils {

    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);
    private static final long DEFAULT_TIMEOUT = DriverContext.getConfig().getExplicitWait();

    public static WebElement waitForElementPresence(By locator) {
        return waitForElementPresence(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementPresence(By locator, long timeoutInSeconds) {
        try {
            logger.info("Waiting for element presence: {} (timeout: {}s)", locator, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not found within timeout: {}", locator, e);
            throw new RuntimeException("Element not found: " + locator, e);
        }
    }

    public static WebElement waitForElementVisibility(By locator) {
        return waitForElementVisibility(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementVisibility(By locator, long timeoutInSeconds) {
        try {
            logger.info("Waiting for element visibility: {} (timeout: {}s)", locator, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not visible within timeout: {}", locator, e);
            throw new RuntimeException("Element not visible: " + locator, e);
        }
    }

    public static WebElement waitForElementClickability(By locator) {
        return waitForElementClickability(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementClickability(By locator, long timeoutInSeconds) {
        try {
            logger.info("Waiting for element clickability: {} (timeout: {}s)", locator, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Element not clickable within timeout: {}", locator, e);
            throw new RuntimeException("Element not clickable: " + locator, e);
        }
    }

    public static boolean waitForElementInvisibility(By locator) {
        return waitForElementInvisibility(locator, DEFAULT_TIMEOUT);
    }

    public static boolean waitForElementInvisibility(By locator, long timeoutInSeconds) {
        try {
            logger.info("Waiting for element invisibility: {} (timeout: {}s)", locator, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element still visible after timeout: {}", locator, e);
            return false;
        }
    }

    public static void waitForAllElementsPresence(By locator) {
        waitForAllElementsPresence(locator, DEFAULT_TIMEOUT);
    }

    public static void waitForAllElementsPresence(By locator, long timeoutInSeconds) {
        try {
            logger.info("Waiting for all elements presence: {} (timeout: {}s)", locator, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            logger.error("Elements not found within timeout: {}", locator, e);
        }
    }

    public static boolean waitForPageTitle(String title) {
        return waitForPageTitle(title, DEFAULT_TIMEOUT);
    }

    public static boolean waitForPageTitle(String title, long timeoutInSeconds) {
        try {
            logger.info("Waiting for page title: {} (timeout: {}s)", title, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.titleIs(title));
        } catch (Exception e) {
            logger.error("Page title not matched within timeout: {}", title, e);
            return false;
        }
    }

    public static boolean waitForUrlContains(String urlPortion) {
        return waitForUrlContains(urlPortion, DEFAULT_TIMEOUT);
    }

    public static boolean waitForUrlContains(String urlPortion, long timeoutInSeconds) {
        try {
            logger.info("Waiting for URL contains: {} (timeout: {}s)", urlPortion, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.urlContains(urlPortion));
        } catch (Exception e) {
            logger.error("URL not contains within timeout: {}", urlPortion, e);
            return false;
        }
    }

    public static void staticWait(long milliseconds) {
        try {
            logger.debug("Static wait for {} ms", milliseconds);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.warn("Sleep interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
