package seniorcare.crudseniorcare.dto.usuario.responsavel;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.dto.usuario.usuario.UsuarioCriacaoDto;

@Getter
@Setter
public class ResponsavelCriacaoDto extends UsuarioCriacaoDto {
    private double precoHora;
}