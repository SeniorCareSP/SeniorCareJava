package seniorcare.crudseniorcare.dto.usuario.responsavel;

import lombok.Getter;
import lombok.Setter;

import seniorcare.crudseniorcare.dto.usuario.usuario.UsuarioListagemDto;

@Getter
@Setter
public class ResponsavelListagemDto extends UsuarioListagemDto {
    private double precoHora;
}
