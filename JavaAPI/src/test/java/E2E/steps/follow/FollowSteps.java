package E2E.steps.follow;

import E2E.runner.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class FollowSteps {
    //Scenario 1 As a User I want to click on a user profile follow button
    @Given("the user is on another user's profile")
    public void the_user_is_on_another_users_profile(){
        TestRunner.driver.get("");
        //https://s3.amazonaws.com/dans-code.net/FrontEnd/loginpage/login.html
    }

    @When("the user clicks on the follow button")
    public void the_user_clicks_on_the_follow_button(){
        //TestRunner.rlsPom.followButton.click();
    }

    @Then("the user will be redirected to their feed page")
    public void the_user_will_be_redirected_to_their_feed_page(){
        //explicit wait for title?
        //String title = new TestRunner.driver.getTitle();
        //Assert.assertEquals(title, "");
    }


    //Scenario 2 As a User I want to unfollow a user
    @Given("the user is on another user's profile")
    public void the_user_is_on_a_followed_users_profile(){
        //TestRunner.driver.get("")
    }

    @When("the user clicks on the unfollow button")
    public void the_user_clicks_on_the_unfollow_button(){
        //TestRunner.rlsPom.unfollowButton.click();
    }

    @Then("the user will be redirected to their feed page")
    public void the_user_will_be_redirected_back_to_their_feed_page(){
        //explicit wait for title?
        //String title = new TestRunner.driver.getTitle();
        //Assert.assertEquals(title, "");
    }

    //Scenario 3 As a User I want to see my followers on the feed page
    //MAY CHANGE DEPENDING ON FUNCTIONALITY
    @Given("the user is logged in on homepage")
    public void the_user_is_logged_in_on_homepage(){

    }

    @When("the user clicks on their feed page")
    public void the_user_clicks_on_their_feed_page(){

    }

    @Then("the user will be redirected to the feed page and see their followers")
    public void the_user_will_be_redirected_to_their_feed_and_see_their_followers(){

    }
    

}
