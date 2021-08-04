package com.ssafy.chatroom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessage {
	
	public enum MessageType {
	    JOIN, CHAT
	}
	
    private String roomId;	//방번호
    private String writer;
    private String message;
    private MessageType type;
}