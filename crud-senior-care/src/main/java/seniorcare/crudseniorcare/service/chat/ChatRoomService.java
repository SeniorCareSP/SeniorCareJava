package seniorcare.crudseniorcare.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.chat.ChatRoom;
import seniorcare.crudseniorcare.domain.chat.repository.ChatRoomRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;


    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) {
        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists) {
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }

                    return  Optional.empty();
                });
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }


    public List<ChatRoom> getAllUniqueChatRoomsForUser(String userId) {
        List<ChatRoom> allChatRooms = chatRoomRepository.findBySenderIdOrRecipientId(userId, userId);

        // Usamos um conjunto para armazenar chatIds únicos
        Set<String> uniqueChatIds = new HashSet<>();
        List<ChatRoom> uniqueChatRooms = allChatRooms.stream()
                .filter(chatRoom -> uniqueChatIds.add(chatRoom.getChatId())) // Adiciona ao conjunto e filtra duplicatas
                .collect(Collectors.toList());

        return uniqueChatRooms;
    }
}
