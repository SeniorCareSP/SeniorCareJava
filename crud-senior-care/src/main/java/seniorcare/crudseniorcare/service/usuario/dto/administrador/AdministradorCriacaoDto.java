package seniorcare.crudseniorcare.service.usuario.dto.administrador;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioCriacaoDto;

@Data
public class AdministradorCriacaoDto extends UsuarioCriacaoDto {
    @NotBlank
    private String cargo;
}
