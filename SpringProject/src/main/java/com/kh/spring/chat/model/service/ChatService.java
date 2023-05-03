package com.kh.spring.chat.model.service;


import java.util.List;

import com.kh.spring.chat.model.vo.ChatRoom;

public interface ChatService {

	public List<ChatRoom> selectChatRoomList();
}
