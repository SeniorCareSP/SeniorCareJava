package seniorcare.crudseniorcare.service.usuario.dto;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.ajuda.dto.AjudaMapper;
import seniorcare.crudseniorcare.service.caracteristica.dto.CaracteristicaMapper;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.favorito.dto.FavoritoMapper;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;

import java.time.LocalDate;
import java.util.List;
@Component

public class CuidadorMapper {
    public static Cuidador toCuidador(UsuarioCriacaoCuidadorDto dto) {
        Cuidador cuidador = new Cuidador();

        cuidador.setNome(dto.getNome());
        cuidador.setEmail(dto.getEmail());
        cuidador.setSenha(dto.getSenha());
        cuidador.setTelefone(dto.getTelefone());
        cuidador.setImagemUrl(dto.getImagemUrl());
        cuidador.setCpf(dto.getCpf());
        cuidador.setSexoBiologico(dto.getSexoBiologico());
        cuidador.setTipoDeUsuario(dto.getTipoDeUsuario());
        cuidador.setDtNascimento(dto.getDtNascimento());
        cuidador.setApresentacao(dto.getApresentacao());
        cuidador.setAgenda(AgendaMapper.toEntity(dto.getAgendas()));
        cuidador.setDtCadastro(LocalDate.now());
        cuidador.setIdiomas(IdiomaMapper.toListagemIdioma(dto.getIdiomas()));
        cuidador.setStatus(true);
        cuidador.setExperiencia(dto.getExperiencia());
        cuidador.setFaixaEtaria(dto.getFaixaEtaria());
        cuidador.setPrecoHora(dto.getPrecoHora());
        cuidador.setCaracteristicas(CaracteristicaMapper.toCaracteristicaListagemEntityList(dto.getCaracteristicas()));
        cuidador.setAjudas(AjudaMapper.toAjudaListagemEntityList(dto.getAjuda()));

        cuidador.setEndereco(EnderecoMapper.toEndereco(dto.getEndereco()));

        return cuidador;
    }

    public static UsuarioListagemCuidadorDto toUsuarioListagemCuidadorDto(Cuidador cuidador) {
        UsuarioListagemCuidadorDto dto = new UsuarioListagemCuidadorDto();
        dto.setIdUsuario(cuidador.getIdUsuario());
        dto.setNome(cuidador.getNome());
        dto.setEmail(cuidador.getEmail());
        dto.setTelefone(cuidador.getTelefone());
        dto.setCpf(cuidador.getCpf());
        dto.setSexoBiologico(cuidador.getSexoBiologico());
        dto.setTipoDeUsuario(cuidador.getTipoDeUsuario());
        dto.setImagemUrl(cuidador.getImagemUrl());
        dto.setDtNascimento(cuidador.getDtNascimento());
        dto.setApresentacao(cuidador.getApresentacao());
        dto.setExperiencia(cuidador.getExperiencia());
        dto.setFaixaEtaria(cuidador.getFaixaEtaria());
        dto.setStatus(cuidador.getStatus());
        dto.setDtCadastro(cuidador.getDtCadastro());
        dto.setAgenda(AgendaMapper.toListagemDto(cuidador.getAgenda()));
        dto.setIdiomas(IdiomaMapper.toListagemDtoList(cuidador.getIdiomas()));
        dto.setCaracteristicas(CaracteristicaMapper.toCaracteristicaListagemDtoList(cuidador.getCaracteristicas()));
        dto.setAjudas(AjudaMapper.toAjudaListagemDtoList(cuidador.getAjudas()));
        dto.setEndereco(EnderecoMapper.toEnderecoListagemDto(cuidador.getEndereco()));


        return dto;
    }



    public static List<UsuarioListagemCuidadorDto> toUsuarioListagemCuidadorDtoList(List<Cuidador> cuidadores) {
        return cuidadores.stream().map(CuidadorMapper::toUsuarioListagemCuidadorDto).toList();
    }
}
