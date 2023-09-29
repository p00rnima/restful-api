@restful-api
Feature: Restful API object test scenarios
  As a user
  I want to create objects
  So that I can retrieve and verify the details

  Scenario Outline: Ability to create an item
    When user creates an item with below details
      | NAME   | YEAR   | PRICE   | CPU_MODEL   | HARD_DISK_SIZE   |
      | <NAME> | <YEAR> | <PRICE> | <CPU_MODEL> | <HARD_DISK_SIZE> |
    Then user verifies the item created response
    Examples:
      | NAME                 | YEAR | PRICE   | CPU_MODEL     | HARD_DISK_SIZE |
      | Apple MacBook Pro 16 | 2019 | 1849.99 | Intel Core i9 | 1 TB           |

  Scenario Outline: Ability to return an item and verify the response
    When user retrieves one item by <ID>
    Then user verifies the individual item details
      | ID   | NAME   | YEAR   | PRICE   | CPU_MODEL   | HARD_DISK_SIZE   |
      | <ID> | <NAME> | <YEAR> | <PRICE> | <CPU_MODEL> | <HARD_DISK_SIZE> |
    Examples:
      | ID | NAME                 | YEAR | PRICE   | CPU_MODEL     | HARD_DISK_SIZE |
      | 7  | Apple MacBook Pro 16 | 2019 | 1849.99 | Intel Core i9 | 1 TB           |

  Scenario: Ability to list multiple objects and verify the response
    When user get all the objects list
    Then user verifies the list of objects response

  Scenario Outline: Ability to create and delete an item and verify the response
    Given user creates an item with below details
      | NAME   | YEAR   | PRICE   | CPU_MODEL   | HARD_DISK_SIZE   |
      | <NAME> | <YEAR> | <PRICE> | <CPU_MODEL> | <HARD_DISK_SIZE> |
    Then user verifies the item created response
    When user deletes the created item
    Then user verifies the created item deleted response
    Examples:
      | NAME                 | YEAR | PRICE   | CPU_MODEL     | HARD_DISK_SIZE |
      | Apple MacBook Pro 16 | 2019 | 1849.99 | Intel Core i9 | 1 TB           |