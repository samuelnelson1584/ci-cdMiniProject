Feature: Orders
  As user, 
  I have be able to create and get orders
  So I can purchase products

  Scenario: GET - User get order history
    Given I have GET order history request
    When I send GET order request
    Then I receive response code success 200
    And I receive valid response for get order history

	@getall-invalid
  Scenario: GET - User get order history without token
    Given I have GET order history without token request
    When I send GET order request
    Then I receive response code unauthorized 401
    And I receive valid response for get order history without token

	@getbyid
	Scenario: GET - User get order by id
    Given I have GET order by id request
    When I send GET order request
    Then I receive response code success 200
    And I receive valid response for get order by id
	    
	@add
		Scenario: POST - User add order
    Given I have POST order request
    When I send POST order request
    Then I receive response code success 200
    And I receive valid response for add order
	  
	
	@add-invquant
		Scenario: POST - User add order with invalid quantity
    Given I have POST order with invalid quantity request
    When I send POST order request
    Then I receive response code bad request 400
    And I receive valid response for add order with invalid quantity
	  
	
	    