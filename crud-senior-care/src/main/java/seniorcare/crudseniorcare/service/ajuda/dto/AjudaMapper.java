package seniorcare.crudseniorcare.service.ajuda.dto;

import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.service.ajuda.dto.AjudaCriacaoDto;
import seniorcare.crudseniorcare.service.ajuda.dto.AjudaListagemDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoListagemDto;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AjudaMapper {

    public static Ajuda toAjuda(AjudaCriacaoDto ajudaCriacaoDto) {
        Ajuda ajuda = new Ajuda();
        ajuda.setNome(ajudaCriacaoDto.getNome());
        //ajuda.setCuidadores(ajudaCriacaoDto.getCuidadores());
        return ajuda;
    }

    public static AjudaListagemDto toAjudaListagemDto(Ajuda ajuda) {
        AjudaListagemDto dto = new AjudaListagemDto();
        dto.setIdAjuda(ajuda.getIdAjuda());
        dto.setNome(ajuda.getNome());
        //dto.setCuidadores(ajuda.getCuidadores());
        return dto;
    }

    public static List<AjudaListagemDto> toAjudaListagemDtoList(List<Ajuda> ajudaList) {
        return ajudaList.stream()
                .map(AjudaMapper::toAjudaListagemDto)
                .collect(Collectors.toList());
    }

    public static List<Ajuda> toAjudaListagemEntityList(List<AjudaCriacaoDto> ajudaList) {
        if (ajudaList == null) {
            return Collections.emptyList();
        }
        return ajudaList.stream()
                .map(AjudaMapper::toAjuda)
                .collect(Collectors.toList());
    }
}
