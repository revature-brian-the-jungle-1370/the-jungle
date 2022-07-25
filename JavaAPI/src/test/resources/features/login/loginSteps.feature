Feature: User Login

Scenario: As a user I want to log in so I can use the service
  Given the user is on the log-in page
  When the user enters correct username
  When the user enters correct password
  When the user clicks on log-in button to log in
  Then the user will be redirected to the homepage

Scenario: As a user I want to log out from my account
  # Given user is on the home-page
  Given the user is on the log-in page
  When the user enters correct username
  When the user enters correct password
  When the user clicks on log-in button to log in
  When user clicks on the logout button
  Then user will be redirected to the landing page

  Scenario: As system I want to validate log-in credentials
    Given the user is on the log-in page
    When the user enters wrong username
    When the  user enters wrong password
    When the user clicks on the log-in buttun
    Then error message will be displayed

  Scenario: As a user I would like to reset my password
    Given the user is on the log-in page
    When the user clicks the reset password link
    And Enters their email address
    And clicks the reset password button
    And then enters their new password
    And clicks the reset password button again
    Then they will return to the login page