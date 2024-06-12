package seniorcare.crudseniorcare.service.idoso.dto;

import seniorcare.crudseniorcare.domain.idoso.Idoso;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IdosoMapper {

    public static Idoso toIdoso(IdosoCriacaoDto dto) {
        if (dto == null) return null;
        Idoso idoso = new Idoso();

        idoso.setNome(dto.getNome());
        idoso.setDescricao(dto.getDescricao());
        idoso.setMobilidade(dto.isMobilidade());
        idoso.setLucido(dto.isLucido());
        idoso.setDoencasCronicas(dto.getDoencasCronicas());
        idoso.setCuidadosMin(dto.getCuidadosMin());
        idoso.setGenero(dto.getGenero());

        return idoso;
    }

    public static IdosoListagemDto toIdosoDto(Idoso idoso) {
        if (idoso == null) return null;
        IdosoListagemDto dto = new IdosoListagemDto();

        dto.setIdIdoso(idoso.getIdIdoso());
        dto.setNome(idoso.getNome());
        dto.setDescricao(idoso.getDescricao());
        dto.setMobilidade(idoso.isMobilidade());
        dto.setLucido(idoso.isLucido());
        dto.setDoencasCronicas(idoso.getDoencasCronicas());
        dto.setCuidadosMin(idoso.getCuidadosMin());
        dto.setGenero(idoso.getGenero());

        return dto;
    }

    public static List<IdosoListagemDto> toListagemDtoList(List<Idoso> idosoList) {
        if (idosoList == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se a lista de idosos for nula
        }
        return idosoList.stream()
                .map(IdosoMapper::toIdosoDto)
                .collect(Collectors.toList());
    }

    public static List<Idoso> toIdosoList(List<IdosoCriacaoDto> idosoCriacaoDtoList) {
        if (idosoCriacaoDtoList == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se a lista de DTOs for nula
        }
        return idosoCriacaoDtoList.stream()
                .map(IdosoMapper::toIdoso)
                .collect(Collectors.toList());
    }
}
