package seniorcare.crudseniorcare.service.notificacao.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificacaoCriacaoDto {
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String usuarioEmail;
}