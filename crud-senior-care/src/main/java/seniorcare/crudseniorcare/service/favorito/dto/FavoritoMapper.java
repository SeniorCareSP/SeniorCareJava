package seniorcare.crudseniorcare.service.favorito.dto;

import seniorcare.crudseniorcare.domain.favorito.Favorito;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoListagemDto;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoMapper;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FavoritoMapper {



    public static FavoritoListagemDto toFavoritoDto(Favorito favorito) {
        if (favorito == null) return null;
        FavoritoListagemDto dto = new FavoritoListagemDto();
        dto.setId(favorito.getId());
        dto.setUsuarioFavoritando(UsuarioMapper.toUsuarioListagemFavoritoDto(favorito.getUsuario()));
        dto.setUsuarioFavoritado(UsuarioMapper.toUsuarioListagemFavoritoDto(favorito.getUsuarioFavoritado()));

        return dto;
    }


    public static FavoritoListagemUsuarioDto toFavoritoListagemDto(Favorito favorito) {
        if (favorito == null) return null;
        FavoritoListagemUsuarioDto dto = new FavoritoListagemUsuarioDto();
        dto.setId(favorito.getId());
        dto.setUsuarioFavoritado(UsuarioMapper.toUsuarioListagemFavoritoDto(favorito.getUsuarioFavoritado()));

        return dto;
    }


    public static List<FavoritoListagemDto> toListagemDtoList(List<Favorito> favoritoList) {
        if (favoritoList == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se a lista de favoritos for nula
        }
        return favoritoList.stream()
                .map(FavoritoMapper::toFavoritoDto)
                .collect(Collectors.toList());
    }

//    public static List<Favorito> toListagemFavorito(List<FavoritoCriacaoDto> favoritoCriacaoDtoList) {
//        if (favoritoCriacaoDtoList == null) {
//            return Collections.emptyList(); // Retorna uma lista vazia se a lista de favoritos for nula
//        }
//        return favoritoCriacaoDtoList.stream()
//                .map(FavoritoMapper::toFavorito)
//                .collect(Collectors.toList());
//    }

    public static List<FavoritoListagemUsuarioDto> toListagemUsuarioDtoList(List<Favorito> favoritos) {
        if (favoritos == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se a lista de idosos for nula
        }
        return favoritos.stream()
                .map(FavoritoMapper::toFavoritoListagemDto)
                .collect(Collectors.toList());
    }
}
