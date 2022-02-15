package stepDefination;

import DriverSetup.Driver;
import Pages.TrivagoHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;

public class SearchHotel extends Driver {
    @Given("User is on trivago homepage")
    public void user_is_on_trivago_homepage() {
        Driver.startBrowser();
        driver.get("https://www.trivago.in/");
    }

    @When("User enters destination")
    public void user_enters_destination() {
        TrivagoHomePage th=new TrivagoHomePage(driver);
        th.destinationSearchBox().sendKeys(prop.getProperty("Destination"));
        th.getSuggestedDestnDestination().click();



    }

    @When("Enters checkIn checkOut details")
    public void enters_check_in_check_out_details() {
        TrivagoHomePage th=new TrivagoHomePage(driver);
        th.checkInDate().click();
        th.checkOutDate().click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//Errors:The driver is entering some random values in the setting the
// guest and room details and not taking config.properties value
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

    @When("Click on search button")
    public void click_on_search_button() {
        TrivagoHomePage th=new TrivagoHomePage(driver);
        th.submitButton().click();
    }
}
