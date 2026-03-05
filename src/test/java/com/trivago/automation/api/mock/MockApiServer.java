package com.trivago.automation.api.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Mock API Server - Simulates Trivago API for testing
 * Runs on localhost:8080 without authentication
 *
 * NO REAL API NEEDED - Perfect for testing without credentials!
 */
public class MockApiServer {

    private static final Logger logger = LoggerFactory.getLogger(MockApiServer.class);
    private static WireMockServer wireMockServer;
    private static final int PORT = 8080;

    /**
     * Start mock API server on port 8080
     * This creates a fake API that responds instantly
     */
    public static void startMockServer() {
        wireMockServer = new WireMockServer(
                WireMockConfiguration.wireMockConfig()
                        .port(PORT)
        );

        wireMockServer.start();
        logger.info("════════════════════════════════════════════════════════");
        logger.info("✓ MOCK API SERVER STARTED");
        logger.info("════════════════════════════════════════════════════════");
        logger.info("Base URL: http://localhost:{}", PORT);
        logger.info("════════════════════════════════════════════════════════");

        // Setup all mock endpoints
        setupMockEndpoints();
    }

    /**
     * Stop mock API server
     */
    public static void stopMockServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
            logger.info("════════════════════════════════════════════════════════");
            logger.info("✓ MOCK API SERVER STOPPED");
            logger.info("════════════════════════════════════════════════════════");
        }
    }

    /**
     * Configure all mock API endpoints and their responses
     * This is what makes the tests work!
     */
    private static void setupMockEndpoints() {

        logger.info("────────────────────────────────────────────────────────");
        logger.info("SETTING UP MOCK ENDPOINTS");
        logger.info("────────────────────────────────────────────────────────");

        // ═══════════════════════════════════════════════════════════════════
        // ENDPOINT 1: GET /v1/hotels/search
        // ═══════════════════════════════════════════════════════════════════
        wireMockServer.stubFor(
                get(urlPathEqualTo("/v1/hotels/search"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\n" +
                                        "  \"status\": \"success\",\n" +
                                        "  \"hotels\": [\n" +
                                        "    {\n" +
                                        "      \"id\": 1,\n" +
                                        "      \"name\": \"Hilton Paris\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 150,\n" +
                                        "      \"rating\": 4.5,\n" +
                                        "      \"amenities\": [\"WiFi\", \"Pool\", \"Gym\"]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"id\": 2,\n" +
                                        "      \"name\": \"Hotel le Marais\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 120,\n" +
                                        "      \"rating\": 4.2,\n" +
                                        "      \"amenities\": [\"WiFi\", \"Restaurant\"]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"id\": 3,\n" +
                                        "      \"name\": \"Eiffel Tower Hotel\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 200,\n" +
                                        "      \"rating\": 4.8,\n" +
                                        "      \"amenities\": [\"WiFi\", \"Pool\", \"Gym\", \"Spa\"]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"id\": 4,\n" +
                                        "      \"name\": \"Hotel de la Paix\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 175,\n" +
                                        "      \"rating\": 4.3,\n" +
                                        "      \"amenities\": [\"WiFi\", \"Gym\"]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"id\": 5,\n" +
                                        "      \"name\": \"Luxe Paris Suites\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 250,\n" +
                                        "      \"rating\": 4.9,\n" +
                                        "      \"amenities\": [\"WiFi\", \"Pool\", \"Gym\", \"Spa\", \"Restaurant\"]\n" +
                                        "    }\n" +
                                        "  ],\n" +
                                        "  \"totalResults\": 5\n" +
                                        "}\n")
                                .withFixedDelay(500)  // Simulate 500ms network delay
                        )
        );
        logger.info("✓ Mocked: GET /v1/hotels/search → 200 OK (5 hotels)");

        // ═══════════════════════════════════════════════════════════════════
        // ENDPOINT 2: GET /v1/hotels/filter
        // ═══════════════════════════════════════════════════════════════════
        wireMockServer.stubFor(
                get(urlPathEqualTo("/v1/hotels/filter"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\n" +
                                        "  \"status\": \"success\",\n" +
                                        "  \"filters\": {\n" +
                                        "    \"rating\": 4.5,\n" +
                                        "    \"priceMin\": 100,\n" +
                                        "    \"priceMax\": 200\n" +
                                        "  },\n" +
                                        "  \"hotels\": [\n" +
                                        "    {\n" +
                                        "      \"id\": 1,\n" +
                                        "      \"name\": \"Hilton Paris\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 150,\n" +
                                        "      \"rating\": 4.5,\n" +
                                        "      \"amenities\": [\"WiFi\", \"Pool\", \"Gym\"]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"id\": 3,\n" +
                                        "      \"name\": \"Eiffel Tower Hotel\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 200,\n" +
                                        "      \"rating\": 4.8,\n" +
                                        "      \"amenities\": [\"WiFi\", \"Pool\", \"Gym\", \"Spa\"]\n" +
                                        "    }\n" +
                                        "  ],\n" +
                                        "  \"totalResults\": 2\n" +
                                        "}\n")
                                .withFixedDelay(400)
                        )
        );
        logger.info("✓ Mocked: GET /v1/hotels/filter → 200 OK (filtered results)");

        // ═══════════════════════════════════════════════════════════════════
        // ENDPOINT 3: POST /v1/bookings
        // ═══════════════════════════════════════════════════════════════════
        wireMockServer.stubFor(
                post(urlPathEqualTo("/v1/bookings"))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\n" +
                                        "  \"status\": \"success\",\n" +
                                        "  \"message\": \"Booking confirmed\",\n" +
                                        "  \"bookingId\": \"BK12345678\",\n" +
                                        "  \"hotelId\": 1,\n" +
                                        "  \"destination\": \"Paris\",\n" +
                                        "  \"checkIn\": \"2024-03-10\",\n" +
                                        "  \"checkOut\": \"2024-03-15\",\n" +
                                        "  \"nights\": 5,\n" +
                                        "  \"pricePerNight\": 150,\n" +
                                        "  \"totalPrice\": 750,\n" +
                                        "  \"currency\": \"EUR\",\n" +
                                        "  \"bookingStatus\": \"confirmed\",\n" +
                                        "  \"confirmationEmail\": \"booking@trivago.com\"\n" +
                                        "}\n")
                                .withFixedDelay(600)
                        )
        );
        logger.info("✓ Mocked: POST /v1/bookings → 201 Created (booking confirmed)");

        // ═══════════════════════════════════════════════════════════════════
        // ENDPOINT 4: GET /v1/hotels/sort
        // ═══════════════════════════════════════════════════════════════════
        wireMockServer.stubFor(
                get(urlPathEqualTo("/v1/hotels/sort"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\n" +
                                        "  \"status\": \"success\",\n" +
                                        "  \"sortBy\": \"rating\",\n" +
                                        "  \"hotels\": [\n" +
                                        "    {\n" +
                                        "      \"id\": 5,\n" +
                                        "      \"name\": \"Luxe Paris Suites\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 250,\n" +
                                        "      \"rating\": 4.9\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"id\": 3,\n" +
                                        "      \"name\": \"Eiffel Tower Hotel\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 200,\n" +
                                        "      \"rating\": 4.8\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"id\": 1,\n" +
                                        "      \"name\": \"Hilton Paris\",\n" +
                                        "      \"destination\": \"Paris\",\n" +
                                        "      \"price\": 150,\n" +
                                        "      \"rating\": 4.5\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}\n")
                                .withFixedDelay(450)
                        )
        );
        logger.info("✓ Mocked: GET /v1/hotels/sort → 200 OK (sorted by rating)");

        // ═══════════════════════════════════════════════════════════════════
        // ENDPOINT 5: GET /v1/hotels/{id} (dynamic endpoint)
        // ═══════════════════════════════════════════════════════════════════
        wireMockServer.stubFor(
                get(urlMatching("/v1/hotels/[0-9]+"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\n" +
                                        "  \"status\": \"success\",\n" +
                                        "  \"hotel\": {\n" +
                                        "    \"id\": 1,\n" +
                                        "    \"name\": \"Hilton Paris\",\n" +
                                        "    \"destination\": \"Paris\",\n" +
                                        "    \"description\": \"Luxury 5-star hotel in the heart of Paris\",\n" +
                                        "    \"price\": 150,\n" +
                                        "    \"currency\": \"EUR\",\n" +
                                        "    \"rating\": 4.5,\n" +
                                        "    \"reviews\": 1250,\n" +
                                        "    \"amenities\": [\"WiFi\", \"Pool\", \"Gym\", \"Restaurant\", \"Spa\"],\n" +
                                        "    \"address\": \"123 Champs-Élysées, Paris, France\",\n" +
                                        "    \"phone\": \"+33-1-2345-6789\",\n" +
                                        "    \"email\": \"hilton@paris.fr\",\n" +
                                        "    \"availability\": true\n" +
                                        "  }\n" +
                                        "}\n")
                                .withFixedDelay(350)
                        )
        );
        logger.info("✓ Mocked: GET /v1/hotels/{id} → 200 OK (hotel details)");

        // ═══════════════════════════════════════════════════════════════════
        // ERROR RESPONSES (for negative testing)
        // ═════════════════════════════════════════════════════════════════════

        // Mock invalid request (400 Bad Request)
        wireMockServer.stubFor(
                get(urlPathEqualTo("/v1/hotels/search"))
                        .withQueryParam("destination", absent())  // Missing destination
                        .willReturn(aResponse()
                                .withStatus(400)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\n" +
                                        "  \"status\": \"error\",\n" +
                                        "  \"message\": \"Bad Request: destination parameter is required\",\n" +
                                        "  \"code\": \"INVALID_REQUEST\"\n" +
                                        "}\n")
                        )
        );
        // Stub for Search Failures (Past dates or Invalid formats)
        wireMockServer.stubFor(
                get(urlPathEqualTo("/v1/hotels/search"))
                        .withQueryParam("checkIn", matching("(2020.*|invalid-date)")) // Matches 2020 or 'invalid-date'
                        .willReturn(aResponse()
                                .withStatus(400)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"status\":\"error\",\"message\":\"Invalid date provided\"}"))
        );
        // Stub for Booking Failures
        wireMockServer.stubFor(
                post(urlPathEqualTo("/v1/bookings"))
                        .withRequestBody(matchingJsonPath("$.[?(@.checkOut < @.checkIn)]")) // Logical check
                        .willReturn(aResponse()
                                .withStatus(400)
                                .withBody("{\"status\":\"error\",\"message\":\"Check-out cannot be before Check-in\"}"))
        );

// Stub for Missing Fields (If 'hotelId' is missing)
        wireMockServer.stubFor(
                post(urlPathEqualTo("/v1/bookings"))
                        .withRequestBody(notMatching(".*hotelId.*"))
                        .willReturn(aResponse()
                                .withStatus(400)
                                .withBody("{\"status\":\"error\",\"message\":\"hotelId is required\"}"))
        );
        logger.info("✓ Mocked: GET /v1/hotels/search (missing params) → 400 Bad Request");

        logger.info("────────────────────────────────────────────────────────");
        logger.info("✓ ALL MOCK ENDPOINTS CONFIGURED");
        logger.info("────────────────────────────────────────────────────────");
    }

    /**
     * Reset all recorded interactions (for reusing mock server)
     */
    public static void resetMocks() {
        if (wireMockServer != null) {
            wireMockServer.resetAll();
            logger.info("✓ All mock interactions reset");
        }
    }
}
