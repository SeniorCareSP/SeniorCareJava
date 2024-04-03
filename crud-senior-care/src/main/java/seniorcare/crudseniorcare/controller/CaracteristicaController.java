package seniorcare.crudseniorcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.AgendaRecordDTO;
import seniorcare.crudseniorcare.dto.CaracteristicaRecordDTO;
import seniorcare.crudseniorcare.model.Agenda;
import seniorcare.crudseniorcare.model.Ajuda;
import seniorcare.crudseniorcare.model.Caracteristica;
import seniorcare.crudseniorcare.model.Cuidador;
import seniorcare.crudseniorcare.repository.CaracteristicaRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {
    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    @PostMapping
    ResponseEntity<Caracteristica>cadastrar(@RequestBody @Valid CaracteristicaRecordDTO caracteristicaDTO){
        Caracteristica caracteristica = new Caracteristica();
        BeanUtils.copyProperties(caracteristicaDTO, caracteristica);
        return ResponseEntity.status(201).body(caracteristicaRepository.save(caracteristica));
    }

    @DeleteMapping("/{idCaracteristica}")
    public ResponseEntity<Ajuda> removerCaracteristica(@PathVariable UUID idCaracteristica){
        Optional<Caracteristica> caracteristica = caracteristicaRepository.findById(idCaracteristica);

        if (caracteristica.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        caracteristicaRepository.delete(caracteristica.get());
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{idCaracteristica}")
    public ResponseEntity<Caracteristica> atualizarCaracteristica(@RequestBody @Valid CaracteristicaRecordDTO novaCaracteristica, @PathVariable UUID idCaracteristica) {
        Caracteristica caracteristica = new Caracteristica();
        BeanUtils.copyProperties(novaCaracteristica, caracteristica);
        Optional<Caracteristica> caracteristicaEncontrada = caracteristicaRepository.findById(idCaracteristica);

        if (caracteristicaEncontrada.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        caracteristica.setIdCaracteristica(null);
        return ResponseEntity.status(200).body(caracteristicaRepository.save(caracteristica));
    }

}
