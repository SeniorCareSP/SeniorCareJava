package seniorcare.crudseniorcare.dto.agenda;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import seniorcare.crudseniorcare.model.Agenda;

@Mapper
public interface AgendaMapper {

    AgendaMapper INSTANCE = Mappers.getMapper(AgendaMapper.class);


    Agenda toEntityAgenda(AgendaCriacaoDto agenda);


}
