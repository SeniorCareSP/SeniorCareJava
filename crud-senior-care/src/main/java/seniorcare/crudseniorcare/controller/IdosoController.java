package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.model.Idoso;
import seniorcare.crudseniorcare.model.Responsavel;
import seniorcare.crudseniorcare.model.Usuario;
import seniorcare.crudseniorcare.repository.IdosoRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/idosos")
public class IdosoController {

    @Autowired
    private IdosoRepository idosoRepository;

    @PutMapping("/{idUsuario}/idoso/{idIdoso}")
    public ResponseEntity<Idoso> atualizarIdoso(@PathVariable UUID idIdoso, @RequestBody Idoso idosoAtualizado) {
        Optional<Idoso> idosoEncontrado = idosoRepository.findById(idIdoso);

        if (idosoEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }


        idosoAtualizado.setIdoso(null);
        idosoRepository.save(idosoAtualizado);

        return ResponseEntity.status(200).body(idosoAtualizado);
    }

    @DeleteMapping("/{idUsuario}/idoso/{idIdoso}")
    public ResponseEntity<Idoso> deletarIdoso(@PathVariable UUID idIdoso) {
        Optional<Idoso> idoso = idosoRepository.findById(idIdoso);

        if (idoso.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        idosoRepository.delete(idoso.get());

        return ResponseEntity.status(200).build();
    }

}
