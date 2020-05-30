/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.BarbaroChat.listener;

import com.ericknavarro.BarbaroChat.model.ChatRoomUser;
import com.ericknavarro.BarbaroChat.service.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 *
 * @author ericknavarro
 */
@Component
@AllArgsConstructor
public class WebSocketEvents {
    
    private final ChatRoomService chatRoomService;
    
    @EventListener
    private void handleSessionConnected(SessionConnectEvent event){
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        
        String chatRoomUsername = headers.getNativeHeader("chatroomuser").get(0);
        
        //String UserId = event.getUser().getName();
        
        ChatRoomUser chatRoomUser = new ChatRoomUser(chatRoomUsername);
        
        chatRoomService.joinChatRoom(chatRoomUser);
        
    }
    
    //@EventListener
    private void handleSessionDisconnected(SessionDisconnectEvent event){
        
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        
        String chatRoomUsername = headers.getNativeHeader("chatroomuser").get(0);
        
        ChatRoomUser chatRoomUser = new ChatRoomUser(chatRoomUsername);
        
        chatRoomService.leaveChatRoom(chatRoomUser);
    }
    
}
