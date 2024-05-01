package seniorcare.crudseniorcare.service.idioma.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class IdiomaListagemDto {

    private UUID idIdioma;
    private String idioma;
    private UUID usuario;
}