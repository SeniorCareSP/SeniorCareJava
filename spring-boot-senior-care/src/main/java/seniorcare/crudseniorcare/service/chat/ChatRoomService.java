package seniorcare.crudseniorcare.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.chat.ChatMessage;
import seniorcare.crudseniorcare.domain.chat.ChatRoom;
import seniorcare.crudseniorcare.domain.chat.repository.ChatRoomRepository;
import seniorcare.crudseniorcare.service.chat.dto.ChatRoomWithLastMessageDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomWithLastMessageDTO> getAllChatRoomsWithLastMessages() {
        return chatRoomRepository.findFirstChatAndLastMessage().stream()
                .map(result -> new ChatRoomWithLastMessageDTO(
                        (Integer) result[0],          // chatRoomId
                        (String) result[1],           // chatId
                        (Integer) result[2],          // senderId
                        (Integer) result[3],          // recipientId
                        (Integer) result[4],          // messageId
                        (String) result[5],           // content
                        (Date) result[6],              // timestamp
                        null
                ))
                .collect(Collectors.toList());
    }

    public List<ChatRoomWithLastMessageDTO> getChatsWithLastMessagesBySenderId(Integer senderId) {
        return chatRoomRepository.findChatsWithLastMessagesBySenderId(senderId).stream()
                .map(result -> new ChatRoomWithLastMessageDTO(
                        (Integer) result[0],          // chatRoomId
                        (String) result[1],           // chatId
                        (Integer) result[2],          // senderId
                        (Integer) result[3],          // recipientId
                        (Integer) result[4],          // messageId
                        (String) result[5],           // content
                        (Date) result[6],              // timestamp
                        null
                ))
                .collect(Collectors.toList());
    }
    public Optional<String> getChatRoomId(Integer senderId, Integer recipientId, boolean createNewRoomIfNotExists) {
        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        String chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChatId(Integer senderId, Integer recipientId) {
        String chatId = String.format("%d_%d", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }

    public List<ChatRoom> getAllUniqueChatRoomsForUser(Integer userId) {
        return chatRoomRepository.findBySenderId(userId);
    }

    // Método para criar uma sala de chat vazia
    public ChatRoom createEmptyChatRoom(Integer senderId, Integer recipientId) {
        String chatId = createChatId(senderId, recipientId);
        return chatRoomRepository.findByChatId(chatId)
                .orElseThrow(() -> new IllegalStateException("Erro ao criar a sala de chat"));
    }
}
