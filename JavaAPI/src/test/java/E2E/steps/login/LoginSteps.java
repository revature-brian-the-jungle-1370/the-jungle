package E2E.steps.login;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginSteps {

   @Given("the user is on the log-in page")
   public void the_user_is_on_the_log_in_page() {
       //TestRunner.driver.get("file:///Users/adamjanusewski/Desktop/The-Jungle/FrontEnd/loginpage/login.html");
       TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/loginpage/login.html");
   }

   @When("the user enters correct username")
   public void the_user_enters_correct_username() {
       TestRunner.rlsPom.usernameInput.sendKeys("test");
   }

   @When("the user enters correct password")
   public void the_user_enters_correct_password() {
       TestRunner.rlsPom.passwordInput.sendKeys("createpost");
       TestRunner.rlsPom.usernameInput.click();
   }

   @When("the user clicks on log-in button again")
   public void the_user_clicks_on_log_in_button_again() {
       TestRunner.explicitWait.until(ExpectedConditions.elementSelectionStateToBe(TestRunner.rlsPom.loginButton, false));
       TestRunner.rlsPom.loginButton.click();
   }

   @Then("the user will be redirected to the homepage")
   public void the_user_will_be_redirected_to_the_homepage() {
       TestRunner.explicitWait.until(ExpectedConditions.titleIs("Home"));
       String title = TestRunner.driver.getTitle();
       Assert.assertEquals(title, "Home");
   }

   //  LOGOUT

   @Given("user is on the home-page")
   public void user_in_on_the_home_page() {
       String title = TestRunner.driver.getTitle();
       Assert.assertEquals(title, "Home");
   }

   @When("user clicks on the logout button")
   public void user_clicks_on_the_log_out_button() throws InterruptedException {
       Thread.sleep(1000);
       TestRunner.rlsPom.logoutButton.click();
   }

   @Then("user will be redirected to the landing page")
   public void user_will_be_redirected_to_the_landing_page() {
       TestRunner.explicitWait.until(ExpectedConditions.titleIs("Login"));
       String title = TestRunner.driver.getTitle();
       Assert.assertEquals(title, "Login");
   }

   // SYSTEM STEPS

   @Given("user is on the log-in page")
   public void user_is_on_the_log_in_page() {
       TestRunner.driver.get("file:///Users/adamjanusewski/Desktop/The-Jungle/FrontEnd/loginpage/login.html");
   }

   @When("the user enters wrong username")
   public void the_user_enters_wrong_username() {
       TestRunner.rlsPom.usernameInput.sendKeys("testIamWrong");
   }

   @When("the  user enters wrong password")
   public void the_user_enters_wrong_password() {
       TestRunner.rlsPom.passwordInput.sendKeys("ITooAmWrong");
       TestRunner.rlsPom.usernameInput.click();
   }

   @When("the user clicks on the log-in buttun")
   public void the_user_clicks_on_the_log_in_buttun() {
       TestRunner.explicitWait.until(ExpectedConditions.elementSelectionStateToBe(TestRunner.rlsPom.loginButton, false));
       TestRunner.rlsPom.loginButton.click();
   }

   @Then("error message will be displayed")
   public void error_message_will_be_displayed() {
       Assert.assertEquals(TestRunner.rlsPom.errorMessage.getText(), "");
   }

}
