package seniorcare.crudseniorcare.dto.ajuda;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import seniorcare.crudseniorcare.dto.agenda.AgendaMapper;
import seniorcare.crudseniorcare.model.Ajuda;

@Mapper
public interface AjudaMapper {

    AjudaMapper INSTANCE = Mappers.getMapper(AjudaMapper.class);

    Ajuda toEntityAjuda(AjudaCriacaoDto ajuda);

}   
