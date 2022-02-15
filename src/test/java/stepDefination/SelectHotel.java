package stepDefination;

import DriverSetup.Driver;
import Pages.HotelsListPage;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;

public class SelectHotel extends Driver {
    HotelsListPage hlp=new HotelsListPage(driver);
    @When("The name of hotel and cost displayed")
    public void the_name_of_hotel_and_cost_displayed() {
        System.out.println("The Hotel is: "+hlp.getHotelName().getText());
        System.out.println("The cost is: "+hlp.getHotelPrice().getText());
    }


    @When("User is redirected to hotel's website for checkout")
    public void user_is_redirected_to_hotel_s_website_for_checkout() {
        Actions action=new Actions(driver);
        action.moveToElement(hlp.getCheckOutBtn()).click().perform();
    }

}
