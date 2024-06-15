package seniorcare.crudseniorcare.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.chat.ChatRoom;
import seniorcare.crudseniorcare.domain.chat.repository.ChatRoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

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
}   
