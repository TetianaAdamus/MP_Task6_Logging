Feature: Desktop Checkout for Guest User

  Scenario: Searching a product as a guest
    Given I open Book Depository
    And I manually accept cookie
    And I am on the "Home page"
    When I search for "Thinking in Java"
    Then I am on the "Search page"
    And Search results contain the following products
      | Thinking in Java       |
      | Thinking Java Part I   |
      | Core Java Professional |
    When I apply the following search filters
      | Price range  | 30 € +         |
      | Availability | In Stock (5)   |
      | Language     | English (17)   |
      | Format       | Paperback (22) |
    Then Search results contain only the following products
      | Thinking in Java                                                  |
      | Think Java                                                        |
      | Thinking Recursively - A 20th Anniversary Edition with Java (WSE) |
      | Think Data Structures                                             |

  Scenario: Adding the product to the cart
    Given I open Thinking in Java product
    And I manually accept cookie
    Then I am on the "Thinking in Java" product page

  Scenario: Bestseller cathegory opening
    Given I open Book Depository
    And I manually accept cookie
    When I open category "Bestsellers"
    Then I am on the "Bestsellers Page"