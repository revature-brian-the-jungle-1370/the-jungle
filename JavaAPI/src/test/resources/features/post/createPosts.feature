Feature: Create Post


Scenario: As a system I want to reject posts with too much text
  Given the user is on their dashboard page
  When the user clicks on the create post modal button
  When the user enters their post body with too much text
  When the user clicks on the post button
  Then the user will see an error message and nothing will be posted

Scenario: As a system I want to reject posts with no text
  Given the user is on their dashboard page
  When the user clicks on the create post modal button
  When the user enters their post body with no text
  When the user clicks on the post button
  Then the user will see an error message and nothing will be posted

Scenario: As a user I want to create a post
  Given the user is on their dashboard page
  When the user clicks on the create post modal button
  When the user enters their post body
  When the user clicks on the post button
  Then the user will see the created post
  



