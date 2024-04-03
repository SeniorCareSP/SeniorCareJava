package seniorcare.crudseniorcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.AgendaRecordDTO;
import seniorcare.crudseniorcare.dto.AjudaRecordDTO;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.AjudaRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/ajudas")
public class AjudaController {
    @Autowired
    AjudaRepository ajudaRepository;
    @PostMapping
    ResponseEntity<Ajuda>cadastrar(@RequestBody @Valid AjudaRecordDTO ajudaDTO){
        Ajuda ajuda = new Ajuda();
        BeanUtils.copyProperties(ajudaDTO, ajuda);
        return ResponseEntity.status(201).body(ajudaRepository.save(ajuda));
    }

    @DeleteMapping("/{idAjuda}")
    public ResponseEntity<Ajuda> removerAjuda(@PathVariable UUID idAjuda){
        Optional<Ajuda> ajuda = ajudaRepository.findById(idAjuda);

        if (ajuda.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        ajudaRepository.delete(ajuda.get());
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idAjuda}")
    public ResponseEntity<Ajuda> atualizarAjuda(@RequestBody @Valid AjudaRecordDTO novaAjuda, @PathVariable UUID idAjuda) {
        Ajuda ajuda = new Ajuda();
        BeanUtils.copyProperties(novaAjuda, ajuda);
        Optional<Ajuda> ajudaEncontrada = ajudaRepository.findById(idAjuda);

        if (ajudaEncontrada.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        ajuda.setIdAjuda(null);
        return ResponseEntity.status(200).body(ajudaRepository.save(ajuda));
    }
    }

