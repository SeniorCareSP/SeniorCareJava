package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;

    @DeleteMapping("{idEndereco}")
    public ResponseEntity<Idioma> removerEndereco(@PathVariable UUID idEndereco) {
        Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(idEndereco);

        if (enderecoEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        // Remover a língua da lista de línguas do usuário

        enderecoRepository.delete(enderecoEncontrado.get());
        return ResponseEntity.status(200).build();

    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<Endereco> atualizarEndereco( @RequestBody Endereco novoEndereco, @PathVariable UUID idEndereco) {
            Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(idEndereco);

            if (enderecoEncontrado.isEmpty()) {
                return ResponseEntity.status(404).build();
            }


            novoEndereco.setIdEndereco(null);
            enderecoRepository.save(novoEndereco);

            return ResponseEntity.status(200).body(novoEndereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEndereco(@PathVariable UUID idEndereco) {
        List<Endereco> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(enderecos);
    }
}
