package E2E.poms.group;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class GroupJunctionPOM {
    private WebDriver webDriver;


    public GroupJunctionPOM(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }


    @FindBy(id = "tbd")
    public WebElement leaveButton;

    @FindBy(xpath = "#member-3 > div:nth-child(1) > div")
    public WebElement userInGroupList;

    @FindBy(xpath = "#groupName")
    public WebElement groupName;

    @FindBy(xpath = "//div[@id='groupCreator']")
    public WebElement creator;
}
