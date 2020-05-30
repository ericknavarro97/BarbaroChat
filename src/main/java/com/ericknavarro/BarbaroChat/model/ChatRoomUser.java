/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.BarbaroChat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author ericknavarro
 */
@AllArgsConstructor
@RequiredArgsConstructor
public class ChatRoomUser {
    
    @Getter
    private final String username;
    
    @Getter
    private String userId;
}
