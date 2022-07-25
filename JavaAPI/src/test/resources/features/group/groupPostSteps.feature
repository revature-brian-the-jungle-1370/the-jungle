Feature: Users can have interaction within a group/team

  @seegroup
  Scenario: Group member can create a group post
    Given the user is on the login group page
    When the user clicks on their group
    When the group member enters their group post
    When the group member clicks the group post button
    Then a success message will appear

  @seegroup
  Scenario: Group member cannot create a blank group post
    Given the user is on the login group page
    When the user clicks on their group
    When the group member enters nothing on their group post
    When the group member clicks the group post button
    Then a fail message will appear

  @seegroup
  Scenario: Group member can delete a group post
    Given the user is on the login group page
    When the user clicks on their group
    When the user clicks delete post button
    Then the post will be deleted
