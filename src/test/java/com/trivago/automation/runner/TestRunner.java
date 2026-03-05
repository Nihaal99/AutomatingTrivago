package com.trivago.automation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.trivago.automation",
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json"
        },
        monochrome = false,
        dryRun = false,
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
