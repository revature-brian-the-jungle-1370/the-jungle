Feature: Test Create Individual Group Functionality


Scenario: A user wants to create an individual group
Given a user is on group page
When a user enters group name and description
When a user clicks on add group button
Then the group is displayed under Groups


Scenario: A user wants to visit individual group page
When a user clicks on group name under Groups
Then a user gets navigated to individual group page

Scenario: A user wants to post
When a user writes a post
When a user clicks on post button
Then the post is displayed below

Scenario: A user wants to delete a post
When a user clicks on delete button
Then the post gets deleted