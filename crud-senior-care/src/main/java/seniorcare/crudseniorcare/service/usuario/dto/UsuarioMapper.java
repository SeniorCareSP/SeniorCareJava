package seniorcare.crudseniorcare.service.usuario.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import seniorcare.crudseniorcare.model.usuario.Cuidador;
import seniorcare.crudseniorcare.model.usuario.Responsavel;
import seniorcare.crudseniorcare.model.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;

import java.util.List;

@Mapper
public interface UsuarioMapper  {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Cuidador toEntityCuidador(UsuarioCriacaoCuidadorDto dto);

    @Mapping(target = "senha", ignore = true)
    UsuarioListagemCuidadorDto toDtoCuidador(Cuidador cuidador);


    @Mapping(target = "senha", ignore = true)
    List<UsuarioListagemCuidadorDto> toDtoCuidadorList(List<Cuidador> cuidadores);


    Responsavel toEntityResponsavel(UsuarioCriacaoResponsavelDto dto);

    @Mapping(target = "senha", ignore = true)
    UsuarioListagemCuidadorDto toDtoResponsavel(Responsavel responsavel);

    @Mapping(target = "senha", ignore = true)
    List<UsuarioListagemCuidadorDto> toDtoResponsavelList(Responsavel responsavel);

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getIdUsuario());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

}
