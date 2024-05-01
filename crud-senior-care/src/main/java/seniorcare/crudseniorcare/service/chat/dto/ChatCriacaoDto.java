package seniorcare.crudseniorcare.service.chat.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.mensagem.Mensagem;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;

import java.util.UUID;
@Getter
@Setter
public class ChatCriacaoDto {
    private Responsavel responsavel;
    private Cuidador cuidador;
    private Mensagem mensagem;
}
