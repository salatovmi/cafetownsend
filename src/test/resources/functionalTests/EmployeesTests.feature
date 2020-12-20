@Employees
Feature: Employees Tests
  Description: These tests check Employees page functionality

  Scenario Outline: Check that all fields are required for creation
    Given user is logged in
    When user clicks on create button
    And user fills in all fields
    And user leaves <field> empty
    And user clicks on add button
    Then employee isn't created
    And field <field> is required
  Examples:
    | field      |
    | first name |
    | last name  |
    | start date |
    | email      |

  Scenario: Check creation functionality
    Given user is logged in
    When default employee doesn't exist
    And user clicks on create button
    And user creates new employee
    Then new employee is created

  Scenario: Check edition functionality
    Given user is logged in
    And check that one default employee exists
    When user edits employee
    And user changes employee's email to john2@doe.com
    Then employee's email equals john2@doe.com

  Scenario: Check deletion functionality
    Given user is logged in
    And check that one default employee exists
    When user deletes employee
    Then default employee is deleted