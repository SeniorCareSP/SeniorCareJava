package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @DeleteMapping("/{id_usuario}/linguas/{id_lingua}")
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
    public ResponseEntity<Idioma> atualizarLingua(@RequestBody Idioma novoIdioma, @PathVariable UUID idIdioma) {
        Optional<Idioma> idiomaEncontrado = idiomaRepository.findById(idIdioma);

        if (idiomaEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }


        novoIdioma.setIdIdioma(null);
        idiomaRepository.save(novoIdioma);

        return ResponseEntity.status(200).body(novoIdioma);
    }

    @PutMapping("/{idIdioma}")
    public ResponseEntity<Idioma> atualizarIdioma(@PathVariable UUID idIdioma, @RequestBody Idioma novoIdioma) {

        Optional<Idioma> idiomaEncontrado = idiomaRepository.findById(idIdioma);

        if (idiomaEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }


        novoIdioma.setIdIdioma(null);
        idiomaRepository.save(novoIdioma);

        return ResponseEntity.status(200).body(novoIdioma);
    }

    @GetMapping
    public ResponseEntity<List<Idioma>> listarlingua(@PathVariable UUID idIdioma) {
        List<Idioma> idiomas = idiomaRepository.findAll();
        if (idiomas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(idiomas);
    }

    @PostMapping("/cuidador")
    public ResponseEntity<Idioma> cadastrarIdioma(@RequestBody Idioma novoidioma)
    {
        novoidioma.setIdIdioma(null);
        if (idiomaRepository.existsById(novoidioma.getIdIdioma())) {
            return ResponseEntity.status(409).build();
        }

        //mesma coisa do outro
        idiomaRepository.save(novoidioma);
        // Retorna a resposta com o idioma genérico cadastrado
        return ResponseEntity.status(201).body(novoidioma);
    }
}

