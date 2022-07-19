# Feature: Create Account

#   Scenario: As a User, I should be able to register a new account, so that I can log into the system.
#     Given the user is on the sign up page
#     When the user enters First name into the new account form
#     When the user enters Last name into the new account form
#     When the user enters Date of Birth into the new account form
#     When the user enters email into the new account form
#     When the user enters an username with a space into the new account form
#     When the user enters a password into the new account form
#     And an error message populates
#     When the user replaces a correct username into the new account form
#     When the user clicks on the submit button in the new account form
#     Then the user will be redirected to their profile page


#   Scenario: As the System, I want to reject bad new account creations from a duplicate email.
#     Given the user is now on the sign up page with form
#     When the user enters bad test First name into the new account form
#     When the user enters bad test Last name into the new account form
#     When the user enters bad test Date of Birth into the new account form
#     When the user enters a duplicate email into the new account form
#     When the user enters bad test username into the new account form
#     When the user enters bad test password into the new account form
#     When the user clicks on the bad test submit button in the new account form
#     Then a message for duplicate email error populates


#   Scenario: As the System, I want to reject bad new account creations from a blank username.
#     Given the user has now refreshed the sign up page
#     When the user enters a bad test First name into the new account form
#     When the user enters a bad test Last name into the new account form
#     When the user enters a bad test Date of Birth into the new account form
#     When the user enters a bad test email into the new account form
#     When the user enters a bad test password into the new account form
#     When the user clicks on the test submit button in the new account form
#     Then a message for blank inputs error populates
