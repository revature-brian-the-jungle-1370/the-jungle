//package E2E.steps.group;
//
//import E2E.runner.TestRunner;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.Assert;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//import static E2E.runner.TestRunner.driver;
//
//public class GroupPostSteps {
//    Actions action = new Actions(driver);
//    // ------------------------------------- CREATE A GROUP POST-----------------------------------------
//    @Given("the group member is on the group page")
//    public void the_group_member_is_on_the_group_page() {
//        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
//    }
//
//    @When("the group member enters their group post")
//    public void the_group_member_enters_their_group_post() {
//        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.groupPage.getGroupPostInput));
//        TestRunner.groupPage.getGroupPostInput.sendKeys("E2E Test: Creating Group Post");
//    }
//    @When("the group member clicks the group post button")
//    public void the_group_member_clicks_the_group_post_button() {
//        TestRunner.groupPage.getSendGroupPostButton.click();
//    }
//
//    @Then("a success message will appear")
//    public void a_success_message_will_appear() {
//        Assert.assertNotNull(TestRunner.groupPage.getPostInfoNotification);
//    }
//
//    @When("the group member enters nothing on their group post")
//    public void the_group_member_enters_nothing_on_their_group_post() {
//        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.groupPage.getGroupPostInput));
//        TestRunner.groupPage.getGroupPostInput.sendKeys("");
//    }
//
//    @Then("a fail message will appear")
//    public void a_fail_message_will_appear() {
//        Assert.assertNotNull(TestRunner.groupPage.getPostInfoNotification);
//    }
//    //  ------------------------------------- DELETE A GROUP POST-----------------------------------------
//    @When("the user clicks delete post button")
//    public void the_user_clicks_delete_post_button() {
//        action.moveToElement(TestRunner.groupPage.getDeleteGroupPostButton);
//        TestRunner.groupPage.getDeleteGroupPostButton.click();
//    }
//    @Then("the post will be deleted")
//    public void the_post_will_be_deleted() {
//        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
//        Assert.assertNotNull(TestRunner.groupPage.getDeleteGroupPostButton);
//    }
//
//}
