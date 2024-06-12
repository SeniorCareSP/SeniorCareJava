package seniorcare.crudseniorcare.service.favorito.dto;

import lombok.Getter;
import lombok.Setter;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemFavoritoDto;
@Getter
@Setter
public class FavoritoListagemUsuarioDto {

    private Integer id;
    private UsuarioListagemCuidadorDto cuidadorFavoritado;
}
