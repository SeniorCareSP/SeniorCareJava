package seniorcare.crudseniorcare.dto.idioma;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class IdiomaCriacaoDto {

    private String idioma;
    private UUID usuarioId;
}
