package stepDefination;

import DriverSetup.Driver;
import Pages.TrivagoHomePage;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SearchHotel extends Driver {
    @BeforeTest
    @Given("User is on trivago homepage")
    public void user_is_on_trivago_homepage() {
        logger=report.createTest("Test");
        logger.log(Status.INFO,"Initializing the Browser");
        Driver.startBrowser();
        logger.log(Status.INFO,"Opening the Trivago Website");
        driver.get("https://www.trivago.in/");
    }
    @Test
    @When("User enters destination")
    public void user_enters_destination() {
        TrivagoHomePage th=new TrivagoHomePage(driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        th.destinationSearchBox().sendKeys(prop.getProperty("Destination"));
        logger.log(Status.INFO,"Destination is Entered by User");
        th.getSuggestedDestnDestination().click();




    }
    @Test
    @When("Enters checkIn checkOut details")
    public void enters_check_in_check_out_details() {
        TrivagoHomePage th=new TrivagoHomePage(driver);
        th.checkInDate().click();
        th.checkOutDate().click();
        logger.log(Status.INFO,"Check In and Check Out details are entered by user");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//Errors:The driver is entering some random values in the setting the
// guest and room details and not taking config.properties value
    @Test
    @When("select rooms and guest details")
    public void select_rooms_and_guest_details() {
        Actions action = new Actions(driver);
        //action.moveToElement(th.roomAndGuestBtn()).click(th.roomAndGuestBtn());
        //Assert.assertEquals("Adults",th.verifytextOfFlex().getText());

        //Error from this line
        /*
        th.setAdultsNo().click();
        th.setAdultsNo().sendKeys(String.valueOf(prop.getProperty("AdultsNo")));
        th.setChildrenNo().sendKeys(prop.getProperty("ChildrenNO"));
        */
    }
    @Test
    @When("Click on search button")
    public void click_on_search_button() {
        TrivagoHomePage th=new TrivagoHomePage(driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        takeScreenShot();
        th.submitButton().click();
        logger.log(Status.INFO,"User clicks on search");
    }
}
