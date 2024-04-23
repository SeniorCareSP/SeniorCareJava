package seniorcare.crudseniorcare.dto.endereco;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.model.Endereco;

@Mapper
public interface EnderecoMapper {

    Endereco toEntity(EnderecoCriacaoDto dto);

    EnderecoListagemDto toDto(Endereco entity);
}