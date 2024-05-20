package seniorcare.crudseniorcare.service.usuario.dto.administrador;

import lombok.Data;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

@Data
public class AdministradorListagemDto extends UsuarioListagemDto {
    private String cargo;
}
