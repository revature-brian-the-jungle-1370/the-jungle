package E2E.poms;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegLoginSearchPOM {

    private WebDriver webDriver;

    public RegLoginSearchPOM(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }

    // ------------------- LOGIN --------------------------------

    @FindBy(id = "usernameInput")
    public WebElement usernameInput;

    @FindBy(id = "passcodeInput")
    public WebElement passwordInput;

    @FindBy(id = "submitLogin")
    public WebElement loginButton;

    // ------------------- LOGOUT --------------------------------

    @FindBy(id = "logoutBtn")
    public WebElement logoutButton;

    // -------------------- SYSTEM ---------------------------------

    @FindBy(id = "errorMessageGoesHere")
    public WebElement errorMessage;

    // ------------------- SEARCH ----------------------------------

    @FindBy(id = "searchInputBox") // SEARCH USERNAME ID
    public WebElement usernameSearch;

    @FindBy(id = "searchButton") // Submit Button for search
    public WebElement submitButton;

    @FindBy(id = "searchList") // Search Results
    public WebElement getSearchResults;

    // REGISTRATION WEB ELEMENTS
    @FindBy(id = "signup-firstname")
    public WebElement signUpFirstName;

    @FindBy(id = "signup-lastname")
    public WebElement signUpLastName;

    @FindBy(id = "signup-email")
    public WebElement signUpEmail;

    @FindBy(id = "signup-bdate")
    public WebElement signUpBirthdate;

    @FindBy(id = "signup-username")
    public WebElement signUpUsername;

    @FindBy(id = "signup-password")
    public WebElement signUpPassword;

    @FindBy(id = "invalidUserName")
    public WebElement usernameErrorMessage;

    @FindBy(id = "signup-submit")
    public WebElement signUpSubmitButton;

    @FindBy(id = "errorSubmitMessage")
    public WebElement regErrorMessage;

    // Reset Password
    @FindBy(className = "reset-password-link")
    public WebElement resetPasswordLink;
    
    @FindBy(id = "emailInput")
    public WebElement emailInput;

    @FindBy(id = "submitEmail")
    public WebElement submitEmail;

    @FindBy(id = "passcodeInput")
    public WebElement passcodeInput;

    @FindBy(id = "submitPasscode")
    public WebElement submitPasscode;

    public void unfocus_text_box(WebElement text_box){
        text_box.sendKeys(Keys.TAB);
    }
}
