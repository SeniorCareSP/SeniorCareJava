package seniorcare.crudseniorcare.service.favorito.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemFavoritoDto;
@Getter
@Setter
public class FavoritoListagemUsuarioDto {

    private Integer id;
    private UsuarioListagemFavoritoDto usuarioFavoritado;
}
