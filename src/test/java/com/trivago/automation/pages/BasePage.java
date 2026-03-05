package com.trivago.automation.pages;

import com.trivago.automation.driver.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * BasePage - Base class for all page objects
 *
 * Provides two sets of methods:
 * 1. WebElement methods (for @FindBy annotations)
 * 2. By locator methods (for By.xpath/id/css etc)
 *
 * This allows flexibility - use either approach!
 */
public class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverContext.getDriver();
        // For @FindBy support (if used)
        PageFactory.initElements(driver, this);
        logger.info("BasePage initialized with WebDriver");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // WEBDRIVER HELPER METHODS (EXISTING - for compatibility)
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Get WebDriver instance
     */
    protected WebDriver getDriver() {
        return driver;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BY LOCATOR METHODS (NEW - for modern approach with explicit waits)
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Click element using By locator
     * Note: You should use explicit wait BEFORE calling this
     */
    public void click(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.click();
            logger.debug("Clicked on element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Send keys to element using By locator
     * Note: Element should be visible before calling this
     */
    public void sendKeys(By locator, String text) {
        try {
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
            logger.debug("Sent keys to element: {} - Text: {}", locator, text);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Hover over element using By locator
     * Uses Actions class for hover functionality
     */
    public void hover(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            logger.debug("Hovered over element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to hover over element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Get text from element using By locator
     */
    public String getText(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            String text = element.getText();
            logger.debug("Got text from element: {} - Text: {}", locator, text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Find multiple elements using By locator
     * Returns fresh list every time (no stale references)
     */
    public List<WebElement> findElements(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            logger.debug("Found {} elements for locator: {}", elements.size(), locator);
            return elements;
        } catch (Exception e) {
            logger.error("Failed to find elements: {}", locator, e);
            throw e;
        }
    }

    /**
     * Find single element using By locator
     * Returns the element without clicking or interacting
     */
    public WebElement findElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            logger.debug("Found element: {}", locator);
            return element;
        } catch (Exception e) {
            logger.error("Failed to find element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Check if element is displayed using By locator
     */
    public boolean isDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            boolean displayed = element.isDisplayed();
            logger.debug("Element displayed: {} - Result: {}", locator, displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("Element not displayed or not found: {}", locator);
            return false;
        }
    }

    /**
     * Check if element is enabled using By locator
     */
    public boolean isEnabled(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            boolean enabled = element.isEnabled();
            logger.debug("Element enabled: {} - Result: {}", locator, enabled);
            return enabled;
        } catch (Exception e) {
            logger.debug("Element not enabled or not found: {}", locator);
            return false;
        }
    }

    /**
     * Get attribute value using By locator
     */
    public String getAttribute(By locator, String attributeName) {
        try {
            WebElement element = driver.findElement(locator);
            String value = element.getAttribute(attributeName);
            logger.debug("Got attribute {} from element: {} - Value: {}", attributeName, locator, value);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get attribute {} from element: {}", attributeName, locator, e);
            throw e;
        }
    }

    /**
     * Clear text from element using By locator
     */
    public void clear(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.clear();
            logger.debug("Cleared element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to clear element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Submit form using By locator
     */
    public void submit(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.submit();
            logger.debug("Submitted form: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to submit form: {}", locator, e);
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // WEBELEMENT METHODS (EXISTING - for backwards compatibility)
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Click WebElement (legacy method for @FindBy support)
     */
    public void click(WebElement element) {
        try {
            element.click();
            logger.debug("Clicked on WebElement");
        } catch (Exception e) {
            logger.error("Failed to click WebElement", e);
            throw e;
        }
    }

    /**
     * Send keys to WebElement (legacy method for @FindBy support)
     */
    public void sendKeys(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.debug("Sent keys to WebElement - Text: {}", text);
        } catch (Exception e) {
            logger.error("Failed to send keys to WebElement", e);
            throw e;
        }
    }

    /**
     * Hover over WebElement (legacy method for @FindBy support)
     */
    public void hover(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            logger.debug("Hovered over WebElement");
        } catch (Exception e) {
            logger.error("Failed to hover over WebElement", e);
            throw e;
        }
    }

    /**
     * Get text from WebElement (legacy method for @FindBy support)
     */
    public String getText(WebElement element) {
        try {
            String text = element.getText();
            logger.debug("Got text from WebElement - Text: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from WebElement", e);
            throw e;
        }
    }

    /**
     * Check if WebElement is displayed (legacy method)
     */
    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.debug("WebElement not displayed or not found");
            return false;
        }
    }

    /**
     * Check if WebElement is enabled (legacy method)
     */
    public boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            logger.debug("WebElement not enabled or not found");
            return false;
        }
    }

    /**
     * Get attribute value from WebElement (legacy method)
     */
    public String getAttribute(WebElement element, String attributeName) {
        try {
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            logger.error("Failed to get attribute from WebElement", e);
            throw e;
        }
    }

    /**
     * Clear WebElement (legacy method)
     */
    public void clear(WebElement element) {
        try {
            element.clear();
            logger.debug("Cleared WebElement");
        } catch (Exception e) {
            logger.error("Failed to clear WebElement", e);
            throw e;
        }
    }

    /**
     * Submit form using WebElement (legacy method)
     */
    public void submit(WebElement element) {
        try {
            element.submit();
            logger.debug("Submitted form");
        } catch (Exception e) {
            logger.error("Failed to submit form", e);
            throw e;
        }
    }

    public void navigateTo(String url) {
        try {
            logger.info("Navigating to URL: {}", url);
            driver.get(url);
            logger.info("Successfully navigated to: {}", url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw e;
        }
    }
}