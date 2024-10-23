Feature: Wishlist Controller

  Scenario: Add product to wishlist
    Given a customer with id "customer1"
    When the customer adds a product with id "product1" and name "Product 1" to the wishlist
    Then the response status should be 201 CREATED

  Scenario: Remove product from wishlist
    Given a customer with id "customer1" and a product with id "product1" in the wishlist
    When the customer removes the product with id "product1" from the wishlist
    Then the response status should be 204 NO CONTENT

  Scenario: Check if product is in wishlist
    Given a customer with id "customer1" and a product with id "product1" in the wishlist
    When the customer checks if the product with id "product1" is in the wishlist
    Then the response status should be 200 OK

  Scenario: List products from wishlist
    Given a customer with id "customer1" and a product with id "product1" in the wishlist
    When the customer lists all products in the wishlist
    Then the response status should be 200 OK
    And the response should contain the product with id "product1" and name "Product 1"