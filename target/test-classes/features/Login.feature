@login
Feature: Login feature

  @login_with_empty_data
  Scenario: Login with empty data
    When Click to Login link
    And Click to login button
    Then Verify error message "Please enter your email" at Email field for empty data

  @login_with_invalid_email
  Scenario: Login with invalid email
    When Click to Login link
    And Input "hoai@" in Email field
    And Input "123456" in Password field
    And Click to login button
    Then Verify error message "Wrong email" at Email field for Wrong email


    @login_with_unregistered_email
    Scenario: Login with unregistered email
      When Click to Login link
      And Input "hoaiabc@gmail.com" in Email field
      And Input "123456" in Password field
      And Click to login button
      Then Verify error summary message "No customer account found" for Email field


      @login_with_registered_email_and_no_password
      Scenario: Login with registered email and no fill password
        When Click to Register link
        When Input info for required fields
          | First Name | Last Name | Email          | Password | Confirm Password |
          | Van A      | Nguyen    | vana3@gmail.com |   123123 |           123123 |
        When Click to Register button
        And I wait 3 second
#        When Click to Continue button
#        And I wait 3 second
#        When Click to Logout button
#        And I wait 3 second
        When Click to Login link
        When Input registered email in Email field
        And I wait 3 second
        Then Verify error summary message "The credentials provided are incorrect" for Email field








