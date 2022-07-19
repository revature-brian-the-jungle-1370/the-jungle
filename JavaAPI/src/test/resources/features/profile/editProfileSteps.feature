# Feature: Edit Profile
#   Scenario: As a User, I should be able to change my about me section, so that I can change what people know about me
#     Given the user is on the homepage
#     When the user clicks on the edit profile button
#     When the user modifies their about me section
#     When the user selects a date
#     When the user clicks on the save changes button
#     Then there is a success message saying that the changes have been saved

#   Scenario: As a User, I should be able to change my birthday, so that I can change what people know about me
#     Given the user is on the homepage
#     When the user clicks on the edit profile button
#     When the user selects a date
#     When the user clicks on the save changes button
#     Then there is a success message saying that the changes have been saved

#   Scenario: As a system, I want to validate a user has given their birthday when updating the profile
#     Given the user is on the homepage
#     When the user clicks on the edit profile button
#     When the user modifies their about me section
#     When the user clears the date
#     When the user clicks on the save changes button
#     Then there is a failure message saying that the Birthdate may not be blank