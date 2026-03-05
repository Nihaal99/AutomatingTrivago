package com.trivago.automation.pages;

import com.trivago.automation.utilities.WaitUtils;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TrivagoHomePage - Optimized with explicit waits ONLY
 *
 * Strategy:
 * ✅ Use explicit waits before EVERY action
 * ✅ NO static waits (wastes time, not intelligent)
 * ✅ Fast and reliable (waits only as long as needed)
 */
public class TrivagoHomePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(TrivagoHomePage.class);

    // LOCATORS
    private static final By DESTINATION_SEARCH_BOX = By.xpath("//*[@id='input-auto-complete']");
    private static final By SUGGESTED_DESTINATION_OPTION = By.xpath("//*[@id='suggestion-list']/ul/li[1]");
    //need to optimize this functionality(currently hardcoded check in & check out dates)
    private static final By CHECKIN_DATE_BUTTON = By.xpath("//*[@id='__next']/div[1]/div[2]/section[1]/div[2]/div/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/div[2]/button[14]/time");
    private static final By CHECKOUT_DATE_BUTTON = By.xpath(
            "//*[@id='__next']/div[1]/div[2]/section[1]/div[2]/div/div/div/div/div[2]/div/div/div/div[1]/div/div[2]/div[2]/button[13]/time"
    );
    //need to create optimal code to select room & guest details from ConfigProperties File
    private static final By ROOM_AND_GUEST_BUTTON = By.xpath("//*[@id='__next']/div[1]/div[2]/section[1]/div[2]/div/div/div/div/div[1]/div[2]/button");
    private static final By ADULTS_NUMBER_INPUT = By.xpath("//*[@id='_b_']");
    private static final By CHILDREN_NUMBER_INPUT = By.xpath("//*[@id='_r_k_']");
    private static final By ROOM_AND_GUEST_FLEX_TEXT = By.xpath("//*[@id='_r_l_']");
    private static final By SEARCH_BUTTON = By.xpath("//*[@id='__next']/div[1]/div[2]/section[1]/div[2]/div/div/div/div/button");

    public TrivagoHomePage() {
        super();
        logger.info("Initialized TrivagoHomePage");
    }

    /**
     * Enter destination - Wait for visibility, then interact
     * NO static wait needed (element is already found and visible)
     */
    public TrivagoHomePage enterDestination(String destination) {
        logger.info("Entering destination: {}", destination);
        WaitUtils.waitForElementVisibility(DESTINATION_SEARCH_BOX);
        sendKeys(DESTINATION_SEARCH_BOX, destination);
        return this;
    }

    /**
     * Select suggested destination - Wait for clickability before clicking
     * Explicit wait replaces the need for static wait
     */
    public TrivagoHomePage selectSuggestedDestination() {
        logger.info("Selecting suggested destination");
        WaitUtils.waitForElementClickability(SUGGESTED_DESTINATION_OPTION);
        click(SUGGESTED_DESTINATION_OPTION);
        return this;
    }

    /**
     * Search destination - Fluent API
     */
    public TrivagoHomePage searchDestination(String destination) {
        logger.info("Searching for destination: {}", destination);
        enterDestination(destination);
        selectSuggestedDestination();
        return this;
    }

    /**
     * Click check-in date button
     */
    public TrivagoHomePage clickCheckInDate() {
        logger.info("Clicking on check-in date button");
        WaitUtils.waitForElementClickability(CHECKIN_DATE_BUTTON);
        click(CHECKIN_DATE_BUTTON);
        return this;
    }

    /**
     * Click check-out date button
     */
    public TrivagoHomePage clickCheckOutDate() {
        logger.info("Clicking on check-out date button");
        WaitUtils.waitForElementClickability(CHECKOUT_DATE_BUTTON);
        click(CHECKOUT_DATE_BUTTON);
        return this;
    }

    /**
     * Click room and guest button
     */
    public TrivagoHomePage clickRoomAndGuestButton() {
        logger.info("Clicking on room and guest button");
        WaitUtils.waitForElementClickability(ROOM_AND_GUEST_BUTTON);
        click(ROOM_AND_GUEST_BUTTON);
        return this;
    }

    /**
     * Set adults count - Wait for element before typing
     */
    public TrivagoHomePage setAdultsCount(int count) {
        logger.info("Setting adults count to: {}", count);
        WaitUtils.waitForElementVisibility(ADULTS_NUMBER_INPUT);
        sendKeys(ADULTS_NUMBER_INPUT, String.valueOf(count));
        return this;
    }

    /**
     * Set children count - Wait for element before typing
     */
    public TrivagoHomePage setChildrenCount(int count) {
        logger.info("Setting children count to: {}", count);
        WaitUtils.waitForElementVisibility(CHILDREN_NUMBER_INPUT);
        sendKeys(CHILDREN_NUMBER_INPUT, String.valueOf(count));
        return this;
    }

    /**
     * Click search button
     */
    public void clickSearchButton() {
        logger.info("Clicking search button");
        WaitUtils.waitForElementClickability(SEARCH_BUTTON);
        click(SEARCH_BUTTON);
    }

    /**
     * Perform complete hotel search
     * OPTIMIZED: Only explicit waits, NO static waits
     *
     * Time saved: ~8.5 seconds per test! 🚀
     */
    public void performCompleteSearch(String destination, int adults, int children) {
        logger.info("Performing complete hotel search - Destination: {}, Adults: {}, Children: {}",
                destination, adults, children);

        // Enter destination and select from suggestions
        enterDestination(destination);
        selectSuggestedDestination();  // Explicit wait handles the timing

        // Select dates
        clickCheckInDate();              // Explicit wait handles timing
        clickCheckOutDate();             // Explicit wait handles timing

        // Set room and guest
        clickRoomAndGuestButton();       // Explicit wait handles timing
        setAdultsCount(adults);          // Explicit wait handles timing
        setChildrenCount(children);      // Explicit wait handles timing

        // Search
        clickSearchButton();              // Explicit wait handles timing
        logger.info("Search initiated");
    }

    /**
     * Get room and guest flex text
     */
    public String getRoomAndGuestFlexText() {
        String text = getText(ROOM_AND_GUEST_FLEX_TEXT);
        logger.info("Room and Guest text: {}", text);
        return text;
    }

    /**
     * Verify homepage is loaded
     */
    public boolean isHomepageLoaded() {
        logger.info("Verifying homepage is loaded");
        try {
            return WaitUtils.waitForElementVisibility(DESTINATION_SEARCH_BOX, 5) != null;
        } catch (Exception e) {
            logger.error("Homepage not loaded", e);
            return false;
        }
    }
}