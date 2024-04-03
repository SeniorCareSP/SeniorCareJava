package seniorcare.crudseniorcare.dto;

import java.util.UUID;

public record AgendaRecordDTO(UUID idAgenda, String diaDaSemana, boolean periodo_manha, boolean periodo_tarde, boolean periodo_noite) {
}
