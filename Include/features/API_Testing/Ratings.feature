Feature: Rating
  As user, 
  I have be able to create and get ratings
  So I can see ratong of products

	@get
  Scenario: GET - User get rating
    Given I have GET rating request
    When I send GET rating request
    Then I receive response codes success 200
    And I receive valid response for get rating

	@get-inv
  Scenario: GET - User get rating with invalid endpoint
    Given I have GET rating with invalid endpoint request
    When I send GET rating request
    Then I receive response codes not found 404

	@add-invalid
  Scenario: POST - User give rating without token
    Given I have POST rating without token request
    When I send POST rating request
    Then I receive response codes unauthorized 401
    And I receive valid response for give rating without token

	@add
	Scenario: POST - User give rating
    Given I have POST rating request
    When I send POST rating request
    Then I receive response codes success 200
    And I receive valid response for give rating
	    