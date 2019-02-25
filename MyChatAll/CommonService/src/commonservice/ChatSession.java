/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class ChatSession implements Serializable {

    UUID chatSessionID;
    ArrayList<Message> chatHistory;
    ArrayList<User> users;

    public ChatSession() {
        chatSessionID = UUID.randomUUID();
        users = new ArrayList<User>();
        chatHistory = new ArrayList<Message>();
      
    }

    public UUID getChatSessionID() {
        return chatSessionID;
    }

    public ArrayList<Message> getChatHistory() {
        return chatHistory;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    
    

}
