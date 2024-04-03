package seniorcare.crudseniorcare.controller;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.AgendaRecordDTO;
import seniorcare.crudseniorcare.dto.EnderecoRecordDTO;
import seniorcare.crudseniorcare.dto.IdiomaRecordDTO;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.IdiomaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/idiomas")
public class IdiomaController {

    @Autowired
    IdiomaRepository idiomaRepository;
    @DeleteMapping("/{idIdioma}")
    public ResponseEntity<Idioma> removerLingua(@PathVariable UUID idLingua) {
        Optional<Idioma> idiomaEncontrado = idiomaRepository.findById(idLingua);

        if (idiomaEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        // Remover a língua da lista de línguas do usuário

        idiomaRepository.delete(idiomaEncontrado.get());
        return ResponseEntity.status(200).build();

    }


    @PutMapping("/{idIdioma}")
    public ResponseEntity<Idioma> atualizarIdioma(@RequestBody @Valid IdiomaRecordDTO novoIdioma, @PathVariable UUID idIdioma) {
        Idioma idioma = new Idioma();
        BeanUtils.copyProperties(novoIdioma, idioma);
        Optional<Idioma> idiomaEncontrado = idiomaRepository.findById(idIdioma);


        if (idiomaEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(idiomaRepository.save(idioma));
    }


    @GetMapping
    public ResponseEntity<List<Idioma>> listarIdioma() {
        List<Idioma> idiomas = idiomaRepository.findAll();
        if (idiomas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(idiomas);
    }

    @PostMapping
    ResponseEntity<Idioma>cadastrar(@RequestBody @Valid IdiomaRecordDTO idiomaDTO){
        Idioma idioma = new Idioma();
        BeanUtils.copyProperties(idiomaDTO, idioma);
        return ResponseEntity.status(201).body(idiomaRepository.save(idioma));
    }
    }


