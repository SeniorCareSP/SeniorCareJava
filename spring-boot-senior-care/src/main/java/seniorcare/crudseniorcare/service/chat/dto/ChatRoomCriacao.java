package seniorcare.crudseniorcare.service.chat.dto;

import lombok.Data;

@Data
public class ChatRoomCriacao {

    private String chatId;
    private String senderId;
    private String recipientId;
}
