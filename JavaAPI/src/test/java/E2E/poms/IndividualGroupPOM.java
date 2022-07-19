package E2E.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndividualGroupPOM {
    private WebDriver driver;

    public IndividualGroupPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


@FindBy(id = "groupName")
public WebElement name_text_box;

@FindBy(id = "groupAbout")
public WebElement description_text_box;

@FindBy(id = "submitCreateGroup")
public WebElement add_button;

@FindBy(xpath = "//*[@id='groups-div']/div[1]/div[1]/a")
public WebElement group_name;

@FindBy(id = "postInput")
public WebElement post_text_box;

@FindBy(id = "sendGroupPostButton")
public WebElement post_button;

@FindBy(xpath = "//*[@id='postInfo']/div[1]/div[1]/input")
public WebElement delete_button;

@FindBy(xpath = "//*[@id='allpost']/div[1]/div[2]/input[contains(@id,'likePost')]")
public WebElement like_button;

@FindBy(xpath = "//*[@id='allpost']/div[1]/div[2]/input[contains(@id,'bookmarkPost')]")
public WebElement bookmark_button;

}
