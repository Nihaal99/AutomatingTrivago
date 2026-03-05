Feature: Search and Filter Hotels on Trivago
  As a user
  I want to search for hotels with specific criteria
  And apply filters to find the best hotel matching my preferences
  So that I can book a hotel that meets all my requirements

  Background:
    Given User is on trivago homepage

  @Smoke @Search
  Scenario: Complete hotel search with all required details
    When User enters destination
    And Enters checkIn checkOut details
#    And select rooms and guest details
    And Click on search button

  @Regression @Filter
  Scenario: Apply comprehensive filters on hotel's list page
    Given User enters destination
    And Enters checkIn checkOut details
    And select rooms and guest details
    And Click on search button
    When Property type is hotel
    And Guest rating is excellent
    And Hotel class is three star
    And Which have free cancelletion facility
    And Free breakfast
    And Is family friendly
    And Sorted by rating and recommended

  @Smoke @Selection
  Scenario: Select top hotel from filtered results and proceed to checkout
    Given User enters destination
    And Enters checkIn checkOut details
    And select rooms and guest details
    And Click on search button
    When Property type is hotel
    And Guest rating is excellent
    And Hotel class is three star
    And Which have free cancelletion facility
    And Free breakfast
    And Is family friendly
    And Sorted by rating and recommended
    Then The name of hotel and cost displayed
    And User is redirected to hotel's website for checkout
    And Quit Browser

