Feature: Argos Product Purchase Flow
  

  Scenario: Search and add product to Argos basket
    Given I navigate to Argos website
    When I search for "lenovo ideapad"
    Then I should see search results for "lenovo ideapad"
    When I add the first product to the basket
    Then the product should be visible in the basket
    When I increase the product quantity to 2
    Then the basket subtotal should be updated correctly