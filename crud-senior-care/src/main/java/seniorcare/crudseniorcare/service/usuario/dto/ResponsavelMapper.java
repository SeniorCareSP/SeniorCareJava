package seniorcare.crudseniorcare.service.usuario.dto;
import org.springframework.stereotype.Component;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaMapper;
import seniorcare.crudseniorcare.service.idoso.dto.IdosoMapper;
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
        responsavel.setIdiomas(IdiomaMapper.toListagemIdioma(dto.getIdiomas()));
        responsavel.setApresentacao(dto.getApresentacao());
        responsavel.setAgenda(AgendaMapper.toEntity(dto.getAgendas()));
        responsavel.setDtCadastro(LocalDate.now());
        responsavel.setIdosos(IdosoMapper.toIdosoList(dto.getIdosos()));
        responsavel.setEndereco(EnderecoMapper.toEndereco(dto.getEndereco()));
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
        dto.setAgenda(AgendaMapper.toListagemDto(responsavel.getAgenda()));
        dto.setIdiomas(IdiomaMapper.toListagemDtoList(responsavel.getIdiomas()));
        dto.setEndereco(EnderecoMapper.toEnderecoListagemDto(responsavel.getEndereco()));
        dto.setIdosos(IdosoMapper.toListagemDtoList(responsavel.getIdosos()));


        return dto;
    }

    public static List<UsuarioListagemResponsavelDto> toUsuarioListagemResponsavelDtoList(List<Responsavel> responsaveis) {
        return responsaveis.stream().map(ResponsavelMapper::toUsuarioListagemResponsavelDto).toList();

    }
}
