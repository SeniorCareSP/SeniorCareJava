package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.AgendaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.UUID;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    AgendaRepository agendaRepository;

    @DeleteMapping("/agendas/{idAgenda}")
    public ResponseEntity<Usuario> removerAgenda(@PathVariable UUID idAgenda) {
        Optional<Agenda> agendaEncontrada = agendaRepository.findById(idAgenda);

        if (agendaEncontrada.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        // Remover a língua da lista de línguas do usuário

        agendaRepository.delete(agendaEncontrada.get());
        return ResponseEntity.status(200).build();

    }

    @PutMapping("/{idAgenda}")
    public ResponseEntity<Agenda> atualizarAgenda( @RequestBody Agenda novaAgenda, @PathVariable UUID idAgenda) {

        Optional<Agenda> agendaEncontrada = agendaRepository.findById(idAgenda);

        if (agendaEncontrada.isEmpty()) {
            return ResponseEntity.status(404).build();
        }


        novaAgenda.setIdAgenda(null);
        agendaRepository.save(novaAgenda);

        return ResponseEntity.status(200).body(novaAgenda);
    }
}
