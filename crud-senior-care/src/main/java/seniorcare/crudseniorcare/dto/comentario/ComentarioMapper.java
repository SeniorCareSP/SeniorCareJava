package seniorcare.crudseniorcare.dto.comentario;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.model.Comentario;
@Mapper
public interface ComentarioMapper {

    Comentario toEntity(ComentarioCriacaoDto dto);

    ComentarioListagemDto toDto(Comentario entity);
}
