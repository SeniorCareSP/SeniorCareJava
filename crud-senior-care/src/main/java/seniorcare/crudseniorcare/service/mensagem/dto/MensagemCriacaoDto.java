package seniorcare.crudseniorcare.service.mensagem.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class MensagemCriacaoDto {

    private String mensagem;
    private LocalDateTime dtEnvio;
}