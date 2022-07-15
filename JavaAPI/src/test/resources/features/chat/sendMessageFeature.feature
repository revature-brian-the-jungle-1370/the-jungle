Feature: Sending chat message
    As a User, I should be able to join a live chatroom with others and only see messages when I am in the room.

    Background:
        Given I am on the login page
        When I login as username username
        And I click on chat option

    Scenario: Send Message
        Given I am logged in
        When I input a chat message
        Then I should see the message
        