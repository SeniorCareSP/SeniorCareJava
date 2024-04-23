package seniorcare.crudseniorcare.dto.usuario;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.dto.usuario.cuidador.CuidadorCriacaoDto;
import seniorcare.crudseniorcare.dto.usuario.cuidador.CuidadorListagemDto;
import seniorcare.crudseniorcare.model.usuario.Cuidador;

@Mapper
public interface CuidadorMapper {

    Cuidador toEntity(CuidadorCriacaoDto dto);

    CuidadorListagemDto toDto(Cuidador entity);
}