package com.trivago.automation.driver;

import com.trivago.automation.config.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
/*
* two classes are provided in driver package as seperation of concerns
* DriverContext->DriverManager manages the lifecycle of driver
* WebDriverFactory->Driver Builder
* */
public class WebDriverFactory {
    WebDriver driver;
    //logger will record the logs
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    /*it adds ConfigProperties interface as dependency to get specific instructions like
       whether to be headless what is timeout etc*/
    private final ConfigProperties config;

    public WebDriverFactory(ConfigProperties config) {
        this.config = config;
    }

    //logic for selecting browser provided in config.properties file via ConfigProperties interface
    public WebDriver createDriver() {
        String browserName = config.getBrowser().toUpperCase();
        logger.info("Initializing WebDriver for browser: {}", browserName);


        if (browserName.equalsIgnoreCase("CHROME")) {
            driver = createChromeDriver();
        } else if (browserName.equalsIgnoreCase("FIREFOX")) {
            driver = createFirefoxDriver();
        } else if (browserName.equalsIgnoreCase("EDGE")) {
            driver = createEdgeDriver();
        } else if (browserName.equalsIgnoreCase("SAFARI")) {
            driver = createSafariDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        configureDriver(driver);
        logger.info("WebDriver initialized successfully");
        return driver;
    }

    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (config.isHeadlessBrowser()) {
            options.addArguments("--headless");
            logger.info("Running Chrome in headless mode");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        //standard production ready code so that site does not identify automation scripts as bot
        options.addArguments("--disable-blink-features=AutomationControlled");
        //Removes the "Chrome is being controlled by automated software" banner.
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        return new ChromeDriver(options);
    }

    private WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        if (config.isHeadlessBrowser()) {
            options.addArguments("--headless");
            logger.info("Running Firefox in headless mode");
        }

        return new FirefoxDriver(options);
    }

    private WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();

        if (config.isHeadlessBrowser()) {
            options.addArguments("--headless");
            logger.info("Running Edge in headless mode");
        }

        return new EdgeDriver(options);
    }

    private WebDriver createSafariDriver() {
        logger.info("Safari driver initialization (note: Safari requires native driver)");
        return new SafariDriver();
    }

    private void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.getPageLoadTimeout()));

        driver.manage().window().maximize();

        logger.info("WebDriver configured with timeouts - Implicit: {}s, Explicit: {}s, PageLoad: {}s",
                config.getImplicitWait(), config.getExplicitWait(), config.getPageLoadTimeout());
    }

    public void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed successfully");
        }
    }
}
