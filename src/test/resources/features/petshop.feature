Feature: Pet Shop Product Price Sorting
  Verify that users can successfully sort pet shop products by price in ascending order on the Migros e-commerce platform.
  This test ensures the price sorting functionality works correctly and displays products from lowest to highest price.

  Scenario Outline: Verify product price sorting in Pet Shop category
    Given Open Migros website with "<browser>"
    When Accept cookies and close popup
    And Select Pet Shop from Categories menu
    Then Verify Pet Shop page is opened
    When Sort products by low price
    Then Verify products are sorted by low price

    Examples:
      | browser |
      | chrome  |
      | firefox | 