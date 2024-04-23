package seniorcare.crudseniorcare.dto.mensagem;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.model.Mensagem;

@Mapper
public interface MensagemMapper {

    Mensagem toEntity(MensagemCriacaoDto dto);

    MensagemListagemDto toDto(Mensagem entity);
}

