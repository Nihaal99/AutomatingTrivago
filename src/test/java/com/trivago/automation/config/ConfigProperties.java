package com.trivago.automation.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
        "file:src/test/resources/config.properties",
        "system:properties",
        "system:env"
})
public interface ConfigProperties extends Config {

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browser.headless")
    @DefaultValue("false")
    boolean isHeadlessBrowser();

    @Key("browser.version")
    @DefaultValue("latest")
    String getBrowserVersion();

    @Key("base.url")
    @DefaultValue("https://www.trivago.in/")
    String getBaseUrl();

    @Key("app.environment")
    @DefaultValue("UAT")
    String getEnvironment();

    @Key("implicit.wait")
    @DefaultValue("10")
    long getImplicitWait();

    @Key("explicit.wait")
    @DefaultValue("15")
    long getExplicitWait();

    @Key("page.load.timeout")
    @DefaultValue("30")
    long getPageLoadTimeout();

    @Key("destination")
    @DefaultValue("New Delhi")
    String getDestination();

    @Key("adults.count")
    @DefaultValue("2")
    int getAdultsCount();

    @Key("children.count")
    @DefaultValue("1")
    int getChildrenCount();

    @Key("rooms.count")
    @DefaultValue("1")
    int getRoomsCount();

    @Key("screenshot.on.failure")
    @DefaultValue("true")
    boolean takeScreenshotOnFailure();

    @Key("log.level")
    @DefaultValue("INFO")
    String getLogLevel();

    @Key("report.dir")
    @DefaultValue("target/reports")
    String getReportDirectory();

    @Key("screenshots.dir")
    @DefaultValue("target/screenshots")
    String getScreenshotsDirectory();

    @Key("parallel.execution")
    @DefaultValue("false")
    boolean isParallelExecution();

    @Key("thread.count")
    @DefaultValue("4")
    int getThreadCount();

    @Key("video.recording")
    @DefaultValue("false")
    boolean enableVideoRecording();

    @Key("retry.count")
    @DefaultValue("1")
    int getRetryCount();
}
