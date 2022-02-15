//JUnit Runner file
package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Feature",
        glue = "stepDefination",
        plugin={"pretty",
        "json:target/MyReports/report.json",
        "junit:target/MyReports/report.xml"},
        //If monochrome is true then console output without colours
        monochrome = false,
        /*If dryRun is true then it will check line by line each steps of feature file
       if it is linked to stepDefination file or not*/
        dryRun = false,
        publish = true

)
public class testRunner {

}
