package seniorcare.crudseniorcare.service.chat.dto;

import seniorcare.crudseniorcare.domain.chat.ChatRoom;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

public class ChatMapper {



    public static ChatRoomListagem toChatRoomDto(ChatRoom chatRoom) {
        ChatRoomListagem dto = new ChatRoomListagem();

        dto.setId(chatRoom.getId());
        dto.setChatId(chatRoom.getChatId());
        dto.setUsuario(chatRoom.getSenderId());

        return dto;
    }

    public static ChatRoom toEntity(ChatRoomCriacao chatRoomCriacao) {
        ChatRoom dto = new ChatRoom();
        dto.setChatId(chatRoomCriacao.getChatId());
        dto.setSenderId(chatRoomCriacao.getSenderId());
        dto.setRecipientId(chatRoomCriacao.getRecipientId());

        return dto;
    }

    public static List<ChatRoomListagem> toDtoList(List<ChatRoom> chatRoomList) {
        return chatRoomList.stream()
                .map(ChatMapper::toChatRoomDto)
                .collect(Collectors.toList());
    }
}
