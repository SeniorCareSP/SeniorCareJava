package seniorcare.crudseniorcare.service.usuario.dto;

import org.mapstruct.Mapper;
import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.usuario.dto.administrador.AdministradorCriacaoDto;
import seniorcare.crudseniorcare.service.usuario.dto.administrador.AdministradorListagemDto;

import java.time.LocalDate;
import java.util.List;

@Mapper
public class AdministradorMapper {
    public static Administrador toAdministrador(AdministradorCriacaoDto dto) {

        Administrador administrador = new Administrador();

        administrador.setNome(dto.getNome());
        administrador.setEmail(dto.getEmail());
        administrador.setSenha(dto.getSenha());
        administrador.setTipoDeUsuario(dto.getTipoDeUsuario());
        administrador.setDtCadastro(LocalDate.now());
        administrador.setCargo(dto.getCargo());


        return administrador;
    }

    public static AdministradorListagemDto toAdministradorDto(Administrador administrador) {
        AdministradorListagemDto dto = new AdministradorListagemDto();

        dto.setIdUsuario(administrador.getIdUsuario());
        dto.setNome(administrador.getNome());
        dto.setEmail(administrador.getEmail());
        dto.setTipoDeUsuario(administrador.getTipoDeUsuario());
        dto.setDtCadastro(administrador.getDtCadastro());
        dto.setCargo(administrador.getCargo());



        return dto;
    }

    public static List<AdministradorListagemDto> toDtoList(List<Administrador> adms) {
        return adms.stream().map(AdministradorMapper::toAdministradorDto).toList();

    }
}


