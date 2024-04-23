package seniorcare.crudseniorcare.dto.comentario;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ComentarioCriacaoDto {

    private String conteudo;
    private double avaliacao;
    private UUID usuarioId;

}