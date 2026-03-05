package com.trivago.automation.driver;

import com.trivago.automation.config.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverContext {

    private static final Logger logger = LoggerFactory.getLogger(DriverContext.class);
    //parallel execution ensuring thread safe operation
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    //here config.properties file actually loads
    private static final ConfigProperties config = ConfigFactory.create(ConfigProperties.class);
    //instance of WebdriverFactory class
    private static final WebDriverFactory webDriverFactory = new WebDriverFactory(config);

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            logger.info("Creating new WebDriver instance for thread: {}", Thread.currentThread().getId());
            driverThreadLocal.set(webDriverFactory.createDriver());
        }
        return driverThreadLocal.get();
    }

    public static void initializeDriver() {
        if (driverThreadLocal.get() == null) {
            logger.info("Initializing WebDriver");
            driverThreadLocal.set(webDriverFactory.createDriver());
        }
    }

    public static void closeDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            logger.info("Closing WebDriver for thread: {}", Thread.currentThread().getId());
            webDriverFactory.closeDriver(driver);
            driverThreadLocal.remove();
        }
    }

    public static ConfigProperties getConfig() {
        return config;
    }

    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }

    public static void resetDriver() {
        closeDriver();
        initializeDriver();
    }
}
