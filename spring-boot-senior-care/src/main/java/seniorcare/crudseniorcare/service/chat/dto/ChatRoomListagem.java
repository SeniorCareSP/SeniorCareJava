package seniorcare.crudseniorcare.service.chat.dto;

import lombok.Data;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

@Data
public class ChatRoomListagem {
    private Integer id;
    private String chatId;
    private String usuario;
    private UsuarioListagemDto usuario2;
}
