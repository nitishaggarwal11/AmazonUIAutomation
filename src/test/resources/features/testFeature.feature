# Feature to cover the Amazon automation for Android device
Feature: Amazon Automation for android device 

  #Scenario to cover the flow of adding an item into the cart and purchase it	
  @AmazonTest
  Scenario: Add an item to the cart and purchase it
    Given User launches the Amazon app and Login into it
    When User searches for an item
    And Select any random item from the list
    Then User navigates to the product search screen
    When User click on the Add to Cart button
    Then Item should be added to the cart
    When User clicks on Cart button
    Then User navigates to the Checkout screen
    And Information of product should be same on checkout screen
