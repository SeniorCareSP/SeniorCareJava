package seniorcare.crudseniorcare.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.chat.ChatMessage;
import seniorcare.crudseniorcare.domain.chat.ChatNotification;
import seniorcare.crudseniorcare.domain.chat.ChatRoom;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.chat.ChatMessageService;
import seniorcare.crudseniorcare.service.chat.ChatRoomService;
import seniorcare.crudseniorcare.service.chat.dto.ChatMapper;
import seniorcare.crudseniorcare.service.chat.dto.ChatRoomListagem;
import seniorcare.crudseniorcare.service.chat.dto.ChatRoomWithLastMessageDTO;
import seniorcare.crudseniorcare.service.geolocalizacao.CoordenadaService;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class ChatController {
    private final UsuarioService usuarioService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @PostMapping("/chat")
    @CrossOrigin
    public ResponseEntity<ChatMessage> processMessage(@RequestBody ChatMessage chatMessage) {
        // Salvar a mensagem
        ChatMessage savedMsg = chatMessageService.save(chatMessage);

        // Criar a notificação com todos os dados relevantes
        ChatNotification notification = new ChatNotification(
                savedMsg.getId(),
                savedMsg.getSenderId(),     // ID do remetente
                savedMsg.getRecipientId(),  // ID do destinatário
                savedMsg.getContent()       // Conteúdo da mensagem
        );

        // Enviar a notificação ao usuário específico
        messagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getRecipientId()), "/queue/messages",
                notification
        );

        // Enviar a mensagem para o tópico
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getChatId(), notification); // Enviar notificação em vez de apenas o conteúdo

        return ResponseEntity.ok(savedMsg);
    }


    @GetMapping("/chats/{userId}")
    public ResponseEntity<List<ChatRoomListagem>> getAllChatsForUser(@PathVariable Integer userId) {
        List<ChatRoom> chatRooms = chatRoomService.getAllUniqueChatRoomsForUser(userId);
        List<ChatRoomListagem> chatRoomListagems = new java.util.ArrayList<>(List.of());

        for (ChatRoom chatRoom : chatRooms) {
            ChatRoomListagem dto = ChatMapper.toChatRoomDto(chatRoom);
            dto.setUsuario2(UsuarioMapper.toUsuarioListagemDto(usuarioService.byId(chatRoom.getRecipientId())));
            chatRoomListagems.add(dto);
        }

        if (chatRoomListagems.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(chatRoomListagems);
        }
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable Integer senderId, @PathVariable Integer recipientId) {
        List<ChatMessage> chatMessages = chatMessageService.findChatMessages(senderId, recipientId);

        for (ChatMessage chatMessage : chatMessages) {
            System.out.println(chatMessage.getContent());
        }
        System.out.println(chatMessages);

        if (chatMessages.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(chatMessages);
        }
    }

    // Novo endpoint para criar uma sala de chat vazia
    @PostMapping("/chat/empty")
    @CrossOrigin
    public ResponseEntity<ChatRoom> createEmptyChatRoom(@RequestParam Integer senderId, @RequestParam Integer recipientId) {
        ChatRoom chatRoom = chatRoomService.createEmptyChatRoom(senderId, recipientId);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/rooms-with-last-messages")
    public List<ChatRoomWithLastMessageDTO> getAllChatRoomsWithLastMessages() {
        return chatRoomService.getAllChatRoomsWithLastMessages();
    }

    @GetMapping("/with-last-messages/by-sender")
    public List<ChatRoomWithLastMessageDTO> getChatsWithLastMessagesBySenderId(@RequestParam Integer senderId) {

        List<ChatRoomWithLastMessageDTO> chatsWithLastMessagesBySenderId = chatRoomService.getChatsWithLastMessagesBySenderId(senderId);

        for (ChatRoomWithLastMessageDTO chatRoom : chatsWithLastMessagesBySenderId) {
            Usuario usuario = usuarioService.byId(chatRoom.getRecipientId());
            if (usuario != null) {
                chatRoom.setNome(usuario.getNome());
                chatRoom.setImagemUrl(usuario.getImagemUrl());
            }
        }

        return chatsWithLastMessagesBySenderId;

    }
    @GetMapping("/pegar-distancia")
    public ResponseEntity<Double> getDistancia(@RequestParam Integer senderId, @RequestParam Integer recipientId) {
        Double distancia = chatRoomService.getDistancia(senderId, recipientId);
        if (distancia != null) {
            return ResponseEntity.ok(distancia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
