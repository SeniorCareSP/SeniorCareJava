package seniorcare.crudseniorcare.service.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ChatCriacaoDto {
    private UUID responsavelId;
    private UUID cuidadorId;
    private UUID mensagemId;
}
