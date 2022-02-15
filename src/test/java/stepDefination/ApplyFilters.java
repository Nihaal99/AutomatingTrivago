package stepDefination;

import DriverSetup.Driver;
import Pages.HotelsListPage;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;

public class ApplyFilters extends Driver {
    HotelsListPage hlp=new HotelsListPage(driver);
    Actions action= new Actions(driver);
    @When("Property type is hotel")
    public void property_type_is_hotel() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hlp.hotelBtnFilter().click();
    }

    @When("Guest rating is excellent")
    public void guest_rating_is_excellent() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.moveToElement(hlp.getGuestRatingBtn()).perform();
        hlp.getRatingBtn().click();
    }

    @When("Hotel class is three star")
    public void hotel_class_is_three_star() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.moveToElement(hlp.getMoreFiltersBtn()).perform();
        hlp.getThreeStarBtn().click();

    }

    @When("Which have free cancelletion facility")
    public void which_have_free_cancelletion_facility() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hlp.getFeeCancelBtn().click();
    }

    @When("Free breakfast")
    public void free_breakfast() {
        hlp.getFreeBreakfastBtn().click();
    }

    @When("Is family friendly")
    public void is_family_friendly() {
        hlp.getFamilyFriendlyBtn().click();
    }

    @When("Sorted by rating and recommended")
    public void sorted_by_rating_and_recommended() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.moveToElement(hlp.getSortByBtn()).click().perform();
        hlp.getRatingNRmBtn().click();
    }
}
