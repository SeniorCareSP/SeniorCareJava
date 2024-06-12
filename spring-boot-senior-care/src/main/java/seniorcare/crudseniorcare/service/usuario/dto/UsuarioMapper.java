package seniorcare.crudseniorcare.service.usuario.dto;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.denuncia.dto.DenunciaMapper;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoMapper;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaMapper;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioCriacaoDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDenunciaDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemFavoritoDto;

import java.util.List;

@Component
public class UsuarioMapper {
    public static UsuarioListagemDto toUsuarioListagemDto(Usuario usuario) {
        if (usuario == null) return null;
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
        dto.setAgenda(AgendaMapper.toListagemDto(usuario.getAgenda()));
        dto.setEndereco(EnderecoMapper.toEnderecoListagemDto(usuario.getEndereco()));
        dto.setDenuncias(DenunciaMapper.toListagemUsuarioDtoList(usuario.getDenuncias()));
        return dto;
    }

    public static UsuarioListagemFavoritoDto toUsuarioListagemFavoritoDto(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioListagemFavoritoDto dto = new UsuarioListagemFavoritoDto();

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
        dto.setAgenda(AgendaMapper.toListagemDto(usuario.getAgenda()));
        dto.setEndereco(EnderecoMapper.toEnderecoListagemDto(usuario.getEndereco()));

        return dto;
    }

    public static UsuarioListagemDenunciaDto toUsuarioListagemDenunciaDto(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioListagemDenunciaDto dto = new UsuarioListagemDenunciaDto();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setSexoBiologico(usuario.getSexoBiologico());
        dto.setTipoDeUsuario(usuario.getTipoDeUsuario());
        dto.setDtNascimento(usuario.getDtNascimento());

        return dto;
    }


    public static UsuarioTokenDto of(Usuario usuario, String token){
        if (usuario == null ) return null;

        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getIdUsuario());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setTipoUsuario(usuario.getTipoDeUsuario());
        usuarioTokenDto.setNome(usuario.getNome());

        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

}

