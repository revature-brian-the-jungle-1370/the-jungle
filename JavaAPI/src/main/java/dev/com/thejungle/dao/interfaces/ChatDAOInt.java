package dev.com.thejungle.dao.interfaces;

import dev.com.thejungle.entity.ChatMessage;

import java.util.ArrayList;

public interface ChatDAOInt {

    ChatMessage createMessage(ChatMessage chatMessage);

    void deleteMessage(ChatMessage chatMessage);

    ArrayList<ChatMessage> getMessageHistory(int groupId);

    ArrayList<ChatMessage> getMessageHistory();
}
