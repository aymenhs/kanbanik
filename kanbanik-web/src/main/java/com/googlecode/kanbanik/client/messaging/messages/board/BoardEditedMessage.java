package com.googlecode.kanbanik.client.messaging.messages.board;

import com.googlecode.kanbanik.client.api.Dtos;
import com.googlecode.kanbanik.client.messaging.BaseMessage;

public class BoardEditedMessage extends BaseMessage<Dtos.BoardDto> {

	public BoardEditedMessage(Dtos.BoardDto payload, Object source) {
		super(payload, source);
	}

}
