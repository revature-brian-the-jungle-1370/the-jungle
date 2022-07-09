//package E2E.steps.post;
//
//import E2E.runner.TestRunner;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//
//public class CreatePostSteps {
//    @When("the user clicks on the create post modal button")
//    public void the_user_clicks_on_the_create_post_modal_button() {
//        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.profileCreateANewPost));
//        TestRunner.userProfile.profileCreateANewPost.click();
//    }
//
//    @When("the user enters their post body")
//    public void the_user_enters_their_post_body() {
//        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.postText));
//        TestRunner.userProfile.postText.sendKeys("E2E Test for about me");
//    }
//
//    @When("the user clicks on the post button")
//    public void the_user_clicks_on_the_post_button() {
//        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.submitNewPostButton));
//        TestRunner.userProfile.submitNewPostButton.click();
//    }
//
//    @Then("the user will see the created post")
//    public void the_user_will_see_the_created_post() {
//
//        TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("newPostText"), "E2E Test for about me"));
//        Assert.assertEquals(TestRunner.userProfile.newPostText.getText(), "E2E Test for about me");
//
//        //to delete the testing post so that it does not mess up further tests
//        TestRunner.userProfile.deleteButton.click();
//    }
//
//
//    @Then("the user will see an error message in the post feed")
//    public void the_user_will_see_an_error_message_in_the_post_feed() {
//        TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("postErrorMessage"), "Error creating the post."));
//        Assert.assertEquals(TestRunner.userProfile.postErrorMessage.getText(), "Error creating the post.");
//    }
//
//    @When("the user enters their post body with too much text")
//    public void the_user_enters_their_post_body_with_too_much_text() {
//        TestRunner.userProfile.postText.sendKeys("012345678901234567890123456789012345678901234567890123456" +
//                "789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012" +
//                "3456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456" +
//                "78901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" +
//                "23456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456" +
//                "78901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" +
//                "23456789012345678901234567890123456789012345678901234567890123456789");
//    }
//    }
