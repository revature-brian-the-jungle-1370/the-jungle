package E2E.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class UserProfile {

    private WebDriver driver;

    public UserProfile(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "updateProfileEditProfileBtn")
    public WebElement updateProfileEditButton;

    @FindBy(id="userAboutMeInput")
    public WebElement updateProfileAboutMeInput;

    @FindBy(id="userBirthdateInput")
    public WebElement updateProfileUserBirthDateInput;

    @FindBy(id="updateProfileModalBtn")
    public WebElement saveChangesModalButton;
    @FindBy(id="profileModalMsg")
    public WebElement profileSuccessMessage;
    @FindBy(id="createPostBtn")
    public WebElement createNewPostBtn;
    @FindBy(id="postText")
    public WebElement postText;
    @FindBy(id="submitNewPostBtn")
    public WebElement submitNewPostBtn;
    @FindBy(id= "newPostText")
    public WebElement newPostText;

    @FindBy(className= "three-dots-icon")
    public WebElement deleteButton;

    @FindBy(id="postErrorMessage")
    public WebElement postErrorMessage;

    @FindBy(id="profileModalMsg")
    public WebElement profileErrorMessage;

    @FindBy(xpath="/html/body/div/div/div/div[10]/div[2]/div/div")
    public WebElement userFollower;

    @FindBy(id="follow-user-button")
    public WebElement followUserButton;
    @FindBy(id="unfollow-user-button")
    public WebElement unfollowUserButton;
    @FindBy(xpath = "//div[@id=\"followers-div\"]/div/div/a")
    public WebElement followersList;


}
