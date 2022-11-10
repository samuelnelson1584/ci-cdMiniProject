Feature: Authentication
  As user, I have be able to authenticate user 
  so I can acess other feature

  @register-valid
  Scenario: POST - User create new account
    Given I have POST user create new account request
    When I send POST auth request
    Then I receive response code 200
    And I receive valid response for user create new account

  @register-invalid
  Scenario: POST - User register with blank password
    Given I have POST user register with blank password
    When I send POST auth request
    Then I receive response code 400
    And I receive valid response for user register with blank password

  @login-valid
  Scenario: POST - User login existing account
    Given I have POST user login existing account
    When I send POST auth request
    Then I receive response code 200
    And I receive valid response for user login existing account

  @login-invalid
  Scenario: POST - User login with unregistered email
    Given I have POST user login with unregistered email
    When I send POST auth request
    Then I receive response code 400
    And I receive valid response for user login with unregistered email

  Scenario: GET - User get user auth information
    Given I have GET user auth info request
    When I send GET auth request
    Then I receive response code 200
    And I receive valid response for user auth info

  Scenario: GET - User get user auth information without token
    Given I have GET user auth info request without token
    When I send GET auth request
    Then I receive response code 401
    And I receive error response for user auth info
