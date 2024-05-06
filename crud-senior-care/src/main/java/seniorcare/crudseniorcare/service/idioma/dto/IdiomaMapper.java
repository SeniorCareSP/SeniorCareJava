package seniorcare.crudseniorcare.service.idioma.dto;

import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaCriacaoDto;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaListagemDto;

import java.util.UUID;

public class IdiomaMapper {

    public Idioma toIdioma(IdiomaCriacaoDto dto) {
        Idioma idioma = new Idioma();

        idioma.setIdioma(dto.getIdioma());

        return idioma;
    }

    public IdiomaListagemDto toIdiomaListagemDto(Idioma idioma) {
        IdiomaListagemDto dto = new IdiomaListagemDto();


        dto.setIdIdioma(idioma.getIdIdioma());
        dto.setIdioma(idioma.getIdioma());

        return dto;
    }
}
