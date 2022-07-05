package E2E.steps.group;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class viewGroupSteps {
    @Given("the user is on the main group page")
    public void the_user_is_on_the_main_group_page() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.driver.get("file:///C:/Users/Dearce/Desktop/Project3/The-Jungle/FrontEnd/grouppage/group-page.html");

    }
    @When("the user clicks on a group name")
    public void the_user_clicks_on_a_group_name() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.groupPage.groupToJoin.click();
    }
    @Then("the user will be redirected to that individual groups page")
    public void the_user_will_be_redirected_to_that_individual_groups_page() {
        // Write code here that turns the phrase above into concrete actions
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "Individual Group Page");
    }

    @When("the user clicks on a group name in the my group session")
    public void the_user_clicks_on_a_group_name_in_the_my_group_session() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.groupPage.myGroupsLink.click();
    }

    @Then("the user will be redirected to that individual my groups page")
    public void the_user_will_be_redirected_to_that_individual_my_groups_page() {
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "Individual Group Page");
    }

    @When("the user clicks on a group logo")
    public void the_user_clicks_on_a_group_logo() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.groupPage.groupLogoLink.click();
    }

    @Then("the user will be redirected to that individual groups logo page")
    public void the_user_will_be_redirected_to_that_individual_groups_logo_page() {
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "Individual Group Page");
    }




}
