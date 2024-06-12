package seniorcare.crudseniorcare.service.caracteristica.dto;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.service.caracteristica.dto.CaracteristicaCriacaoDto;
import seniorcare.crudseniorcare.service.caracteristica.dto.CaracteristicaListagemDto;

import java.util.Collections;
import java.util.stream.Collectors;

import java.util.List;
public class CaracteristicaMapper {

    public static Caracteristica toCaracteristica(CaracteristicaCriacaoDto dto) {
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setNome(dto.getNome());
        //caracteristica.setCuidadores(dto.getCuidadores());
        return caracteristica;
    }

    public static CaracteristicaListagemDto toCaracteristicaDto(Caracteristica caracteristica) {
        CaracteristicaListagemDto dto = new CaracteristicaListagemDto();
        dto.setIdCaracteristica(caracteristica.getIdCaracteristica());
        dto.setNome(caracteristica.getNome());
        //dto.setCuidadores(caracteristica.getCuidadores());
        return dto;
    }

    public static List<CaracteristicaListagemDto> toCaracteristicaListagemDtoList(List<Caracteristica> caracteristicas) {
        return caracteristicas.stream()
                .map(CaracteristicaMapper::toCaracteristicaDto)
                .collect(Collectors.toList());
    }

    public static List<Caracteristica> toCaracteristicaListagemEntityList(List<CaracteristicaCriacaoDto> caracteristicasDto) {
        if (caracteristicasDto == null) {
            return Collections.emptyList();
        }
        return caracteristicasDto.stream()
                .map(CaracteristicaMapper::toCaracteristica)
                .collect(Collectors.toList());
    }
}
