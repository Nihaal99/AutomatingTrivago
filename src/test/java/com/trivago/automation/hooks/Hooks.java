package com.trivago.automation.hooks;

import com.trivago.automation.driver.DriverContext;
import com.trivago.automation.utilities.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("=".repeat(80));
        logger.info("SCENARIO STARTED: {}", scenario.getName());
        logger.info("=".repeat(80));

        try {
            DriverContext.initializeDriver();
            logger.info("WebDriver initialized successfully");
            logger.info("Browser: {}", DriverContext.getConfig().getBrowser());
            logger.info("Base URL: {}", DriverContext.getConfig().getBaseUrl());
            logger.info("Environment: {}", DriverContext.getConfig().getEnvironment());
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver", e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("=".repeat(80));
        logger.info("SCENARIO FINISHED: {}", scenario.getName());
        logger.info("Scenario Status: {}", scenario.getStatus());
        logger.info("=".repeat(80));

        try {
            if (scenario.isFailed()) {
                logger.error("Scenario failed. Capturing screenshot...");

                if (DriverContext.isDriverInitialized()) {
                    String screenshotPath = ScreenshotUtils.takeScreenshot(
                            scenario.getName(),
                            true
                    );
                    logger.info("Screenshot saved at: {}", screenshotPath);

                    try {
                        scenario.attach(
                                ScreenshotUtils.getScreenshotAsBytes(DriverContext.getDriver()),
                                "image/png",
                                scenario.getName() + "_failure"
                        );
                    } catch (Exception e) {
                        logger.warn("Could not attach screenshot to report", e);
                    }
                }
            } else {
                logger.info("Scenario passed successfully");
            }
        } catch (Exception e) {
            logger.error("Error during screenshot capture", e);
        } finally {
            try {
                if (DriverContext.isDriverInitialized()) {
                    DriverContext.closeDriver();
                    logger.info("WebDriver closed successfully");
                }
            } catch (Exception e) {
                logger.error("Error closing WebDriver", e);
            }
        }

    }
}
