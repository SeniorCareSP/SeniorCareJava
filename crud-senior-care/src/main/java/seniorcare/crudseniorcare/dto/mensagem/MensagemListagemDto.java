package seniorcare.crudseniorcare.dto.mensagem;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MensagemListagemDto {

    private UUID idMensagem;
    private String mensagem;
    private LocalDateTime dtEnvio;
}
