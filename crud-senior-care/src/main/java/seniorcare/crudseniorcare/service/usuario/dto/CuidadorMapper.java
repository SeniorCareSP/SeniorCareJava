package seniorcare.crudseniorcare.service.usuario.dto;

import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;

import java.util.List;

public interface CuidadorMapper {
    public static Cuidador toCuidador(UsuarioCriacaoCuidadorDto dto) {
        Cuidador cuidador = new Cuidador();
        // Mapear propriedades comuns a todas as subclasses de Usuario
        cuidador.setNome(dto.getNome());
        cuidador.setEmail(dto.getEmail());
        cuidador.setSenha(dto.getSenha());
        cuidador.setTelefone(dto.getTelefone());
        cuidador.setCpf(dto.getCpf());
        cuidador.setSexoBiologico(dto.getSexoBiologico());
        cuidador.setDtNascimento(dto.getDtNascimento());
        cuidador.setApresentacao(dto.getApresentacao());
        cuidador.setDtCadastro(dto.getDtCadastro());
        // Mapear propriedades específicas de Cuidador
        cuidador.setExperiencia(dto.getExperiencia());
        cuidador.setFaixaEtaria(dto.getFaixaEtaria());
        cuidador.setPrecoHora(dto.getPrecoHora());
        cuidador.setCaracteristicas(dto.getCaracteristicas());
        cuidador.setAjudas(dto.getAjudas());
        return cuidador;
    }

    public static UsuarioListagemCuidadorDto toUsuarioListagemCuidadorDto(Cuidador cuidador) {
        UsuarioListagemCuidadorDto dto = new UsuarioListagemCuidadorDto();
        dto.setIdUsuario(cuidador.getIdUsuario());
        dto.setNome(cuidador.getNome());
        dto.setEmail(cuidador.getEmail());
        dto.setPrecoHora(cuidador.getPrecoHora());
        dto.setCaracteristicas(cuidador.getCaracteristicas());
        dto.setAjudas(cuidador.getAjudas());
        return dto;
    }

    public static List<UsuarioListagemCuidadorDto> toUsuarioListagemCuidadorDtoList(List<Cuidador> cuidadores) {
        return cuidadores.stream().map(CuidadorMapper::toUsuarioListagemCuidadorDto).toList();
    }
}