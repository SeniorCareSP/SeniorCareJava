package seniorcare.crudseniorcare.service.usuario.dto;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.service.ajuda.dto.AjudaMapper;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
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
        cuidador.setCpf(dto.getCpf());
        cuidador.setSexoBiologico(dto.getSexoBiologico());
        cuidador.setTipoDeUsuario(dto.getTipoDeUsuario());
        cuidador.setDtNascimento(dto.getDtNascimento());
        cuidador.setApresentacao(dto.getApresentacao());
        cuidador.setDtCadastro(LocalDate.now());
        cuidador.setExperiencia(dto.getExperiencia());
        cuidador.setFaixaEtaria(dto.getFaixaEtaria());
        cuidador.setPrecoHora(dto.getPrecoHora());
        cuidador.setCaracteristicas(dto.getCaracteristicas());
        cuidador.setAjudas(dto.getAjudas());


        cuidador.setEndereco(dto.getEndereco());

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
        dto.setDtNascimento(cuidador.getDtNascimento());
        dto.setApresentacao(cuidador.getApresentacao());
        dto.setDtCadastro(cuidador.getDtCadastro());
        dto.setAgendas(cuidador.getAgendas());
        dto.setIdiomas(cuidador.getIdiomas());
        dto.setPrecoHora(cuidador.getPrecoHora());
        dto.setCaracteristicas(cuidador.getCaracteristicas());
        dto.setAjudas(cuidador.getAjudas());

        dto.setEndereco(cuidador.getEndereco());

        return dto;
    }

    public static List<UsuarioListagemCuidadorDto> toUsuarioListagemCuidadorDtoList(List<Cuidador> cuidadores) {
        return cuidadores.stream().map(CuidadorMapper::toUsuarioListagemCuidadorDto).toList();
    }
}
