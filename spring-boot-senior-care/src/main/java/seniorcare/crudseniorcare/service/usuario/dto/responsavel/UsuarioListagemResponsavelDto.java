package seniorcare.crudseniorcare.service.usuario.dto.responsavel;

import lombok.Data;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoListagemUsuarioDto;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoListagemDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;

@Data

public class UsuarioListagemResponsavelDto extends UsuarioListagemDto {

    private List<IdosoListagemDto> idosos;
    private List<FavoritoListagemUsuarioDto> favoritos;
}
