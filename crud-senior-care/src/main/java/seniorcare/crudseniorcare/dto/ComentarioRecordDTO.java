package seniorcare.crudseniorcare.dto;

import java.util.UUID;

public record ComentarioRecordDTO(UUID idComentario, String conteudo, double avaliacao) {
}
