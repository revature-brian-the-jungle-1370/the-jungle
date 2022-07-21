package E2E.steps.post;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CreatePostSteps {
    private int postCount;
    private int newPostCount;
    public void login(){
        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/loginpage/login.html");
        TestRunner.rlsPom.usernameInput.sendKeys("test");
        TestRunner.rlsPom.passwordInput.sendKeys("createpost");
        TestRunner.rlsPom.usernameInput.sendKeys(Keys.TAB);
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.rlsPom.loginButton));
        TestRunner.rlsPom.loginButton.click();
        try {
            TestRunner.explicitWait.until(ExpectedConditions.titleIs("Home"));
        } catch (Exception e) {

        }
    }

    public void logoutAfterTest(){
        TestRunner.rlsPom.logoutButton.click();
    }

    @Given("the user is on their dashboard page")
    public void the_user_is_on_their_dashboard_page() throws InterruptedException{
        login();
        Thread.sleep(1500);
        postCount = TestRunner.driver.findElements(By.className("post")).size();
        Assert.assertEquals(TestRunner.driver.getTitle(), "Home"); 
    }

    @When("the user clicks on the create post modal button")
    public void the_user_clicks_on_the_create_post_modal_button() {
        TestRunner.userProfile.createNewPostBtn.click();
        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.createNewPostBtn));
    }

    @When("the user enters their post body")
    public void the_user_enters_their_post_body() {
        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.postText));
        TestRunner.userProfile.postText.sendKeys("E2E Test for about me");
    }

    @When("the user clicks on the post button")
    public void the_user_clicks_on_the_post_button() {
        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.submitNewPostBtn));
        TestRunner.userProfile.submitNewPostBtn.click();
        TestRunner.driver.navigate().refresh();
        postCount+=1;
    }

    @Then("the user will see the created post")
    public void the_user_will_see_the_created_post() throws InterruptedException {
        Thread.sleep(1500);
        Assert.assertEquals(TestRunner.driver.findElements(By.className("post")).size(),postCount);
        //to delete the testing post so that it does not mess up further tests
        TestRunner.userProfile.deleteButton.click();
        logoutAfterTest();
    }


    @Then("the user will see an error message in the post feed")
    public void the_user_will_see_an_error_message_in_the_post_feed() {
        TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("postErrorMessage"), "Error creating the post."));
        Assert.assertEquals(TestRunner.userProfile.postErrorMessage.getText(), "Error creating the post.");
        logoutAfterTest();
    }

    @When("the user enters their post body with too much text")
    public void the_user_enters_their_post_body_with_too_much_text() {
        TestRunner.userProfile.postText.sendKeys("012345678901234567890123456789012345678901234567890123456" +
                "789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012" +
                "3456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456" +
                "78901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" +
                "23456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456" +
                "78901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" +
                "23456789012345678901234567890123456789012345678901234567890123456789");
    }
}
