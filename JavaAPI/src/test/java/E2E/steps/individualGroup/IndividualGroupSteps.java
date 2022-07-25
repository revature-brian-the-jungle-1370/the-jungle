package E2E.steps.individualGroup;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class IndividualGroupSteps {

    @Given("a user is on group page")
    public void a_user_is_on_group_page() throws InterruptedException{
        TestRunner.driver.get("https://s3.amazonaws.com/dans-code.net/FrontEnd/grouppage/group-page.html");
        Thread.sleep(2000);
        TestRunner.driver.navigate().refresh();
    }
        
    @When("a user enters group name and description")
    public void a_user_enters_group_name_and_description() throws InterruptedException{
        TestRunner.individualGroupPOM.name_text_box.sendKeys("Group 3 Testing");
        TestRunner.individualGroupPOM.description_text_box.sendKeys("Testing for BDD");
        Thread.sleep(2000);
    }

    @When("a user clicks on add group button")
    public void a_user_clicks_on_add_group_button() throws InterruptedException{
        
        System.out.println(TestRunner.individualGroupPOM.add_button);
        TestRunner.individualGroupPOM.add_button.click();
        
    }

    @Then("the group is displayed under Groups")
    public void the_group_is_displayed_under_MyGroups() throws InterruptedException{
        TestRunner.driver.navigate().refresh();
        Thread.sleep(2000);
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), "https://s3.amazonaws.com/dans-code.net/FrontEnd/grouppage/group-page.html");
    }

    @When("a user clicks on group name under Groups")
    public void a_user_clicks_on_group_name_under_My_Groups() throws InterruptedException{
        WebElement m = TestRunner.individualGroupPOM.group_name;
        JavascriptExecutor j =(JavascriptExecutor) TestRunner.driver;
        j.executeScript("window.scrollBy(0,-500)");
        Thread.sleep(2000);
        j.executeScript("arguments[0].click();",m);
        
    }

    @Then("a user gets navigated to individual group page")
    public void a_user_gets_navigated_to_individual_group_page() throws InterruptedException{
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), "https://s3.amazonaws.com/dans-code.net/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
        Thread.sleep(2000);
    }

    @When("a user writes a post")
    public void a_user_writes_a_post() throws InterruptedException{
        TestRunner.driver.get("https://s3.amazonaws.com/dans-code.net/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
        TestRunner.individualGroupPOM.post_text_box.sendKeys("BDD Testing");
        Thread.sleep(2000);
    }

    @When("a user clicks on post button")
    public void a_user_clicks_on_post_button() throws InterruptedException{
        TestRunner.individualGroupPOM.post_button.click();
        Thread.sleep(2000);
    }

    @Then("the post is displayed below")
    public void the_post_is_displayed_below() throws InterruptedException{
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), "https://s3.amazonaws.com/dans-code.net/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
        Thread.sleep(4000);
    }

    @When("a user clicks on delete button")
    public void a_user_clicks_on_delete_button() throws InterruptedException{
        TestRunner.individualGroupPOM.delete_button.click();
        Thread.sleep(2000);
    }
    
    @Then("the post gets deleted")
    public void the_post_gets_deleted() throws InterruptedException{
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), "https://s3.amazonaws.com/dans-code.net/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
        Thread.sleep(2000);
    }

    @When("a user clicks on like button")
    public void a_user_clicks_on_like_button() throws InterruptedException{
        TestRunner.individualGroupPOM.like_button.click();
        Thread.sleep(2000);
    }

    @Then("the post gets liked")
    public void the_post_gets_liked() throws InterruptedException{
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), "http://127.0.0.1:5500/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
        Thread.sleep(2000);
    }

    @When("a user clicks on like button twice")
    public void a_user_clicks_on_like_button_twice() throws InterruptedException{
        TestRunner.individualGroupPOM.like_button.click();
        Thread.sleep(2000);
    }

    @Then("the post gets unliked")
    public void the_post_gets_unliked() throws InterruptedException{
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), "http://127.0.0.1:5500/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
        Thread.sleep(2000);
    }

    @When("user clicks on bookmark icon of a post")
    public void user_clicks_on_bookmark_icon_of_a_post() throws InterruptedException{
        TestRunner.individualGroupPOM.bookmark_button.click();
        Thread.sleep(2000);
    }
    @Then("the post is saved and the icon is changed")
    public void the_post_is_saved_and_the_icon_is_changed() throws InterruptedException{
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), "http://127.0.0.1:5500/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
        Thread.sleep(2000);
    }

    @When("user clicks on bookmark icon of a post that is bookmarked")
    public void user_clicks_on_bookmark_icon_of_a_post_that_is_bookmarked() throws InterruptedException{
        TestRunner.individualGroupPOM.bookmark_button.click();
        Thread.sleep(2000);
    }
    @Then("the post is unsaved and the icon is changed")
    public void the_post_is_unsaved_and_the_icon_is_changed() throws InterruptedException{
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), "http://127.0.0.1:5500/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
        Thread.sleep(2000);
    }

}
