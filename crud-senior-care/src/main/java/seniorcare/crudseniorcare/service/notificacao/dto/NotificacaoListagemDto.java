package seniorcare.crudseniorcare.service.notificacao.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificacaoListagemDto {
    private UUID id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private UUID usuarioId;
}
