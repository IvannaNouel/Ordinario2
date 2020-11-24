Feature: Test User login https://example.testproject.io/web/

  Scenario: User logins successfully with credentials
    Given: Browser is open
    And user is in login page
    When user enters <username> and <password>
    And user clicks login button
    Then website shows main page

	Examples:
	|username||password|
	|Meli||12345|
	|Jorge||12345|