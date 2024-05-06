package seniorcare.crudseniorcare.service.caracteristica.dto;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
import seniorcare.crudseniorcare.service.caracteristica.dto.CaracteristicaCriacaoDto;
import seniorcare.crudseniorcare.service.caracteristica.dto.CaracteristicaListagemDto;

import java.util.stream.Collectors;

import java.util.List;
public class CaracteristicaMapper {

    public Caracteristica toCaracteristica(CaracteristicaCriacaoDto dto) {
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setNome(dto.getNome());
        caracteristica.setCuidadores(dto.getCuidadores());
        return caracteristica;
    }

    public CaracteristicaListagemDto toCaracteristicaListagemDto(Caracteristica caracteristica) {
        CaracteristicaListagemDto dto = new CaracteristicaListagemDto();
        dto.setIdCaracteristica(caracteristica.getIdCaracteristica());
        dto.setNome(caracteristica.getNome());
        dto.setCuidadores(caracteristica.getCuidadores());
        return dto;
    }

    public List<CaracteristicaListagemDto> toCaracteristicaListagemDtoList(List<Caracteristica> caracteristicas) {
        return caracteristicas.stream()
                .map(this::toCaracteristicaListagemDto)
                .collect(Collectors.toList());
    }
}
