/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.BarbaroChat.service.impl;

import com.ericknavarro.BarbaroChat.model.ChatRoomUser;
import com.ericknavarro.BarbaroChat.service.ChatRoomService;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author ericknavarro
 */
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    
    private final SimpMessagingTemplate messagingTemplate;
    
    private final ChatRoomDatabase chatRoomDatabase = new ChatRoomDatabase();

    @Override
    public void joinChatRoom(ChatRoomUser user) {
        chatRoomDatabase.addChatRoomUser(user);
    }

    @Override
    public void leaveChatRoom(ChatRoomUser user) {
        chatRoomDatabase.removeChatRoomUser(user);
    }

    @Override
    public void notifyListConnectedChatRoomUsers() {
        messagingTemplate.convertAndSend("/connected.users", findAllChatRoomUsers());
    }

    @Override
    public List<ChatRoomUser> findAllChatRoomUsers() {
        return chatRoomDatabase.getListConnectedUsers();
    }
    
    private static class ChatRoomDatabase {
        
        private List<ChatRoomUser> listConnectedUsers = new CopyOnWriteArrayList<>();
        
        private void addChatRoomUser(ChatRoomUser user){
            listConnectedUsers.add(user);
        }
        
        private void removeChatRoomUser(ChatRoomUser user){
            listConnectedUsers.removeIf(u -> u.getUsername().equals(user.getUsername()));
        }
        
        private List<ChatRoomUser> getListConnectedUsers(){
            return listConnectedUsers;
        }
    }
    
}
