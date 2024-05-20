package seniorcare.crudseniorcare.service.usuario.dto;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.service.usuario.dto.administrador.AdministradorCriacaoDto;
import seniorcare.crudseniorcare.service.usuario.dto.administrador.AdministradorListagemDto;
import java.util.List;

@Mapper
public class AdministradorMapper {
    public static Administrador toAdministrador(AdministradorCriacaoDto dto) {

        Administrador administrador = new Administrador();

        administrador.setNome(dto.getNome());
        administrador.setEmail(dto.getEmail());
        administrador.setTelefone(dto.getTelefone());
        administrador.setCpf(dto.getCpf());
        administrador.setSexoBiologico(dto.getSexoBiologico());
        administrador.setDtNascimento(dto.getDtNascimento());
        administrador.setTipoDeUsuario(dto.getTipoDeUsuario());
        administrador.setApresentacao(dto.getApresentacao());
        administrador.setDtCadastro(dto.getDtCadastro());
        administrador.setAgendas(dto.getAgendas());
        administrador.setIdiomas(dto.getIdiomas());
        administrador.setCargo(dto.getCargo());
        administrador.setEndereco(dto.getEndereco());


        return administrador;
    }

    public static AdministradorListagemDto toAdministradorDto(Administrador administrador) {
        AdministradorListagemDto dto = new AdministradorListagemDto();

        dto.setIdUsuario(administrador.getIdUsuario());
        dto.setNome(administrador.getNome());
        dto.setEmail(administrador.getEmail());
        dto.setTelefone(administrador.getTelefone());
        dto.setCpf(administrador.getCpf());
        dto.setSexoBiologico(administrador.getSexoBiologico());
        dto.setDtNascimento(administrador.getDtNascimento());
        dto.setTipoDeUsuario(administrador.getTipoDeUsuario());
        dto.setApresentacao(administrador.getApresentacao());
        dto.setDtCadastro(administrador.getDtCadastro());
        dto.setAgendas(administrador.getAgendas());
        dto.setIdiomas(administrador.getIdiomas());
        dto.setCargo(administrador.getCargo());
        dto.setEndereco(administrador.getEndereco());



        return dto;
    }

    public static List<AdministradorListagemDto> toDtoList(List<Administrador> adms) {
        return adms.stream().map(AdministradorMapper::toAdministradorDto).toList();

    }
}


