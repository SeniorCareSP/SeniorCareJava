package seniorcare.crudseniorcare.dto.caracteristica;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.model.Caracteristica;

import java.util.List;

@Mapper
public interface CaracteristicaMapper {



    Caracteristica toEntity(CaracteristicaCriacaoDto dto);

    CaracteristicaListagemDto toDto(Caracteristica entity);

    List<CaracteristicaListagemDto> toDtoList(List<Caracteristica> entities);
}
