package seniorcare.crudseniorcare.service.favorito.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemFavoritoDto;

@Getter
@Setter
public class FavoritoListagemDto {
    private Integer id;

    private UsuarioListagemCuidadorDto cuidadorFavoritado;
}
