Feature: User can follow other Users

    Scenario: As a User I want to click on a user profile follow button
        Given the user is on another user's profile
        When the user clicks on the follow button
        Then the user will be redirected to their feed page

    Scenario: As a User I want to see my followers on the home page
        Given the user is logged in on homepage
        Then the user will be able to see their followers
        
    Scenario: As a User I want to unfollow a user
        Given the user is on another user's profile
        When the user clicks on the unfollow button
        Then the user will be redirected to their feed page


    
    

