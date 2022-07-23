package E2E.steps.follow;

import E2E.runner.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class FollowSteps {
    //Scenario 1 As a User I want to click on a user profile follow button
    @Given("the user is on another user's profile")
    public void the_user_is_on_another_users_profile(){
        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/loginpage/login.html");
        TestRunner.rlsPom.usernameInput.sendKeys("followtest");
        TestRunner.rlsPom.passwordInput.sendKeys("followtest");
        WebElement body = TestRunner.driver.findElement(By.xpath("/html/body"));
        body.click();
        TestRunner.rlsPom.loginButton.click();
        TestRunner.driver.get("http://127.0.0.1:5500/frontend/profilepage/profile-page.html?userId=1606");
    }

    @When("the user clicks on the follow button")
    public void the_user_clicks_on_the_follow_button(){
        TestRunner.userProfile.followUserButton.click();
    }

    @Then("the user will be redirected to their feed page")
    public void the_user_will_be_redirected_to_their_feed_page(){
        Assert.assertEquals(TestRunner.driver.getTitle(), "Home");
    }


    //Scenario 2 As a User I want to see my followers on the home page 
    @Given("the user is logged in on homepage")
    public void the_user_is_logged_in_on_the_homepage(){
        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/loginpage/login.html");
        TestRunner.rlsPom.usernameInput.sendKeys("followtest");
        TestRunner.rlsPom.passwordInput.sendKeys("followtest");
        WebElement body = TestRunner.driver.findElement(By.xpath("/html/body"));
        body.click();
        TestRunner.rlsPom.loginButton.click();
    }

    @Then("the user will be able to see their followers")
    public void the_user_will_be_able_to_see_their_followers(){
        String message = TestRunner.userProfile.followersList.getAccessibleName();
        Assert.assertEquals(message, "followmetest1");
    }

    //Scenario 3 As a User I want to unfollow a user
    @When("the user clicks on the unfollow button")
    public void the_user_clicks_on_the_unfollow_button(){
        TestRunner.userProfile.unfollowUserButton.click();
    }

/*     @Then("the user will be redirected to their feed page")
    public void the_user_will_be_redirected_back_to_their_feed_page(){
        //explicit wait for title?
        //String title = new TestRunner.driver.getTitle();
        //Assert.assertEquals(title, "");
    } */

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
