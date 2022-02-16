//JUnit Runner file
package cucumberOptions;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
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
/*
*@RunWith(Cucumber.class) will run runner class as JUnit test
*For using runner file as testNG we need to comment JUnit from runner file(eg.In line 8)
*We need to extend testRunner as below
* Also import @CucumberOptions related to testNG
*/
public class testRunner extends AbstractTestNGCucumberTests {

}
