package seniorcare.crudseniorcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.AgendaRecordDTO;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.AgendaRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    private final AgendaRepository agendaRepository;

    @Autowired
    public AgendaController(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @PostMapping
    ResponseEntity<Agenda>cadastrar(@RequestBody @Valid AgendaRecordDTO agendaDTO){
        Agenda agenda = new Agenda();
        BeanUtils.copyProperties(agendaDTO, agenda);
        return ResponseEntity.status(201).body(agendaRepository.save(agenda));
    }

    @DeleteMapping("/{idAgenda}")
    public ResponseEntity<Usuario> removerAgenda(@PathVariable UUID idAgenda) {
        Optional<Agenda> agendaEncontrada = agendaRepository.findById(idAgenda);
        if (agendaEncontrada.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        agendaRepository.delete(agendaEncontrada.get());
        return ResponseEntity.status(200).build();

    }

    @PutMapping("/{idAgenda}")
    public ResponseEntity<Agenda> atualizarAgenda(@RequestBody @Valid AgendaRecordDTO novaAgenda, @PathVariable UUID idAgenda) {
        Agenda agenda = new Agenda();
        BeanUtils.copyProperties(novaAgenda, agenda);
        Optional<Agenda> agendaEncontrada = agendaRepository.findById(idAgenda);

        if (agendaEncontrada.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        agenda.setIdAgenda(null);
        return ResponseEntity.status(200).body(agendaRepository.save(agenda));
    }
}
