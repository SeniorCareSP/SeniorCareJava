package seniorcare.crudseniorcare.service.usuario.dto;
import org.springframework.stereotype.Component;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.time.LocalDate;
import java.util.List;

@Component
public class ResponsavelMapper {
    public static Responsavel toResponsavel(UsuarioCriacaoResponsavelDto dto) {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome(dto.getNome());
        responsavel.setEmail(dto.getEmail());
        responsavel.setSenha(dto.getSenha());
        responsavel.setTelefone(dto.getTelefone());
        responsavel.setCpf(dto.getCpf());
        responsavel.setTipoDeUsuario(dto.getTipoDeUsuario());
        responsavel.setSexoBiologico(dto.getSexoBiologico());
        responsavel.setDtNascimento(dto.getDtNascimento());
        responsavel.setApresentacao(dto.getApresentacao());
        responsavel.setDtCadastro(LocalDate.now());
        responsavel.setPrecoHora(dto.getPrecoHora());
        responsavel.setIdosos(dto.getIdosos());
        responsavel.setEndereco(dto.getEndereco());
        return responsavel;
    }

    public static UsuarioListagemResponsavelDto toUsuarioListagemResponsavelDto(Responsavel responsavel) {
        UsuarioListagemResponsavelDto dto = new UsuarioListagemResponsavelDto();

        dto.setIdUsuario(responsavel.getIdUsuario());
        dto.setNome(responsavel.getNome());
        dto.setEmail(responsavel.getEmail());
        dto.setTelefone(responsavel.getTelefone());
        dto.setCpf(responsavel.getCpf());
        dto.setSexoBiologico(responsavel.getSexoBiologico());
        dto.setDtNascimento(responsavel.getDtNascimento());
        dto.setTipoDeUsuario(responsavel.getTipoDeUsuario());
        dto.setApresentacao(responsavel.getApresentacao());
        dto.setDtCadastro(responsavel.getDtCadastro());
        dto.setAgendas(responsavel.getAgendas());
        dto.setIdiomas(responsavel.getIdiomas());
        dto.setPrecoHora(responsavel.getPrecoHora());
        dto.setEndereco(responsavel.getEndereco());



        return dto;
    }

    public static List<UsuarioListagemResponsavelDto> toUsuarioListagemResponsavelDtoList(List<Responsavel> responsaveis) {
        return responsaveis.stream().map(ResponsavelMapper::toUsuarioListagemResponsavelDto).toList();

    }
}
