Feature: Search Hotel
  Background: When user clicks on trivago homepage
  Scenario: Fill all the required details on trivago homepage

    Given User is on trivago homepage
    When User enters destination
    And Enters checkIn checkOut details
    And select rooms and guest details
    And Click on search button

    Scenario: Apply all filters on hotel's list page
      When Property type is hotel
      And Guest rating is excellent
      And Hotel class is three star
      And Which have free cancelletion facility
      And Free breakfast
      And Is family friendly
      And Sorted by rating and recommended

Scenario: Select the top hotel in the list
  When The name of hotel and cost displayed
  And User is redirected to hotel's website for checkout
  And Quit Browser




