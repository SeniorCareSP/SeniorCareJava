package seniorcare.crudseniorcare.service.favorito.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemFavoritoDto;

@Getter
@Setter
public class FavoritoListagemDto {
    private Integer id;
    private UsuarioListagemFavoritoDto usuarioFavoritando;
    private UsuarioListagemFavoritoDto usuarioFavoritado;
}
