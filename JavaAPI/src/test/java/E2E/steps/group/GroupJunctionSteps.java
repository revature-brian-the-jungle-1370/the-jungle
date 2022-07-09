//package E2E.steps.group;
//
//import E2E.runner.TestRunner;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//public class GroupJunctionSteps {
//    Actions actions = new Actions(TestRunner.driver);
//
//    @Then("the user should see the list of users in the group")
//    public void theUserShouldSeeTheListOfUsersInTheGroup() {
//       TestRunner.explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#member-3 > div:nth-child(1) > div")));
//       Assert.assertTrue(TestRunner.driver.findElement(By.cssSelector("#member-3 > div:nth-child(1) > div")).isDisplayed());
//    }
//
//    @Then("user can see who created the group")
//    public void userCanSeeWhoCreatedTheGroup() {
//        Assert.assertEquals(TestRunner.groupJunctionPOM.creator.getText(),"test create post,test");
//    }
//
//    @When("the user clicks the leave button")
//    public void theUserClicksTheLeaveButton() {
//        actions.moveToElement(TestRunner.groupJunctionPOM.leaveButton).click();
//    }
//
//    @Then("the user will be redirected to the group homepage")
//    public void theUserWillBeRedirectedToTheGroupHomepage() {
//        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Group Page"));
//        String title = TestRunner.driver.getTitle();
//        Assert.assertEquals(title,"Group Page");
//    }
//
//    @Then("the leave group button should not be visible")
//    public void theLeaveGroupButtonShouldNotBeVisible() {
//    }
//
//    @Given("User is on the group page")
//    public void userIsOnTheGroupPage() {
//        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/grouppage/group-page.html");
//    }
//
//    @When("the user selects the group")
//    public void theUserSelectsTheGroup() {
//        actions.moveToElement(TestRunner.driver.findElement(By.cssSelector("#groupLink-7")));
//        TestRunner.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/div[2]/div[5]/div")).click();
//    }
//
//    @When("User is redirected to the group individual page")
//    public void userIsRedirectedToTheGroupIndividualPage() {
//        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Individual Group Page"));
//    }
//
//    @When("User selects group page icon")
//    public void userSelectsGroupPageIcon() {
//        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[1]/div[3]/div[2]/div"))).click();
//    }
//
//    @Given("the user is on the login page")
//    public void theUserIsOnTheLoginPage() {
//        TestRunner.driver.get("file:///C:/Users/chris/OneDrive/Desktop/Revature/The-Jungle/FrontEnd/loginpage/login.html");
//    }
//
//    @When("the user enter the username")
//    public void theUserEnterTheUsername() {
//        TestRunner.driver.findElement(By.cssSelector("#usernameInput")).sendKeys("test");
//    }
//
//    @When("the user enters password")
//    public void theUserEntersPassword() {
//        TestRunner.driver.findElement(By.cssSelector("#passcodeInput")).sendKeys("createpost");
//    }
//
//    @When("The user will be redirected to their profile page")
//    public void theUserWillBeRedirectedToTheirProfilePage() {
//        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Home"));
//    }
//
//    @When("the user clicks the login button")
//    public void theUserClicksTheLoginButton() {
//        TestRunner.driver.findElement(By.id("submitLogin"));
//    }
//
//    @When("the user clicks the login button again")
//    public void theUserClicksTheLoginButtonAgain() {
//        TestRunner.driver.findElement(By.id("submitLogin"));
//    }
//
//    @When("user scrolls to group icon")
//    public void userScrollsToGroupIcon() {
//        actions.moveToElement(TestRunner.driver.findElement(By.xpath("/html/body/div/div/div[5]/div[2]")));
//    }
//}
