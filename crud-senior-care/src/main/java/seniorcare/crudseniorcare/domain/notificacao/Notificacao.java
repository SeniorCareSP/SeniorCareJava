package seniorcare.crudseniorcare.domain.notificacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Notificacao {
    private String mensagem;
    private String destinatario; // Pode ser um e-mail, telefone, etc.

    @Override
    public String toString() {
        return "Notificacao{mensagem='" + mensagem + "', destinatario='" + destinatario + "'}";
    }
}

