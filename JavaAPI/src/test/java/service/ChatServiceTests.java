package service;

import dev.com.thejungle.customexception.InvalidInputException;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.dao.interfaces.ChatDAOInt;
import dev.com.thejungle.entity.ChatMessage;
import dev.com.thejungle.service.implementations.ChatService;
import dev.com.thejungle.service.interfaces.ChatServiceInt;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ChatServiceTests {

    public static ChatDAO chatDAO = new ChatDAO();
    public static ChatServiceInt chatService = new ChatService(chatDAO);
    public static ChatDAO mockchatDAO = new ChatDAO();
    public static ChatServiceInt mockchatService = new ChatService(mockchatDAO);

    //Vars
    static ChatMessage validMessage;
    static ChatMessage invalidUserId;
    static ChatMessage invalidGroupId;
    static ChatMessage emptyChatContent;
    static ChatMessage longChatContent;
    static ArrayList<ChatMessage> newList;

    @BeforeClass
    public void setup() {
        mockchatDAO = Mockito.mock(ChatDAO.class);
        mockchatService = new ChatService(mockchatDAO);
        validMessage = new ChatMessage(1, 2, "Valid Contents.");
        invalidUserId = new ChatMessage(0, 13, "Contents");
        invalidGroupId = new ChatMessage(1, -1, "Contents");
        emptyChatContent = new ChatMessage(1, 2, "");
        longChatContent = new ChatMessage(1, 2, "This is " +
                    "looooooooooooooooooooooooooooooooooooooooooooooooooooo" + 
                    "oooooooooooooooooooooooooooooooooooooooooooooooooooooo" + 
                    "oooooooooooooooooooooooooooooooooooooooooooooooooooooo" + 
                    "oooooooooooooooooooooooooooooooooooooooooooooooooooooo" + 
                    "oooooooooooooooooooooooooooooooooooooooooooooooooooooo" + 
                    "ooooooooooooooooooooong");
        newList = new ArrayList<ChatMessage>();
        newList.add(new ChatMessage(1, 2, "Content_1"));
        newList.add(new ChatMessage(2, 2, "Content_2"));
    }

    
//  ------------------------------------ MOCK TESTS ----------------------------------------

    // CREATE MESSAGE
    @Test
    public void createMessageObjectMockito() {
        Mockito.when(mockchatDAO.createMessage(validMessage)).thenReturn(validMessage);
        ChatMessage actual = mockchatService.serviceCreateMessageObject(validMessage);
        Assert.assertEquals(actual.getUserId(), validMessage.getUserId());
        Assert.assertEquals(actual.getGroupId(), validMessage.getGroupId());
        Assert.assertEquals(actual.getChatContent(), validMessage.getChatContent());
    }

    // SAD CREATE MESSAGE
    // Invalid user_id
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid User ID")
    public void cannotHaveUserIdLessThanOne() {
        Mockito.when(mockchatDAO.createMessage(invalidUserId)).thenThrow(new InvalidInputException("Invalid User ID"));
        mockchatService.serviceCreateMessageObject(invalidUserId);
    }

    // Invalid group_id
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Group ID")
    public void cannotHaveGroupIdLessThanZero() {
        Mockito.when(mockchatDAO.createMessage(invalidGroupId)).thenThrow(new InvalidInputException("Invalid Group ID"));
        mockchatService.serviceCreateMessageObject(invalidGroupId);
    }

    // Empty content
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Chat Content")
    public void cannotHaveBlankChatContent() {
        Mockito.when(mockchatDAO.createMessage(emptyChatContent)).thenThrow(new InvalidInputException("Invalid Chat Content"));
        mockchatService.serviceCreateMessageObject(emptyChatContent);
    }

    // Long content
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Long Content")
    public void cannotHaveChatContentLongerThan300() {
        Mockito.when(mockchatDAO.createMessage(longChatContent)).thenThrow(new InvalidInputException("Long Content"));
        mockchatService.serviceCreateMessageObject(longChatContent);
    }

    // GROUP MESSAGE HISTORY
    @Test
    public void getMessageHistoryByGroupId(){
        Mockito.when(mockchatDAO.getMessageHistory(1)).thenReturn(newList);
        ArrayList<ChatMessage> actual = mockchatService.serviceGetMessageHistory(1);
        Assert.assertEquals(actual, newList);
    }

    // SAD GROUP MESSAGE HISTORY
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input Exception")
    public void cannotGetMessageHistoryWithGroupIdLessThanOne(){
        Mockito.when(mockchatDAO.getMessageHistory(-1)).thenThrow(new InvalidInputException());
        mockchatService.serviceGetMessageHistory(-1);
    }

    // MESSAGE HISTORY
    @Test
    public void getAllMessageHistory(){
        Mockito.when(mockchatDAO.getMessageHistory()).thenReturn(newList);
        ArrayList<ChatMessage> actual = mockchatService.serviceGetMessageHistory();
        Assert.assertEquals(actual, newList);
    }
}
