package seniorcare.crudseniorcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.AgendaRecordDTO;
import seniorcare.crudseniorcare.dto.EnderecoRecordDTO;
import seniorcare.crudseniorcare.dto.IdosoRecordDTO;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.IdosoRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/idosos")
public class IdosoController {

    @Autowired
    private IdosoRepository idosoRepository;

    @PutMapping("/{idIdoso}")
    public ResponseEntity<Idoso> atualizarIdoso(@RequestBody @Valid IdosoRecordDTO novoIdoso, @PathVariable UUID idIdoso) {
        Idoso idoso = new Idoso();
        BeanUtils.copyProperties(novoIdoso, idoso);
        Optional<Idoso> idosoEncontrado = idosoRepository.findById(idIdoso);

        if (idosoEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        idoso.setIdIdoso(null);
        return ResponseEntity.status(200).body(idosoRepository.save(idoso));
    }

    @DeleteMapping("/{idIdoso}")
    public ResponseEntity<Idoso> deletarIdoso(@PathVariable UUID idIdoso) {
        Optional<Idoso> idoso = idosoRepository.findById(idIdoso);

        if (idoso.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        idosoRepository.delete(idoso.get());

        return ResponseEntity.status(200).build();
    }

    @PostMapping
    ResponseEntity<Idoso>cadastrar(@RequestBody @Valid IdosoRecordDTO idosoDTO){
        Idoso idoso = new Idoso();
        BeanUtils.copyProperties(idosoDTO, idoso);
        return ResponseEntity.status(201).body(idosoRepository.save(idoso));
    }

}
