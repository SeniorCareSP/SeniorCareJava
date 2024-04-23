package seniorcare.crudseniorcare.dto.usuario;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.dto.usuario.usuario.UsuarioCriacaoDto;
import seniorcare.crudseniorcare.dto.usuario.usuario.UsuarioListagemDto;
import seniorcare.crudseniorcare.model.usuario.Usuario;

@Mapper
public interface UsuarioMapper {

    Usuario toEntity(UsuarioCriacaoDto dto);

    UsuarioListagemDto toDto(Usuario entity);
}
