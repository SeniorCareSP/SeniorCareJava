package seniorcare.crudseniorcare.dto;

import java.util.UUID;

public record IdosoRecordDTO(UUID idoso, String nome, boolean mobilidade, boolean lucido, String doencasCronicas, Boolean cuidadosMin) {
}
