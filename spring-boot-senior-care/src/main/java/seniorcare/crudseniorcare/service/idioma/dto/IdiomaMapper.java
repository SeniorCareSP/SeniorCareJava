package seniorcare.crudseniorcare.service.idioma.dto;

import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaListagemDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaCriacaoDto;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaListagemDto;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class IdiomaMapper {

    public static Idioma toIdioma(IdiomaCriacaoDto dto) {
        if (dto == null) return null;
        Idioma idioma = new Idioma();

        idioma.setIdioma(dto.getIdioma());

        return idioma;
    }

    public static IdiomaListagemDto toIdiomaDto(Idioma idioma) {
        if (idioma == null) return null;
        IdiomaListagemDto dto = new IdiomaListagemDto();


        dto.setIdIdioma(idioma.getIdIdioma());
        dto.setIdioma(idioma.getIdioma());

        return dto;
    }

    public static List<IdiomaListagemDto> toListagemDtoList(List<Idioma> idiomaList) {
        if (idiomaList == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se a lista de idiomas for nula
        }
        return idiomaList.stream()
                .map(IdiomaMapper::toIdiomaDto)
                .collect(Collectors.toList());
    }

    public static List<Idioma> toListagemIdioma(List<IdiomaCriacaoDto> idiomaList) {
        if (idiomaList == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se a lista de idiomas for nula
        }
        return idiomaList.stream()
                .map(IdiomaMapper::toIdioma)
                .collect(Collectors.toList());
    }



}
