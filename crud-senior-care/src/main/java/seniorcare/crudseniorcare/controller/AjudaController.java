package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        public ResponseEntity<Ajuda> cadastrarAjuda(@RequestBody Ajuda novaAjuda)
        {
            novaAjuda.setIdAjuda(null);

            if (ajudaRepository.existsByNome(novaAjuda.getNome())) {
                return ResponseEntity.status(409).build();
            }


            Ajuda ajuda = ajudaRepository.save(novaAjuda);
            // Retorna a resposta com o usuário genérico cadastrado
            return ResponseEntity.status(201).body(ajuda);
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
    }

