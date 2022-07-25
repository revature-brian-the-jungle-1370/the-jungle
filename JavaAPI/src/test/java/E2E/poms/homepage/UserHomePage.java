package E2E.poms.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserHomePage {
    private WebDriver driver;

    public UserHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "groupName")
    public WebElement groupName;

    @FindBy(id = "groupAbout")
    public WebElement groupAbout;

    @FindBy(id = "submitCreateGroup")
    public WebElement submitCreateGroup;

    @FindBy(id = "messageGroupCreated")
    public WebElement messageGroupCreated;

    @FindBy(id = "groupNameNull")
    public WebElement groupNameNull;

    @FindBy(id = "groupAboutNull")
    public WebElement groupAboutNull;

    @FindBy(id = "groupNameThreeChar")
    public WebElement groupNameThreeChar;

    @FindBy(id = "groupNameFortyChar")
    public WebElement groupNameFortyChar;

    @FindBy(id = "groupAbout500Char")
    public WebElement groupAbout500Char;

    @FindBy(id = "duplicateGroupNameMessage")
    public WebElement duplicateGroupNameMessage;

    @FindBy(xpath = "//div[@id='groups-div']/div[1]/div")
    public WebElement groupToJoin;

    @FindBy(xpath = "//div[@id='groups-div']/div[2]/div")
    public WebElement selectGroup;

    @FindBy(xpath = "/html/body/div/div/div[5]/div[1]/a")
    public WebElement selectChat;
}
