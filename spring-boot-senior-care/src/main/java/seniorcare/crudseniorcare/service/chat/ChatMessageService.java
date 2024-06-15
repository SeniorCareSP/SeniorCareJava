package seniorcare.crudseniorcare.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.chat.ChatMessage;
import seniorcare.crudseniorcare.domain.chat.repository.ChatMessageRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        validateChatMessage(chatMessage);

        // Obtém o chatId como String
        String chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possível encontrar ou criar a sala de chat"));

        chatMessage.setChatId(chatId); // Define o chatId como String
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(Integer senderId, Integer recipientId) {
        Objects.requireNonNull(senderId, "O ID do remetente não pode ser nulo");
        Objects.requireNonNull(recipientId, "O ID do destinatário não pode ser nulo");

        // Obtém o chatId como String
        String chatId = chatRoomService.getChatRoomId(senderId, recipientId, false)
                .orElseThrow(() -> new IllegalArgumentException("Sala de chat não encontrada"));

        return repository.findByChatId(chatId); // Busca por chatId como String
    }

    private void validateChatMessage(ChatMessage chatMessage) {
        Objects.requireNonNull(chatMessage, "A mensagem de chat não pode ser nula");
        Objects.requireNonNull(chatMessage.getSenderId(), "O ID do remetente da mensagem não pode ser nulo");
        Objects.requireNonNull(chatMessage.getRecipientId(), "O ID do destinatário da mensagem não pode ser nulo");
        Objects.requireNonNull(chatMessage.getContent(), "O conteúdo da mensagem não pode ser nulo");
    }
}
