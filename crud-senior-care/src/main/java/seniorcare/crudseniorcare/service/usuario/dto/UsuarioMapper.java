package seniorcare.crudseniorcare.service.usuario.dto;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;

@Component
public class UsuarioMapper {
        public UsuarioListagemDto toUsuarioListagemDto (Usuario usuario){
            UsuarioListagemDto dto = new UsuarioListagemDto();
            dto.setIdUsuario(usuario.getIdUsuario());
            dto.setNome(usuario.getNome());
            dto.setEmail(usuario.getEmail());
            dto.setTelefone(usuario.getTelefone());
            dto.setCpf(usuario.getCpf());
            dto.setSexoBiologico(usuario.getSexoBiologico());
            dto.setTipoDeUsuario(usuario.getTipoDeUsuario());
            dto.setDtNascimento(usuario.getDtNascimento());
            dto.setApresentacao(usuario.getApresentacao());
            dto.setDtCadastro(usuario.getDtCadastro());
            dto.setAgendas(usuario.getAgendas());
            dto.setIdiomas(usuario.getIdiomas());
            dto.setEndereco(usuario.getEndereco());
            return dto;
        }


        public static UsuarioTokenDto of(Usuario usuario, String token){
            UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

            usuarioTokenDto.setUserId(usuario.getIdUsuario());
            usuarioTokenDto.setEmail(usuario.getEmail());
            usuarioTokenDto.setTipoUsuario(usuario.getTipoDeUsuario());
            usuarioTokenDto.setNome(usuario.getNome());

            usuarioTokenDto.setToken(token);

            return usuarioTokenDto;
        }

}

