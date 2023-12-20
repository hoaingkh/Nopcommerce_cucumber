#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@register
Feature: Register

  @TC01-RegisterEmptyData @run
  Scenario: Register with empty data
    When Click to Register link
    And Click to Register button
    Then Verify error message "First name is required." at First Name field
    Then Verify error message "Last name is required." at Last Name field
    Then Verify error message "Email is required." at Email field
    Then Verify error message "Password is required." at Password field
    Then Verify error message "Password is required." at Confirm Password field

  @TC02-RegisterInvalidEmail @run
  Scenario: Register with invalid email
    When Click to Register link
    And Input "First Name Demo" to First Name field
    And I wait 2 second
    And Input "Last Name Demo" to Last Name field
    And I wait 2 second
    And Input "abc" to Email field
    And I wait 2 second
    And Input "123123" to Password field
    And I wait 2 second
    And Input "123123" to Confirm Password field
    And I wait 2 second
    And Click to Register button
    And I wait 2 second
    Then Verify error message "Wrong email" at Email field

  @TC03-RegisterWithValidData
  Scenario: Register with valid info
    When Click to Register link
    When Input info for required fields
      | First Name | Last Name | Email          | Password | Confirm Password |
      | Van A      | Nguyen    | vana@gmail.com |   123123 |           123123 |
    And Click to Register button
    And I wait 3 second
    Then Verify successfully register

  @TC04-RegisterWithValidData-ScenarioOutlite
  Scenario Outline: Register with invalid email - outline
    When Click to Register link
    When Input info for required fields
      | First Name    | Last Name    | Email     | Password     | Confirm Password    |
      | <FirstNameEx> | <LastNameEx> | <EmailEx> | <PasswordEx> | <ConfirmPasswordEx> |
    And I wait 2 second
    And Click to Register button
    And I wait 2 second
    Then Verify error message "Wrong email" at Email field

    Examples: 
      | FirstNameEx | LastNameEx  | EmailEx | PasswordEx | ConfirmPasswordEx |
      | Van A       | Nguyen      | vana    |     123123 |            123123 |
      | Van!@$%A    | Nguyen ^%*% | v$$ana  |     123123 |            123123 |
      | Van      A  | Nguyen      | vanaJ:: |     123123 |            123123 |
