Feature: User who go onto the Login Page should be able to click on the Forget Password link

    Scenario: User Creates a new password
        Given I am on the Login Page
        When I click reset password button
        When I am taken to the reset password page
        When I enter the email associated to the User
        When I am taken to the new password page
        When I input a new password
        And click the reset password button
        Then I will login with my new password

    # Scenario: User enters wrong email when trying to change password
    #     Given I am on the Login Page
    #     When I click reset password button
    #     When I am taken to the reset password page
    #     When I enter an invalid email address
    #     And I am notified email is invalid
    #     Then I will go back to Login Page