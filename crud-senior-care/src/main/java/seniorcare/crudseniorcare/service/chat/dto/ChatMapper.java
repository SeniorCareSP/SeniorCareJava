package seniorcare.crudseniorcare.service.chat.dto;


import seniorcare.crudseniorcare.domain.chat.Chat;
import seniorcare.crudseniorcare.service.chat.dto.ChatCriacaoDto;
import seniorcare.crudseniorcare.service.chat.dto.ChatListagemDto;

import java.util.UUID;

public class ChatMapper {

    public Chat toChat(ChatCriacaoDto dto) {
        Chat chat = new Chat();
        chat.setResponsavel(dto.getResponsavel());
        chat.setCuidador(dto.getCuidador());
        chat.setMensagem(dto.getMensagem());
        return chat;
    }

    public ChatListagemDto toChatListagemDto(Chat chat) {
        ChatListagemDto dto = new ChatListagemDto();
        dto.setIdChat(chat.getIdChat());
        dto.setResponsavel(chat.getResponsavel());
        dto.setCuidador(chat.getCuidador());
        dto.setMensagem(chat.getMensagem());
        return dto;
    }
}
