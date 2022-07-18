package E2E.steps.chat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import E2E.runner.TestRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class sendChatMessageSteps {

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        TestRunner.driver.get("http://s3.amazonaws.com/dans-code.net/FrontEnd/loginpage/login.html");
    }
    @When("I login as username username")
    public void i_login_as_username_username() {
        TestRunner.rlsPom.usernameInput.sendKeys("username");
        TestRunner.rlsPom.passwordInput.sendKeys("passcode");
        WebElement body = TestRunner.driver.findElement(By.xpath("/html/body"));
        body.click();
        TestRunner.rlsPom.loginButton.click();
    }
    @When("I click on chat option")
    public void i_click_on_chat_option() {
        Alert al = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(3)).until(ExpectedConditions.alertIsPresent());
        al.dismiss();
        TestRunner.userHomePage.chatLink.click();
    }
    @Given("I am on the chat page")
    public void i_am_on_the_chat_page() {
        Assert.assertEquals(TestRunner.driver.getTitle(), "ChatRoom");
    }
    @When("I input a chat message")
    public void i_input_a_chat_message() {
        TestRunner.chatPage.inputChatMessage("Hello from selenium");
        WebElement myelement = TestRunner.driver.findElement(By.id("send"));
        JavascriptExecutor jse2 = (JavascriptExecutor)TestRunner.driver;
        jse2.executeScript("arguments[0].scrollIntoView();", myelement);
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(myelement));
        jse2.executeScript("arguments[0].click();", myelement);
    }
    @Then("I should see the message")
    public void i_should_see_the_message() {
        List<WebElement> chats = TestRunner.driver.findElements(By.className("check-the-documentation"));
        Assert.assertEquals(chats.get(chats.size()-1).getText(), "Hello from selenium");
    }

    @When("I input a very long chat message")
    public void i_input_a_very_long_chat_message() {
        String longMessage = "This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message";
        TestRunner.chatPage.inputChatMessage(longMessage);
        WebElement myelement = TestRunner.driver.findElement(By.id("send"));
        JavascriptExecutor jse2 = (JavascriptExecutor)TestRunner.driver;
        jse2.executeScript("arguments[0].scrollIntoView();", myelement);
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(myelement));
        jse2.executeScript("arguments[0].click();", myelement);
    }

    @Then("I shouldn't see the message")
    public void i_shouldn_t_see_the_message() {
        String longMessage = "This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message This is a long message";
        List<WebElement> chats = TestRunner.driver.findElements(By.className("check-the-documentation"));
        Assert.assertNotEquals(chats.get(chats.size()-1).getText(), longMessage);
    }

    @When("I change chat rooms")
    public void i_change_chat_rooms() {
        TestRunner.driver.findElement(By.id("10000")).click();
        //maybe add alert
        TestRunner.driver.switchTo().alert().dismiss();
    }

    @Then("I should not see the message I posted")
    public void i_should_not_see_the_message_i_posted() {
        List<WebElement> chats = TestRunner.driver.findElements(By.className("check-the-documentation"));
        if (chats.size() > 0) {
            Assert.assertNotEquals(chats.get(chats.size()-1).getText(), "Hello from selenium");
        }
        else {
            Assert.assertTrue(true);
        }
    }

}
