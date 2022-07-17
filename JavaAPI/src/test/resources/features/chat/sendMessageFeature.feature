@chat_message
Feature: Sending chat message
    As a User, I should be able to join a live chatroom with others and only see messages when I am in the room.

    Background:
        Given I am on the login page
        When I login as username username
        And I click on chat option

    Scenario: Send Message
        Given I am on the chat page
        When I input a chat message
        Then I should see the message

    Scenario: Send Too Long Message
        Given I am on the chat page
        When I input a very long chat message
        Then I shouldn't see the message
        