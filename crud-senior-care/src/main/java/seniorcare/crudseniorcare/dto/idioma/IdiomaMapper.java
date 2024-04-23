package seniorcare.crudseniorcare.dto.idioma;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.model.Idioma;

@Mapper
public interface IdiomaMapper {

    Idioma toEntity(IdiomaCriacaoDto dto);

    IdiomaListagemDto toDto(Idioma entity);
}
