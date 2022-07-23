package E2E.poms.group;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GroupPage {
    private WebDriver driver;

    public GroupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "submitJoinGroup")
    public WebElement submitJoinGroup;

    @FindBy(id = "groupJoined")
    public WebElement groupJoined;

    @FindBy(id= "groupLink-9000")
    public WebElement groupLink;

    @FindBy(id = "groupLink-32")
    public WebElement myGroupLink;

    @FindBy(xpath = "/html/body/div/div/div[1]/div[3]/div[2]/a/img")
    public WebElement groupLogoLink;

    @FindBy(id="groupLink-10000")//xpath = "/html/body/div/div/div[2]/div/div[3]/div[2]/div[5]/div")
    public WebElement groupToJoin;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[4]/div[1]/div[3]/div[1]/div")
    public WebElement myGroupsLink;

    @FindBy(id = "postInput")
    public WebElement getGroupPostInput;

    @FindBy(id = "sendGroupPostButton")
    public WebElement getSendGroupPostButton;

    @FindBy(id = "postInfo")
    public WebElement getPostInfoNotification;

    @FindBy(xpath = "//p[@id='allpost']/div[1]/div[1]/input")
    public WebElement getDeleteGroupPostButton;

    @FindBy(xpath = "//div[@id='userGroups-div']/div[1]/div")
    public WebElement myGroups;
}
