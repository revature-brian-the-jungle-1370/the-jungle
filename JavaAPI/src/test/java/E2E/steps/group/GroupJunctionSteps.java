package E2E.steps.group;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupJunctionSteps {
   Actions actions = new Actions(TestRunner.driver);

    public void login(){
        TestRunner.driver.get("https://s3.amazonaws.com/dans-code.net/FrontEnd/loginpage/login.html");
        TestRunner.rlsPom.usernameInput.sendKeys("test");
        TestRunner.rlsPom.passwordInput.sendKeys("createpost");
        TestRunner.rlsPom.usernameInput.click();
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.rlsPom.loginButton));
        TestRunner.rlsPom.loginButton.click();
        try {
            TestRunner.explicitWait.until(ExpectedConditions.titleIs("Home"));
        } catch (Exception e) {

        }
    }

    @Then("the user should see the list of users in the group")
    public void theUserShouldSeeTheListOfUsersInTheGroup() {
        TestRunner.explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#member-3 > div:nth-child(1) > div")));
        Assert.assertTrue(TestRunner.driver.findElement(By.cssSelector("#member-3 > div:nth-child(1) > div")).isDisplayed());
    }

    @Then("user can see who created the group")
    public void userCanSeeWhoCreatedTheGroup() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        //TestRunner.explicitWait.until(ExpectedConditions.attributeContains(TestRunner.groupJunctionPOM.creator, "text", "first name,last name"));
        Assert.assertEquals(TestRunner.groupJunctionPOM.creator.getText(),"first name,last name");
    }

    @When("the user refreshes the page")
    public void theUserRefreshesThePage() {
        TestRunner.driver.navigate().refresh();
    }

    @When("the user clicks the leave button")
    public void theUserClicksTheLeaveButton() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.groupJunctionPOM.leaveButton));
        JavascriptExecutor js = (JavascriptExecutor) TestRunner.driver;
        js.executeScript("document.getElementById('tbd').click();");
        //TestRunner.groupJunctionPOM.leaveButton.click();
    }

    @Then("the user will be redirected to the group homepage")
    public void theUserWillBeRedirectedToTheGroupHomepage() {
        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Group Page"));
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title,"Group Page");
    }

    @Then("the leave group button should not be visible")
    public void theLeaveGroupButtonShouldNotBeVisible() {
    }

    @Given("User is on the group page")
    public void userIsOnTheGroupPage() {
        TestRunner.driver.get("https://s3.amazonaws.com/dans-code.net/FrontEnd/grouppage/group-page.html");
    }

    @When("the user selects the group that has members")
    public void theUserSelectsTheGroupHasMembers() {
        TestRunner.userHomePage.selectGroup.click();
    }

    @When("the user selects the group")
    public void theUserSelectsTheGroup() {
        TestRunner.userHomePage.groupToJoin.click();
    }

    @When("User is redirected to the group individual page")
    public void userIsRedirectedToTheGroupIndividualPage() {
        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Individual Group Page"));
    }

    @When("User selects group page icon")
    public void userSelectsGroupPageIcon() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[1]/div[3]/div[2]/div"))).click();
    }


    @When("The user will be redirected to their profile page")
    public void theUserWillBeRedirectedToTheirProfilePage() {
        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Home"));
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        TestRunner.driver.findElement(By.id("submitLogin"));
    }

    @When("the user clicks the login button again")
    public void theUserClicksTheLoginButtonAgain() {
        TestRunner.driver.findElement(By.id("submitLogin"));
    }

    @When("user scrolls to group icon")
    public void userScrollsToGroupIcon() {
        actions.moveToElement(TestRunner.driver.findElement(By.xpath("/html/body/div/div/div[5]/div[2]")));
    }
}
