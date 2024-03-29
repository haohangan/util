package com.eva.advice;

import java.awt.TrayIcon.MessageType;
import java.util.Set;

public class MessageDTO {
	private Set<String> messages;
	private MessageType type;

	public MessageDTO() {
	}

	public MessageDTO(MessageType error, Set<String> msgs) {
		this.messages = msgs;
		this.type = error;
	}

	public Set<String> getMessages() {
		return messages;
	}

	public void setMessages(Set<String> messages) {
		this.messages = messages;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
}
