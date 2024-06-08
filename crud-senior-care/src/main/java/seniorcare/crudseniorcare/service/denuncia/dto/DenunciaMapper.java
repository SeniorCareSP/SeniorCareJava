package seniorcare.crudseniorcare.service.denuncia.dto;

import seniorcare.crudseniorcare.domain.denuncia.Denuncia;
import seniorcare.crudseniorcare.domain.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DenunciaMapper {

    public static Denuncia toEntity(DenunciaCriacaoDto dto) {
        Denuncia denuncia = new Denuncia();
//        denuncia.setInfo(dto.getInfo());
//        denuncia.setDetalhes(dto.getDetalhes());
//        denuncia.setStatus(dto.getStatus());
//        // Definir o usuário denunciador e denunciado
//        Usuario denunciador = new Usuario();
//        denunciador.setIdUsuario(dto.getUsuarioDenunciador());
//        denuncia.setUsuarioDenunciador(denunciador);
//        Usuario denunciado = new Usuario();
//        denunciado.setIdUsuario(dto.getUsuarioDenunciado());
//        denuncia.setUsuarioDenunciado(denunciado);
        return denuncia;
    }

    public static DenunciaListagemDto toListagemDto(Denuncia denuncia) {
        DenunciaListagemDto dto = new DenunciaListagemDto();
        dto.setId(denuncia.getId());
        dto.setInfo(denuncia.getInfo());
        dto.setDetalhes(denuncia.getDetalhes());
        dto.setStatus(denuncia.getStatus());
        dto.setUsuarioDenunciador(denuncia.getUsuario());
        dto.setUsuarioDenunciado(denuncia.getUsuarioDenunciado());
        return dto;
    }

    public static List<DenunciaListagemDto> toListagemDtoList(List<Denuncia> denuncias) {
        return denuncias.stream()
                .map(DenunciaMapper::toListagemDto)
                .collect(Collectors.toList());
    }
}
