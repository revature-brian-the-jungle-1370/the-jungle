package E2E.steps.profile;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class visitUsersPageSteps {

   @When("the user selects a friend’s profile")
   public void the_user_selects_a_friend_s_profile() {
       TestRunner.userProfile.userFollower.click();
   }

   @Then("the user is redirected to the friend’s profile page")
   public void the_user_is_redirected_to_the_friend_s_profile_page() {
       TestRunner.explicitWait.until(ExpectedConditions.titleIs("Visited User Page"));
   }
}
