package seniorcare.crudseniorcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.ComentarioRecordDTO;
import seniorcare.crudseniorcare.dto.CuidadorRecordDTO;
import seniorcare.crudseniorcare.dto.EnderecoRecordDTO;
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
    public ResponseEntity<Endereco> atualizarEndereco(@RequestBody @Valid EnderecoRecordDTO novoEndereco, @PathVariable UUID idEndereco) {
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(novoEndereco, endereco);
            Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(idEndereco);


            if (enderecoEncontrado.isEmpty()) {
                return ResponseEntity.status(404).build();
            }

        return ResponseEntity.status(200).body(enderecoRepository.save(endereco));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEndereco(@PathVariable UUID idEndereco) {
        List<Endereco> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(enderecos);
    }

    @PostMapping
    ResponseEntity<Endereco>cadastrar(@RequestBody @Valid EnderecoRecordDTO enderecoDTO){
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        return ResponseEntity.status(201).body(enderecoRepository.save(endereco));
    }
}
