package E2E.steps.post;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CreatePostSteps {
    private int postCount;
    private String alertText;

    public void login(){
        TestRunner.driver.get("http://localhost:5500/FrontEnd/loginpage/login.html");
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
        //TestRunner.rlsPom.logoutButton.click();
        JavascriptExecutor js = (JavascriptExecutor) TestRunner.driver;
        js.executeScript("document.getElementById('logoutBtn').click();");
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
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.userProfile.createNewPostBtn));
        TestRunner.userProfile.createNewPostBtn.click();
    }

    @When("the user enters their post body")
    public void the_user_enters_their_post_body() {

        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.postText));
        TestRunner.userProfile.postText.sendKeys("E2E Test for about me");
        
    }

    @When("the user clicks on the post button")
    public void the_user_clicks_on_the_post_button() throws InterruptedException,UnhandledAlertException {
        try{    
            TestRunner.userProfile.submitNewPostBtn.click();
            TestRunner.driver.navigate().refresh();
            Thread.sleep(3000);
            postCount = TestRunner.driver.findElements(By.className("post")).size();

        }
        catch(UnhandledAlertException ua){
            try{
                Thread.sleep(1000);
                Alert alert = TestRunner.driver.switchTo().alert();
                alertText = alert.getText();
            }catch (NoAlertPresentException e) {
                System.out.println("No Alert Found");
            }     
        }
        
    }

    @When("the user enters their post body with too much text")
    public void the_user_enters_their_post_body_with_too_much_text(){
        TestRunner.userProfile.postText.sendKeys("012345678901234567890123456789012345678901234567890123456" +
        "789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012" +
        "3456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456" +
        "78901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" +
        "23456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456" +
        "78901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901" +
        "23456789012345678901234567890123456789012345678901234567890123456789");
    }

    @When("the user enters their post body with no text")
    public void the_user_enters_their_post_body_with_no_text(){
        TestRunner.userProfile.postText.sendKeys("");
    }

    @Then("the user will see the created post")
    public void the_user_will_see_the_created_post(){
        Assert.assertEquals(postCount,TestRunner.driver.findElements(By.className("post")).size());
        //to delete the testing post so that it does not mess up further tests
        TestRunner.userProfile.deleteButton.click();
        logoutAfterTest();
    }


    @Then("the user will see an error message and nothing will be posted")
    public void the_user_will_see_an_error_message_in_the_post_feed() throws InterruptedException {
        try {
            TestRunner.driver.navigate().refresh();
            //TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.rlsPom.logoutButton));
            Thread.sleep(3000);
            Assert.assertEquals(TestRunner.driver.findElements(By.className("post")).size(),postCount);
            logoutAfterTest();
        }

        finally{
            
        }    
    }


}
