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
import seniorcare.crudseniorcare.service.chat.ChatMessageService;
import seniorcare.crudseniorcare.service.chat.ChatRoomService;
import seniorcare.crudseniorcare.service.chat.dto.ChatMapper;
import seniorcare.crudseniorcare.service.chat.dto.ChatRoomListagem;
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
        public void processMessage(@RequestBody ChatMessage chatMessage) {
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getSenderId(),
                        savedMsg.getRecipientId(),
                        savedMsg.getContent()
                )
        );
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getChatId(), savedMsg.getContent());
    }


    @GetMapping("/chats/{userId}")
    public ResponseEntity<List<ChatRoomListagem>> getAllChatsForUser(@PathVariable String userId) {
        List<ChatRoom> chatRooms = chatRoomService.getAllUniqueChatRoomsForUser(userId);
        List<ChatRoomListagem> chatRoomListagems = new java.util.ArrayList<>(List.of());

        for (ChatRoom chatRoom : chatRooms) {
            ChatRoomListagem dto = ChatMapper.toChatRoomDto(chatRoom);
            int id = Integer.parseInt(chatRoom.getRecipientId());
            dto.setUsuario2(UsuarioMapper.toUsuarioListagemDto(usuarioService.byId(id)));
            chatRoomListagems.add(dto);
        }

        if (chatRoomListagems.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(chatRoomListagems);
        }
    }



    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
                                                              @PathVariable String recipientId) {


        List<ChatMessage> chatMessages = chatMessageService.findChatMessages(senderId, recipientId);

        for (ChatMessage chatMessages1 : chatMessages) {
            System.out.println(chatMessages1.getContent());
        }
        System.out.println(chatMessages);

        if (chatMessages.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(chatMessages);
        }
    }
}
