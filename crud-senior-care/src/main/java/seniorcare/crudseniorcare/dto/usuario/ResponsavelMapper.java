package seniorcare.crudseniorcare.dto.usuario;


import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.dto.usuario.responsavel.ResponsavelCriacaoDto;
import seniorcare.crudseniorcare.dto.usuario.responsavel.ResponsavelListagemDto;
import seniorcare.crudseniorcare.model.usuario.Responsavel;

@Mapper
public interface ResponsavelMapper {

    Responsavel toEntity(ResponsavelCriacaoDto dto);

    ResponsavelListagemDto toDto(Responsavel entity);
}