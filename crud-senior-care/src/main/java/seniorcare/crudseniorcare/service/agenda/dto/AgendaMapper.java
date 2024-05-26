package seniorcare.crudseniorcare.service.agenda.dto;


import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaCriacaoDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaListagemDto;

import java.util.List;
import java.util.stream.Collectors;

public class AgendaMapper {

    public static Agenda toEntity(AgendaCriacaoDto dto) {
        if (dto == null) return null;
        Agenda agenda = new Agenda();
        agenda.setDisponibilidade(dto.getDisponibilidade());
        return agenda;
    }

    public static AgendaListagemDto toListagemDto(Agenda agenda) {
        if (agenda == null) return null;
        AgendaListagemDto dto = new AgendaListagemDto();

        dto.setIdAgenda(agenda.getIdAgenda());
        dto.setDisponibilidade(agenda.getDisponibilidade());
        return dto;
    }

    public static List<AgendaListagemDto> toListagemDtoList(List<Agenda> agendaList) {
        return agendaList.stream()
                .map(AgendaMapper::toListagemDto)
                .collect(Collectors.toList());
    }
}
