package seniorcare.crudseniorcare.service.agenda.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.UUID;
@Data
public class AgendaListagemDto {
        private Integer idAgenda;
        private boolean[][] disponibilidade;
}
