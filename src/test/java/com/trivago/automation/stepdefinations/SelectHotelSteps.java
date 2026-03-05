package com.trivago.automation.stepdefinitions;

import com.trivago.automation.pages.HotelsListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectHotelSteps {

    private static final Logger logger = LoggerFactory.getLogger(SelectHotelSteps.class);
    private final HotelsListPage hotelsListPage;
    private String selectedHotelName;
    private String selectedHotelPrice;

    public SelectHotelSteps() {
        this.hotelsListPage = new HotelsListPage();
        logger.info("SelectHotelSteps initialized");
    }

    @When("The name of hotel and cost displayed")
    public void the_name_of_hotel_and_cost_displayed() {
        logger.info("Retrieving top hotel details");

        selectedHotelName = hotelsListPage.getTopHotelName();
        selectedHotelPrice = hotelsListPage.getTopHotelPrice();

        hotelsListPage.displayTopHotelDetails();

        assert selectedHotelName != null && !selectedHotelName.isEmpty()
                : "Hotel name not found";
        assert selectedHotelPrice != null && !selectedHotelPrice.isEmpty()
                : "Hotel price not found";

        logger.info("Hotel details retrieved and displayed successfully");
    }

    @And("User is redirected to hotel's website for checkout")
    public void user_is_redirected_to_hotel_s_website_for_checkout() {
        logger.info("Redirecting to hotel website for checkout");
        logger.info("Selected Hotel: {}, Price: {}", selectedHotelName, selectedHotelPrice);

        hotelsListPage.clickTopHotelCheckout();

        logger.info("User redirected to hotel website successfully");
    }

    @And("Quit Browser")
    public void quit_browser() {
        logger.info("Test scenario completed successfully");
        logger.info("=".repeat(60));
        logger.info("TEST SUMMARY");
        logger.info("Selected Hotel: {}", selectedHotelName);
        logger.info("Price: {}", selectedHotelPrice);
        logger.info("=".repeat(60));
    }

    public String getSelectedHotelName() {
        return selectedHotelName;
    }

    public String getSelectedHotelPrice() {
        return selectedHotelPrice;
    }

    public boolean areHotelDetailsCaptured() {
        return selectedHotelName != null && !selectedHotelName.isEmpty()
                && selectedHotelPrice != null && !selectedHotelPrice.isEmpty();
    }
}