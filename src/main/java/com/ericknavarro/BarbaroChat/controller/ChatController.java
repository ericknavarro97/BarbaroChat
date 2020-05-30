/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.BarbaroChat.controller;

import com.ericknavarro.BarbaroChat.model.ChatRoomUser;
import com.ericknavarro.BarbaroChat.model.Message;
import com.ericknavarro.BarbaroChat.service.ChatRoomService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ericknavarro
 */

@Controller
@AllArgsConstructor
public class ChatController {
    
    private ChatRoomService chatRoomService;
    
    @MessageMapping("/publish") /*EndPonit - client - NO HTTP - pucl*/
    @SendTo("/topic/chatbarbaro") // todos los suscritos recibirán el msj (sala a publicar)
    public Message publishMessage(Message message){
        return message;
    }
    
    @SubscribeMapping("/topic/connected.users")
    public List<ChatRoomUser> listConnectedUsersOnSuscribe(){
        return chatRoomService.findAllChatRoomUsers();
    }
    
    @MessageMapping("/disconnect")
    public void sendMessageDisconnect(ChatRoomUser user){
        chatRoomService.leaveChatRoom(user);
    }
}
