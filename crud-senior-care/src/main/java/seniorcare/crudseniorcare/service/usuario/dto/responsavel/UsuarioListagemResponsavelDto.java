package seniorcare.crudseniorcare.service.usuario.dto.responsavel;

import lombok.Data;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;

@Data

public class UsuarioListagemResponsavelDto extends UsuarioListagemDto {
    private double precoHora;
    private List<Idoso> idosos;
}
