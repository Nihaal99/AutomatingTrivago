package com.trivago.automation.stepdefinitions;

import com.trivago.automation.pages.HotelsListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplyFiltersSteps {

    private static final Logger logger = LoggerFactory.getLogger(ApplyFiltersSteps.class);
    private final HotelsListPage hotelsListPage;

    public ApplyFiltersSteps() {
        this.hotelsListPage = new HotelsListPage();
        logger.info("ApplyFiltersSteps initialized");
    }

    @When("Property type is hotel")
    public void property_type_is_hotel() {
        logger.info("Applying property type filter - Hotel");

        boolean isPageLoaded = hotelsListPage.isHotelsListPageLoaded();
        assert isPageLoaded : "Hotels list page not loaded";

        hotelsListPage.applyHotelTypeFilter();
        hotelsListPage.waitForFiltersToApply();

        logger.info("Hotel property type filter applied successfully");
    }

    @And("Guest rating is excellent")
    public void guest_rating_is_excellent() {
        logger.info("Applying guest rating filter - Excellent");

        hotelsListPage.applyGuestRatingFilter();
        hotelsListPage.waitForFiltersToApply();

        logger.info("Guest rating filter applied successfully");
    }

    @And("Hotel class is three star")
    public void hotel_class_is_three_star() {
        logger.info("Applying hotel class filter - 3 Star");

        hotelsListPage.applyThreeStarFilter();
        hotelsListPage.waitForFiltersToApply();

        logger.info("Three-star hotel filter applied successfully");
    }

    @And("Which have free cancelletion facility")
    public void which_have_free_cancellation_facility() {
        logger.info("Applying free cancellation filter");

        hotelsListPage.applyFreeCancellationFilter();
        hotelsListPage.waitForFiltersToApply();

        logger.info("Free cancellation filter applied successfully");
    }

    @And("Free breakfast")
    public void free_breakfast() {
        logger.info("Applying free breakfast filter");

        hotelsListPage.applyFreeBreakfastFilter();
        hotelsListPage.waitForFiltersToApply();

        logger.info("Free breakfast filter applied successfully");
    }

    @And("Is family friendly")
    public void is_family_friendly() {
        logger.info("Applying family-friendly filter");

        hotelsListPage.applyFamilyFriendlyFilter();
        hotelsListPage.waitForFiltersToApply();

        logger.info("Family-friendly filter applied successfully");
    }

    @And("Sorted by rating and recommended")
    public void sorted_by_rating_and_recommended() {
        logger.info("Sorting results by rating and recommended");

        hotelsListPage.sortByRatingAndRecommended();
        hotelsListPage.waitForFiltersToApply();

        logger.info("Results sorted successfully");
    }

    public int getHotelCount() {
        return hotelsListPage.getHotelCount();
    }

    public void verifyAllFiltersApplied() {
        logger.info("Verifying all filters are applied");
        int hotelCount = getHotelCount();
        assert hotelCount > 0 : "No hotels found after applying filters";
        logger.info("Hotels found after filters: {}", hotelCount);
    }
}
