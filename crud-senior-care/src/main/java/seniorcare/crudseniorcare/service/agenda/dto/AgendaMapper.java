package seniorcare.crudseniorcare.service.agenda.dto;


import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaCriacaoDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaListagemDto;

import java.util.List;
import java.util.stream.Collectors;

public class AgendaMapper {

    public static Agenda toEntity(AgendaCriacaoDto criacaoDto) {
        Agenda agenda = new Agenda();
        agenda.setDiaDaSemana(criacaoDto.getDiaDaSemana());
        agenda.setPeriodo_manha(criacaoDto.isPeriodo_manha());
        agenda.setPeriodo_tarde(criacaoDto.isPeriodo_tarde());
        agenda.setPeriodo_noite(criacaoDto.isPeriodo_noite());
        agenda.setUsuario(criacaoDto.getUsuario());
        return agenda;
    }

    public static AgendaListagemDto toListagemDto(Agenda agenda) {
        AgendaListagemDto listagemDto = new AgendaListagemDto();
        listagemDto.setIdAgenda(agenda.getIdAgenda()); // Utilize o ID da entidade Agenda, se disponível
        listagemDto.setDiaDaSemana(agenda.getDiaDaSemana());
        listagemDto.setPeriodo_manha(agenda.isPeriodo_manha());
        listagemDto.setPeriodo_tarde(agenda.isPeriodo_tarde());
        listagemDto.setPeriodo_noite(agenda.isPeriodo_noite());
        listagemDto.setUsuario(agenda.getUsuario());
        return listagemDto;
    }

    public static List<AgendaListagemDto> toListagemDtoList(List<Agenda> agendaList) {
        return agendaList.stream()
                .map(AgendaMapper::toListagemDto)
                .collect(Collectors.toList());
    }
}
