package com.trivago.automation.pages;

import com.trivago.automation.utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * HotelsListPage - Optimized with explicit waits ONLY
 *
 * Strategy:
 * ✅ Use explicit waits before EVERY action
 * ✅ NO static waits (wastes time, not intelligent)
 * ✅ Fast and reliable (waits only as long as needed)
 */
public class HotelsListPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HotelsListPage.class);

    // LOCATORS
    private static final By HOTEL_TYPE_FILTER = By.xpath("//*[@data-title='Hotel']");
    private static final By GUEST_RATING_FILTER_BUTTON = By.xpath("//*[@name='guest_rating_filters']");
    private static final By EXCELLENT_RATING_OPTION = By.xpath("//*[@data-testid='8.5-rating-hotels-filter']");
    private static final By MORE_FILTERS_BUTTON = By.xpath("//*[@name='more_filters']");
    private static final By THREE_STAR_FILTER = By.xpath(
            "//*[@id='__next']/main/div[2]/div[2]/div/div/div[5]/div/div/div/div[2]/section/div[1]/div/button[3]/span[1]"
    );
    private static final By FREE_CANCELLATION_CHECKBOX = By.xpath("//*[@id='filter-checkbox-0']");
    private static final By FREE_BREAKFAST_CHECKBOX = By.xpath("//*[@id='filter-checkbox-1']");
    private static final By FAMILY_FRIENDLY_CHECKBOX = By.xpath("//*[@id='filter-checkbox-8']");
    private static final By SORT_BY_DROPDOWN = By.xpath("/html/body/div[1]/main/div[3]/div[1]/div[1]/section/div/select");
    private static final By RATING_AND_RECOMMENDED_OPTION = By.xpath("//*[@value='6']");
    private static final By TOP_HOTEL_NAME = By.xpath(
            "/html/body/div[1]/main/div[3]/div[1]/div[2]/div/ol[1]/li[1]/section/article/div[2]/div[1]/h3/button"
    );
    private static final By TOP_HOTEL_PRICE = By.xpath("//*[@data-testid='recommended-price']");
    private static final By CHECKOUT_BUTTON = By.xpath("//*[@data-testid='clickout-area']");
    private static final By HOTEL_LIST_ITEMS = By.xpath("//ol[@role='listbox']/li");

    public HotelsListPage() {
        super();
        logger.info("Initialized HotelsListPage");
    }

    /**
     * Apply hotel type filter - Wait for clickability before clicking
     */
    public HotelsListPage applyHotelTypeFilter() {
        logger.info("Applying hotel type filter");
        WaitUtils.waitForElementClickability(HOTEL_TYPE_FILTER);
        click(HOTEL_TYPE_FILTER);
        return this;
    }
    public void waitForFiltersToApply() {
        // ✅ MISSING METHOD - ADD THIS!
        logger.info("Waiting for filters to apply");
        try {
            WaitUtils.waitForElementVisibility(HOTEL_LIST_ITEMS);
        } catch (Exception e) {
            WaitUtils.staticWait(2000);
        }
    }
    /**
     * Apply guest rating filter - Wait for visibility and clickability
     * Explicit waits ensure element is ready before interaction
     */
    public HotelsListPage applyGuestRatingFilter() {
        logger.info("Applying guest rating filter - Excellent");
        WaitUtils.waitForElementVisibility(GUEST_RATING_FILTER_BUTTON);
        hover(GUEST_RATING_FILTER_BUTTON);
        WaitUtils.waitForElementClickability(EXCELLENT_RATING_OPTION);
        click(EXCELLENT_RATING_OPTION);
        return this;
    }

    /**
     * Apply three-star filter - Hover then click with explicit waits
     */
    public HotelsListPage applyThreeStarFilter() {
        logger.info("Applying three-star hotel filter");
        WaitUtils.waitForElementVisibility(MORE_FILTERS_BUTTON);
        hover(MORE_FILTERS_BUTTON);
        WaitUtils.waitForElementClickability(THREE_STAR_FILTER);
        click(THREE_STAR_FILTER);
        return this;
    }

    /**
     * Apply free cancellation filter checkbox
     */
    public HotelsListPage applyFreeCancellationFilter() {
        logger.info("Applying free cancellation filter");
        WaitUtils.waitForElementClickability(FREE_CANCELLATION_CHECKBOX);
        click(FREE_CANCELLATION_CHECKBOX);
        return this;
    }

    /**
     * Apply free breakfast filter checkbox
     */
    public HotelsListPage applyFreeBreakfastFilter() {
        logger.info("Applying free breakfast filter");
        WaitUtils.waitForElementClickability(FREE_BREAKFAST_CHECKBOX);
        click(FREE_BREAKFAST_CHECKBOX);
        return this;
    }

    /**
     * Apply family-friendly filter checkbox
     */
    public HotelsListPage applyFamilyFriendlyFilter() {
        logger.info("Applying family-friendly filter");
        WaitUtils.waitForElementClickability(FAMILY_FRIENDLY_CHECKBOX);
        click(FAMILY_FRIENDLY_CHECKBOX);
        return this;
    }

    /**
     * Sort by rating and recommended - Wait for visibility and clickability
     */
    public HotelsListPage sortByRatingAndRecommended() {
        logger.info("Sorting by rating and recommended");
        WaitUtils.waitForElementVisibility(SORT_BY_DROPDOWN);
        hover(SORT_BY_DROPDOWN);
        WaitUtils.waitForElementClickability(RATING_AND_RECOMMENDED_OPTION);
        click(RATING_AND_RECOMMENDED_OPTION);
        return this;
    }

    /**
     * Apply all filters at once - Fluent API with only explicit waits
     */
    public HotelsListPage applyAllFilters() {
        logger.info("Applying all filters");
        return applyHotelTypeFilter()
                .applyGuestRatingFilter()
                .applyThreeStarFilter()
                .applyFreeCancellationFilter()
                .applyFreeBreakfastFilter()
                .applyFamilyFriendlyFilter()
                .sortByRatingAndRecommended();
    }

    /**
     * Get top hotel name
     */
    public String getTopHotelName() {
        String hotelName = getText(TOP_HOTEL_NAME);
        logger.info("Top hotel name: {}", hotelName);
        return hotelName;
    }

    /**
     * Get top hotel price
     */
    public String getTopHotelPrice() {
        String price = getText(TOP_HOTEL_PRICE);
        logger.info("Top hotel price: {}", price);
        return price;
    }

    /**
     * Display top hotel details
     */
    public void displayTopHotelDetails() {
        logger.info("=".repeat(50));
        logger.info("Top Hotel Details:");
        logger.info("Hotel Name: {}", getTopHotelName());
        logger.info("Hotel Price: {}", getTopHotelPrice());
        logger.info("=".repeat(50));
    }

    /**
     * Click checkout button - Wait for visibility and clickability
     */
    public void clickTopHotelCheckout() {
        logger.info("Clicking checkout for top hotel");
        WaitUtils.waitForElementVisibility(CHECKOUT_BUTTON);
        hover(CHECKOUT_BUTTON);
        WaitUtils.waitForElementClickability(CHECKOUT_BUTTON);
        click(CHECKOUT_BUTTON);
    }

    /**
     * Get hotel count - Fresh list every time (no stale references)
     */
    public int getHotelCount() {
        List<WebElement> hotelListItems = findElements(HOTEL_LIST_ITEMS);
        int count = hotelListItems.size();
        logger.info("Total hotels found: {}", count);
        return count;
    }

    /**
     * Verify hotels list page is loaded
     */
    public boolean isHotelsListPageLoaded() {
        logger.info("Verifying hotels list page is loaded");
        try {
            return WaitUtils.waitForElementVisibility(HOTEL_LIST_ITEMS, 5) != null;
        } catch (Exception e) {
            logger.error("Hotels list page not loaded", e);
            return false;
        }
    }
}