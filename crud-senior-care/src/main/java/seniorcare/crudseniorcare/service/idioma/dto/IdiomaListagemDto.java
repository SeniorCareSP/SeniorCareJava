package seniorcare.crudseniorcare.service.idioma.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
public class IdiomaListagemDto {

    private UUID idIdioma;
    private String idioma;
    //private UsuarioDto usuario;
}
