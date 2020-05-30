/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.BarbaroChat.service;

import com.ericknavarro.BarbaroChat.model.ChatRoomUser;
import java.util.List;

/**
 *
 * @author ericknavarro
 */
public interface ChatRoomService {
    
    void joinChatRoom(ChatRoomUser user);
    
    void leaveChatRoom(ChatRoomUser user);
    
    void notifyListConnectedChatRoomUsers();
    
    List<ChatRoomUser> findAllChatRoomUsers();
}
