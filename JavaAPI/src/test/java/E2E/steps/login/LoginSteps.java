package E2E.steps.login;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginSteps {

    private void returnPasswordToOriginal(){
        TestRunner.rlsPom.resetPasswordLink.click();
        TestRunner.rlsPom.emailInput.sendKeys("email");
        TestRunner.rlsPom.unfocus_text_box(TestRunner.rlsPom.emailInput);
        TestRunner.explicitWait.until(ExpectedConditions.elementSelectionStateToBe(TestRunner.rlsPom.submitEmail, false));
        TestRunner.rlsPom.submitEmail.click();
        TestRunner.rlsPom.passcodeInput.sendKeys("passcode");
        TestRunner.rlsPom.unfocus_text_box(TestRunner.rlsPom.passcodeInput);
        TestRunner.explicitWait.until(ExpectedConditions.elementSelectionStateToBe(TestRunner.rlsPom.submitPasscode, false));
        TestRunner.rlsPom.submitPasscode.click();
        TestRunner.explicitWait.until(ExpectedConditions.titleContains("Login"));
    }
    
    @Given("the user is on the log-in page")
    public void the_user_is_on_the_log_in_page() {
        TestRunner.driver.get("http://localhost:5500/FrontEnd/loginpage/login.html");
    }

    @When("the user enters correct username")
    public void the_user_enters_correct_username() {
        TestRunner.rlsPom.usernameInput.sendKeys("username");
    }

    @When("the user enters correct password")
    public void the_user_enters_correct_password() {
        TestRunner.rlsPom.passwordInput.sendKeys("passcode");
        TestRunner.rlsPom.unfocus_text_box(TestRunner.rlsPom.passwordInput);
    }

    @When("the user clicks on log-in button to log in")
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
        JavascriptExecutor js = (JavascriptExecutor) TestRunner.driver;
        js.executeScript("document.getElementById('logoutBtn').click();");
        //TestRunner.rlsPom.logoutButton.click();
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
        //TestRunner.driver.get("http://dans-code.net.s3-website-us-east-1.amazonaws.com/FrontEnd/loginpage/login.html");
        TestRunner.driver.get("https://localhost:5500/FrontEnd/loginpage/login.html");
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

    // Reset Password
    @When("the user clicks the reset password link")
    public void the_user_clicks_the_reset_password_link(){
        TestRunner.rlsPom.resetPasswordLink.click();
    }

    @When("Enters their email address")
    public void enters_their_email_address(){
        TestRunner.rlsPom.emailInput.sendKeys("email");
        TestRunner.rlsPom.unfocus_text_box(TestRunner.rlsPom.emailInput);
    }

    @When("clicks the reset password button")
    public void clicks_reset_password_button() throws InterruptedException{
        TestRunner.explicitWait.until(ExpectedConditions.elementSelectionStateToBe(TestRunner.rlsPom.submitEmail, false));
        TestRunner.rlsPom.submitEmail.click();
    }

    @When("then enters their new password")
    public void then_enters_their_new_password() throws InterruptedException{
        TestRunner.rlsPom.passcodeInput.sendKeys("newpasscode");
        TestRunner.rlsPom.unfocus_text_box(TestRunner.rlsPom.passcodeInput);
    }

    @When("clicks the reset password button again")
    public void clicks_the_reset_password_button_again(){
        TestRunner.explicitWait.until(ExpectedConditions.elementSelectionStateToBe(TestRunner.rlsPom.submitPasscode, false));
        TestRunner.rlsPom.submitPasscode.click();
    }

    @Then("they will return to the login page")
    public void then_they_return_to_the_login_page(){
        TestRunner.explicitWait.until(ExpectedConditions.titleContains("Login"));
        Assert.assertEquals(TestRunner.driver.getTitle(), "Login");
        //Return password to original value
        returnPasswordToOriginal();
    }

}
