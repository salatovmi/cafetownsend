@Login
Feature: Login Tests
Description: These tests check Login page functionality

  Scenario Outline: User logs in with different credentials
    Given user is on Login page
    When user enters Username <username>
    And user enters Password <password>
    And click on Login button
    Then login <result>
  Examples:
    | username    | password    | result     |
    | valid       | valid       | successful |
    | valid       | invalid     | failed     |
    | invalid     | valid       | failed     |
    | invalid     | invalid     | failed     |
    | ' or 1=1;-- | ' or 1=1;-- | failed     |
    | empty       | empty       | blocked    |

  Scenario: Password field is hidden with asterisks
    Given user is on Login page
    When user enters Password password
    Then password is hidden with asterisks

  Scenario: After login back button doesn't return on login page
    Given user is logged in
    When user clicks on back button
    Then user is still logged in

  Scenario: After logout user is redirected to Login Page
    Given user is logged in
    When user clicks on logout button
    Then user is redirected to Login page
    And username and password fields are empty