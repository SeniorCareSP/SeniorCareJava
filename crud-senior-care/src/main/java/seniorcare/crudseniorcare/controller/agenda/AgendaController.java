package seniorcare.crudseniorcare.controller.agenda;


import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaCriacaoDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaListagemDto;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Controller
@Data
@RequestMapping("/agendas")
public class AgendaController {

    private final AgendaService service;
    //solid com responsabilidade unica (clean code). Se tiver um outro endpoint que chama o mesmo metodo, so muda o retorno dele.

    //na controller so tem caminho feliz, ao contrario disso vai pra service. 204 é caminho feliz

    @GetMapping
    public ResponseEntity<List<AgendaListagemDto>> listar(){
        List<Agenda> agendas = service.list();

        if (agendas.isEmpty()) return ResponseEntity.noContent().build();
        List<AgendaListagemDto> dto = AgendaMapper.toListagemDtoList(agendas);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaListagemDto> porId(@PathVariable UUID id){
        Agenda agenda = service.byId(id);
        AgendaListagemDto dto = AgendaMapper.toListagemDto(agenda);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AgendaListagemDto> criar (
            @RequestBody AgendaCriacaoDto agendaDto){

        Agenda salvoEntity = AgendaMapper.toEntity(agendaDto);

        Agenda salvo = service.create(salvoEntity);
        AgendaListagemDto dto = AgendaMapper.toListagemDto(salvo);
        URI uri = URI.create("/agendas/" + salvo.getIdAgenda());

        return ResponseEntity.created(uri).body(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaListagemDto> update(@PathVariable UUID id, @RequestBody Agenda agenda){
        Agenda uptAgenda = service.update(id, agenda);
        AgendaListagemDto dto = AgendaMapper.toListagemDto(uptAgenda);
        return ResponseEntity.ok(dto);
    }
}
