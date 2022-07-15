package E2E.poms.chat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {
    private WebDriver webDriver;

    @FindBy(id = "messageInputBox")
    WebElement messageBoxInput;

    @FindBy(id = "send")
    WebElement sendMessageButton;

    public ChatPage(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }

    public void sendMessage(String input) {
        inputChatMessage(input);
        clickSendMessageButton();
    }

    public void inputChatMessage(String input) {
        messageBoxInput.sendKeys(input);
    }

    public void clickSendMessageButton() {
        sendMessageButton.click();
    }
}
