package seniorcare.crudseniorcare.domain.chat;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class ChatNotification {
    private String id;
    private String senderId;
    private String recipientId;
    private String content;


    public ChatNotification(Long id, String senderId, String recipientId, String content) {
    }

    public ChatNotification(String id, String senderId, String recipientId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }
}
