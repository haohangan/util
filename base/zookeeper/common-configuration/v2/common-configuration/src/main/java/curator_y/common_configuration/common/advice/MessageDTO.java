package curator_y.common_configuration.common.advice;

import java.util.Set;

public class MessageDTO {
	private Set<String> messages;
	private MessageType type;

	public MessageDTO() {
		super();
	}

	public MessageDTO(Set<String> messages, MessageType type) {
		super();
		this.messages = messages;
		this.type = type;
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
