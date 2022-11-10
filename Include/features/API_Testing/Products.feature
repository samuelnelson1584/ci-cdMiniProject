Feature: Products
  As a user 
  I need be able to add and see product data
  So I can know what product to buy

  @add
  Scenario: POST - User create new product
    Given I have POST new product request
    When I send POST new product request
    Then I receive response codes 200
    And I receive valid response for new product

  Scenario: GET - User get product by ID
    Given I have GET product by ID request
    When I send GET product request
    Then I receive response codes 200
    And I receive valid response for get product by ID

  @getbyinvalid
  Scenario: GET - User get product by Invalid ID
    Given I have GET product by invalid ID request
    When I send GET product request
    Then I receive response codes 400
    And I receive error response for get product by invalid ID

  @getall
  Scenario: GET - User get all product
    Given I have GET all product request
    When I send GET product request
    Then I receive response codes 200
    And I receive valid response for get all product

  @getall-invalid
  Scenario: GET - User get all product with invalid endpoint
    Given I have GET all product with invalid endpoint request
    When I send GET product request
    Then I receive response codes 404

  @delete
  Scenario: DEL - User delete product
    Given I have DELETE product request
    When I send DELETE product request
    Then I receive response codes 200
    And I receive valid response for delete product

