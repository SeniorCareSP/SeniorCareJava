package seniorcare.crudseniorcare.service.usuario.dto;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.util.List;

public interface ResponsavelMapper {
    public static Responsavel toResponsavel(UsuarioCriacaoResponsavelDto dto) {
        Responsavel responsavel = new Responsavel();
        // Mapear propriedades comuns a todas as subclasses de Usuario
        responsavel.setNome(dto.getNome());
        responsavel.setEmail(dto.getEmail());
        responsavel.setSenha(dto.getSenha());
        responsavel.setTelefone(dto.getTelefone());
        responsavel.setCpf(dto.getCpf());
        responsavel.setSexoBiologico(dto.getSexoBiologico());
        responsavel.setDtNascimento(dto.getDtNascimento());
        responsavel.setApresentacao(dto.getApresentacao());
        responsavel.setDtCadastro(dto.getDtCadastro());
        // Mapear propriedades específicas de Responsavel
        responsavel.setPrecoHora(dto.getPrecoHora());
        responsavel.setIdosos(dto.getIdosos());
        return responsavel;
    }

    public static UsuarioListagemResponsavelDto toUsuarioListagemResponsavelDto(Responsavel responsavel) {
        UsuarioListagemResponsavelDto dto = new UsuarioListagemResponsavelDto();
        // Mapear propriedades comuns a todas as subclasses de Usuario
        dto.setIdUsuario(responsavel.getIdUsuario());
        dto.setNome(responsavel.getNome());
        dto.setEmail(responsavel.getEmail());
        // Mapear propriedades específicas de Responsavel
        dto.setPrecoHora(responsavel.getPrecoHora());
        dto.setIdosos(responsavel.getIdosos());
        return dto;
    }

    public static List<UsuarioListagemResponsavelDto> toUsuarioListagemResponsavelDtoList(List<Responsavel> responsaveis) {
        return responsaveis.stream().map(ResponsavelMapper::toUsuarioListagemResponsavelDto).toList();

    }
}
