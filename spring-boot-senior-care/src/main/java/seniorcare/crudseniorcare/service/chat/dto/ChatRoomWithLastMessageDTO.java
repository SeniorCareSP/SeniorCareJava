package seniorcare.crudseniorcare.service.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChatRoomWithLastMessageDTO {
    private Integer chatRoomId;
    private String chatId;
    private Integer senderId;
    private Integer recipientId;
    private Integer messageId;
    private String content;
    private Date timestamp;
    private String nome;
    private String imagemUrl;
}
