Feature: Comment
  As user, 
  I have be able to create and get comments
  So I can see ratong of products

	@get
  Scenario: GET - User get comment
    Given I have GET comment request
    When I send GET comment request
    Then I receive success response codes 200
    And I receive valid response for get comment

	@get-inv
  Scenario: GET - User get comment with invalid endpoint
    Given I have GET comment with invalid endpoint request
    When I send GET comment request
    Then I receive not found response codes 404

	@add-invalid
  Scenario: POST - User give comment without token
    Given I have POST comment without token request
    When I send POST comment request
    Then I receive unauthorized response codes 401
    And I receive valid response for give comment without token

	@add
	Scenario: POST - User give comment
    Given I have POST comment request
    When I send POST comment request
    Then I receive success response codes 200
    And I receive valid response for give comment
	    