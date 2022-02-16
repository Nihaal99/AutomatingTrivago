package stepDefination;

import DriverSetup.Driver;
import Pages.HotelsListPage;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class SelectHotel extends Driver {
    HotelsListPage hlp=new HotelsListPage(driver);
    @Test
    @When("The name of hotel and cost displayed")
    public void the_name_of_hotel_and_cost_displayed() {
        System.out.println("The Hotel is: "+hlp.getHotelName().getText());
        System.out.println("The cost is: "+hlp.getHotelPrice().getText());
        logger.log(Status.INFO,"Hotel name and price is displayed in console");
    }

    @Test
    @When("User is redirected to hotel's website for checkout")
    public void user_is_redirected_to_hotel_s_website_for_checkout() {
        Actions action=new Actions(driver);
        action.moveToElement(hlp.getCheckOutBtn()).click().perform();
        logger.log(Status.INFO,"User is redirected to the topmost hotel's website displayed in list");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        takeScreenShot();
    }
    @AfterTest
    @When("Quit Browser")
    public void quit_browser() {
        logger.log(Status.INFO,"Test Case is complete");
        report.flush();
        driver.quit();
    }

}
