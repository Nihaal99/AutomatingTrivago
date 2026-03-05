package com.trivago.automation.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * APIConfig - Loads all API configuration from application.properties
 * All hardcoded values are removed - everything comes from properties file
 */
public class APIConfig {

    private static final Logger logger = LoggerFactory.getLogger(APIConfig.class);
    private static Properties properties = new Properties();

    static {
        try (InputStream input = APIConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                logger.error("application.properties file not found!");
                logger.error("Please create src/test/resources/application.properties");
                throw new RuntimeException("application.properties not found");
            }

            properties.load(input);
            logger.info("✓ API Configuration loaded from application.properties");
            logConfig();

        } catch (IOException e) {
            logger.error("Failed to load application.properties: {}", e.getMessage());
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    // ═════════════════════════════════════════════════════════════════════════
    // API BASE CONFIGURATION
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * Get API base URL from properties
     * Property: api.baseUrl
     */
    public static String getApiBaseUrl() {
        String url = properties.getProperty("api.baseUrl");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("api.baseUrl not configured in application.properties");
        }
        return url;
    }

    /**
     * Get API version from properties
     * Property: api.version
     */
    public static String getApiVersion() {
        String version = properties.getProperty("api.version", "v1");
        return version;
    }

    /**
     * Get API timeout from properties (in milliseconds)
     * Property: api.timeout
     */
    public static int getApiTimeout() {
        try {
            String timeout = properties.getProperty("api.timeout", "10000");
            return Integer.parseInt(timeout);
        } catch (NumberFormatException e) {
            logger.warn("Invalid api.timeout value, using default 10000ms");
            return 10000;
        }
    }

    /**
     * Get connection timeout from properties (in milliseconds)
     * Property: api.connectionTimeout
     */
    public static int getConnectionTimeout() {
        try {
            String timeout = properties.getProperty("api.connectionTimeout", "5000");
            return Integer.parseInt(timeout);
        } catch (NumberFormatException e) {
            logger.warn("Invalid api.connectionTimeout value, using default 5000ms");
            return 5000;
        }
    }

    // ═════════════════════════════════════════════════════════════════════════
    // API ENDPOINTS
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * Get search endpoint from properties
     * Property: api.endpoint.search
     */
    public static String getSearchEndpoint() {
        return properties.getProperty("api.endpoint.search", "/hotels/search");
    }

    /**
     * Get hotels endpoint from properties
     * Property: api.endpoint.hotels
     */
    public static String getHotelsEndpoint() {
        return properties.getProperty("api.endpoint.hotels", "/hotels");
    }

    /**
     * Get filter endpoint from properties
     * Property: api.endpoint.filter
     */
    public static String getFilterEndpoint() {
        return properties.getProperty("api.endpoint.filter", "/hotels/filter");
    }

    /**
     * Get sort endpoint from properties
     * Property: api.endpoint.sort
     */
    public static String getSortEndpoint() {
        return properties.getProperty("api.endpoint.sort", "/hotels/sort");
    }

    /**
     * Get bookings endpoint from properties
     * Property: api.endpoint.bookings
     */
    public static String getBookingsEndpoint() {
        return properties.getProperty("api.endpoint.bookings", "/bookings");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // AUTHENTICATION
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * Get API key from properties
     * Property: api.key
     */
    public static String getApiKey() {
        return properties.getProperty("api.key", "");
    }

    /**
     * Get API token from properties
     * Property: api.token
     */
    public static String getApiToken() {
        return properties.getProperty("api.token", "");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // TEST DATA
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * Get test destination from properties
     * Property: test.destination
     */
    public static String getTestDestination() {
        return properties.getProperty("test.destination", "Paris");
    }

    /**
     * Get test check-in date from properties
     * Property: test.checkIn
     */
    public static String getTestCheckIn() {
        return properties.getProperty("test.checkIn", "2024-03-10");
    }

    /**
     * Get test check-out date from properties
     * Property: test.checkOut
     */
    public static String getTestCheckOut() {
        return properties.getProperty("test.checkOut", "2024-03-15");
    }

    /**
     * Get test adults count from properties
     * Property: test.adults
     */
    public static int getTestAdults() {
        try {
            String adults = properties.getProperty("test.adults", "2");
            return Integer.parseInt(adults);
        } catch (NumberFormatException e) {
            logger.warn("Invalid test.adults value, using default 2");
            return 2;
        }
    }

    /**
     * Get test children count from properties
     * Property: test.children
     */
    public static int getTestChildren() {
        try {
            String children = properties.getProperty("test.children", "1");
            return Integer.parseInt(children);
        } catch (NumberFormatException e) {
            logger.warn("Invalid test.children value, using default 1");
            return 1;
        }
    }

    // ═════════════════════════════════════════════════════════════════════════
    // UTILITY METHODS
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * Log all configuration for debugging
     */
    public static void logConfig() {
        logger.info("════════════════════════════════════════════════════════");
        logger.info("API CONFIGURATION (from application.properties)");
        logger.info("════════════════════════════════════════════════════════");
        logger.info("Base URL: {}", getApiBaseUrl());
        logger.info("API Version: {}", getApiVersion());
        logger.info("Timeout: {} ms", getApiTimeout());
        logger.info("Connection Timeout: {} ms", getConnectionTimeout());
        logger.info("─────────────────────────────────────────────────────");
        logger.info("ENDPOINTS");
        logger.info("─────────────────────────────────────────────────────");
        logger.info("Search: {}", getSearchEndpoint());
        logger.info("Hotels: {}", getHotelsEndpoint());
        logger.info("Filter: {}", getFilterEndpoint());
        logger.info("Sort: {}", getSortEndpoint());
        logger.info("Bookings: {}", getBookingsEndpoint());
        logger.info("─────────────────────────────────────────────────────");
        logger.info("TEST DATA");
        logger.info("─────────────────────────────────────────────────────");
        logger.info("Destination: {}", getTestDestination());
        logger.info("Check-in: {}", getTestCheckIn());
        logger.info("Check-out: {}", getTestCheckOut());
        logger.info("Adults: {}", getTestAdults());
        logger.info("Children: {}", getTestChildren());
        logger.info("════════════════════════════════════════════════════════");
    }

    /**
     * Get all properties for debugging
     */
    public static Properties getAllProperties() {
        return properties;
    }
}
