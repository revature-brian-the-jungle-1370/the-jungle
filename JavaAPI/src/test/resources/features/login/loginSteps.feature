Feature: User Login

  Scenario: As a user I want to log in so I can use the service
    Given the user is on the log-in page
    When the user enters correct username
    When the user enters correct password
    When the user clicks on log-in button again
    Then the user will be redirected to the homepage

  Scenario: As a user I want to log out from my account
    Given user is on the home-page
    When user clicks on the logout button
    Then user will be redirected to the landing page

  Scenario: As system I want to validate log-in credentials
    Given user is on the log-in page
    When the user enters wrong username
    When the  user enters wrong password
    When the user clicks on the log-in buttun
    Then error message will be displayed