Feature: Categories
  As an user,
  I have be able to add and see categories
  So I can group products

  Scenario: GET - User get all categories
    Given I have GET all categories request
    When I send GET categories request
    Then I receive responses code 200
    And I receive valid response for get all categories

  Scenario: GET - User get all categories with invalid endpoint
    Given I have GET all categories with invalid endpoint request
    When I send GET categories request
    Then I receive responses code 404
    
  @getid
  Scenario: GET - User get categories by id
    Given I have GET categories by id request
    When I send GET categories request
    Then I receive responses code 200
    And I receive valid response for get categories by id

	@add
  Scenario: POST - User create new categories
    Given I have POST new categories request
    When I send POST categories request
    Then I receive responses code 200
    And I receive valid response for new categories
  