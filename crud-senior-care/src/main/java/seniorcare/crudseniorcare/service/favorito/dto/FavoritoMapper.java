package seniorcare.crudseniorcare.service.favorito.dto;

import seniorcare.crudseniorcare.domain.favorito.Favorito;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FavoritoMapper {

    public static Favorito toFavorito(FavoritoCriacaoDto dto) {
        if (dto == null) return null;
        Favorito favorito = new Favorito();
        favorito.setUsuarios(dto.getUsuarios());

        return favorito;
    }

    public static FavoritoListagemDto toFavoritoDto(Favorito favorito) {
        if (favorito == null) return null;
        FavoritoListagemDto dto = new FavoritoListagemDto();


        dto.setId(favorito.getId());
        dto.setUsuarios(favorito.getUsuarios());

        return dto;
    }

    public static List<FavoritoListagemDto> toListagemDtoList(List<Favorito> favoritoList) {
        if (favoritoList == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se a lista de idiomas for nula
        }
        return favoritoList.stream()
                .map(FavoritoMapper::toFavoritoDto)
                .collect(Collectors.toList());
    }

    public static List<Favorito> toListagemFavorito(List<FavoritoCriacaoDto> favoritoCriacaoDtoList) {
        if (favoritoCriacaoDtoList == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se a lista de idiomas for nula
        }
        return favoritoCriacaoDtoList.stream()
                .map(FavoritoMapper::toFavorito)
                .collect(Collectors.toList());
    }



}
