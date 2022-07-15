package E2E.steps.chat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import E2E.runner.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class sendChatMessageSteps {

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        TestRunner.driver.get("http://s3.amazonaws.com/dans-code.net/FrontEnd/loginpage/login.html");
        i_login_as_username_username();
    }
    @When("I login as username username")
    public void i_login_as_username_username() {
        TestRunner.rlsPom.usernameInput.sendKeys("username");
        TestRunner.rlsPom.passwordInput.sendKeys("passcode");
        TestRunner.rlsPom.loginButton.click();
        i_click_on_chat_option();
    }
    @When("I click on chat option")
    public void i_click_on_chat_option() {
        TestRunner.userHomePage.chatLink.click();
    }
    @Given("I am logged in")
    public void i_am_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        i_am_on_the_login_page();
    }
    @When("I input a chat message")
    public void i_input_a_chat_message() {
        TestRunner.chatPage.sendMessage("Hello");
    }
    @Then("I should see the message")
    public void i_should_see_the_message() {
        List<WebElement> chats = TestRunner.driver.findElements(By.className("check-the-documentation"));
        Assert.assertEquals(chats.get(chats.size()-1).getText(), "Hello");
    }

}
