package E2E.steps.profile;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class EditProfileSteps {

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        TestRunner.driver.get("http://dans-code.net.s3-website-us-east-1.amazonaws.com/FrontEnd/loginpage/login.html");
        TestRunner.rlsPom.usernameInput.sendKeys("username");
        TestRunner.rlsPom.passcodeInput.sendKeys("passcode");
        TestRunner.driver.findElement(By.xpath("/html/body")).click();
        TestRunner.rlsPom.loginButton.click();
    }

   @When("the user clicks on the edit profile button")
   public void the_user_clicks_on_the_edit_profile_button() {
    //    TestRunner.userProfile.updateProfileEditButton.click();
        WebElement myelement = TestRunner.driver.findElement(By.id("updateProfileEditProfileBtn"));
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(myelement));
        JavascriptExecutor jse2 = (JavascriptExecutor)TestRunner.driver;
        jse2.executeScript("arguments[0].click();", myelement);
   }

   @When("the user modifies their about me section")
   public void the_user_modifies_their_about_me_section() {
       TestRunner.userProfile.updateProfileAboutMeInput.sendKeys("E2E Test for about me");
   }

   @When("the user selects a date")
   public void the_user_selects_a_date() {
       TestRunner.userProfile.updateProfileUserBirthDateInput.sendKeys("0101", Keys.RIGHT, "2022");
   }

   @When("the user clicks on the save changes button")
   public void the_user_clicks_on_the_save_changes_button() {
       TestRunner.userProfile.saveChangesModalButton.click();
   }

   @Then("there is a success message saying that the changes have been saved")
   public void there_is_a_success_message_saying_that_the_changes_have_been_saved() {
       TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElement(TestRunner.userProfile.profileSuccessMessage, "Saved"));
   }

   @When("the user clears the date")
   public void the_user_clears_the_date() {
       TestRunner.userProfile.updateProfileUserBirthDateInput.clear();
   }

   @Then("there is a failure message saying that the Birthdate may not be blank")
   public void there_is_a_failure_message_saying_that_the_birthdate_may_not_be_blank() {
       TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.profileErrorMessage));
   }
}
