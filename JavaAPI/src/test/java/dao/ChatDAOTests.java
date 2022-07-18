package dao;

import dev.com.thejungle.customexception.*;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.entity.ChatMessage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ChatDAOTests {
    ChatDAO chatDAO = new ChatDAO();
        
    // TEST FOR CHAT MESSAGE CREATION
    @Test
    public void testCreateMessage(){
        ChatMessage validMessage = new ChatMessage(13, 10000, "Valid Contents");
        ChatMessage actual = chatDAO.createMessage(validMessage);
        Assert.assertEquals(actual.getUserId(), validMessage.getUserId());
        Assert.assertEquals(actual.getGroupId(), validMessage.getGroupId());
        Assert.assertEquals(actual.getChatContent(), validMessage.getChatContent());
        chatDAO.deleteMessage(actual);

        validMessage = new ChatMessage();
        validMessage.setUserId(13);
        validMessage.setChatContent("Valid Content");
        actual = chatDAO.createMessage(validMessage);
        Assert.assertEquals(actual.getUserId(), validMessage.getUserId());
        Assert.assertEquals(actual.getGroupId(), 0);
        Assert.assertEquals(actual.getChatContent(), validMessage.getChatContent());
        chatDAO.deleteMessage(actual);
    }

    // TEST GROUP MESSAGE HISTORY
    @Test
    public void testGetMessageHistoryByGroupId(){
        ChatMessage validMessage = new ChatMessage(13, 10000, "Valid Contents.");
        ChatMessage msg = chatDAO.createMessage(validMessage);
        ArrayList<ChatMessage> actual = chatDAO.getMessageHistory(10000);
        Assert.assertTrue(actual.size() > 0);
        chatDAO.deleteMessage(msg);
    }

    // TEST SAD GROUP MESSAGE HISTORY
    @Test
    public void testGetMessageHistoryByInvalidGroupId(){
        ArrayList<ChatMessage> actual = chatDAO.getMessageHistory(-1);
        Assert.assertTrue(actual.size() == 0);
    }

    // TEST MESSAGE HISTORY
    @Test
    public void testGetMessageHistoryEmpty(){
        ArrayList<ChatMessage> actual = chatDAO.getMessageHistory();
        Assert.assertTrue(actual instanceof ArrayList);
    }

    @Test
    public void testGetMessageHistoryNotEmpty(){
        ChatMessage validMessage = new ChatMessage();
        validMessage.setUserId(13);
        validMessage.setChatContent("Valid Content");
        ChatMessage result = chatDAO.createMessage(validMessage);
        ArrayList<ChatMessage> actual = chatDAO.getMessageHistory();
        Assert.assertTrue(actual.size() > 0);
        chatDAO.deleteMessage(result);
    }
}
