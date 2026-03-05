package com.trivago.automation.stepdefinitions;

import com.trivago.automation.driver.DriverContext;
import com.trivago.automation.pages.HotelsListPage;
import com.trivago.automation.pages.TrivagoHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchHotelSteps {

    private static final Logger logger = LoggerFactory.getLogger(SearchHotelSteps.class);
    private final TrivagoHomePage homePage;
    private final HotelsListPage hotelsListPage;
    private String destination;
    @Getter
    private int adultsCount;
    private int childrenCount;

    public SearchHotelSteps() {
        this.homePage = new TrivagoHomePage();
        this.hotelsListPage = new HotelsListPage();
        logger.info("SearchHotelSteps initialized");
    }

    @Given("User is on trivago homepage")
    public void user_is_on_trivago_homepage() {
        logger.info("Starting test - Opening Trivago homepage");
        String baseUrl = DriverContext.getConfig().getBaseUrl();
        homePage.navigateTo(baseUrl);

        boolean isLoaded = homePage.isHomepageLoaded();
        assert isLoaded : "Homepage failed to load";

        logger.info("Successfully navigated to: {}", baseUrl);
    }

    @When("User enters destination")
    public void user_enters_destination() {
        destination = DriverContext.getConfig().getDestination();
        logger.info("Entering destination: {}", destination);

        homePage.enterDestination(destination);
        homePage.selectSuggestedDestination();

        logger.info("Destination '{}' entered successfully", destination);
    }

    @And("Enters checkIn checkOut details")
    public void enters_check_in_check_out_details() {
        logger.info("Setting check-in and check-out dates");
        homePage.clickCheckInDate();
        homePage.clickCheckOutDate();

        logger.info("Check-in and check-out dates set");
    }

    @And("select rooms and guest details")
    public void select_rooms_and_guest_details() {
        adultsCount = DriverContext.getConfig().getAdultsCount();
        childrenCount = DriverContext.getConfig().getChildrenCount();

        logger.info("Setting room and guest details - Adults: {}, Children: {}",
                adultsCount, childrenCount);

        homePage.clickRoomAndGuestButton();
        homePage.setAdultsCount(adultsCount);
        homePage.setChildrenCount(childrenCount);

        logger.info("Room and guest details set successfully");
    }

    @And("Click on search button")
    public void click_on_search_button() {
        logger.info("Clicking search button to initiate hotel search");

        homePage.clickSearchButton();

        logger.info("Search initiated successfully");
    }

    public String getDestination() {
        return destination;
    }

    public int getChildrenCount() {
        return childrenCount;
    }
}