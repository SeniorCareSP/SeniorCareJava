package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.model.Idoso;
import seniorcare.crudseniorcare.model.Responsavel;
import seniorcare.crudseniorcare.model.Usuario;
import seniorcare.crudseniorcare.repository.ResponsavelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private ResponsavelRepository responsavelRepository;


    @GetMapping("/responsaveis")
    public ResponseEntity<List<Responsavel>> getResponsaveis() {
        List<Responsavel> responsaveis = responsavelRepository.findAll();

        if (responsaveis.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(responsaveis);
    }



    @PostMapping("/{idUsuario}/idoso")
    public ResponseEntity<Idoso> adicionarIdoso(@PathVariable UUID idUsuario, @RequestBody Idoso novoIdoso) {
        // Encontrar o usuário com o ID fornecido
        Optional<Responsavel> responsavelEncontrado = responsavelRepository.findById(idUsuario);

        // Verificar se o usuário existe e é um Cuidador
        if (responsavelEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        // Adicionar a característica ao Cuidador
        responsavelEncontrado.get().getIdosos().add(novoIdoso);

        // Retornar uma resposta com o Idoso adicionado
        return ResponseEntity.status(200).body(novoIdoso);
    }

    @GetMapping("/{idUsuario}/idoso/{idIdoso}")
    public ResponseEntity<List<Responsavel>> BuscarIdososPeloResponsavel(@PathVariable UUID idUsuario, @PathVariable int idIdoso) {

       List<Responsavel> idososEncontrados = responsavelRepository.findByIdByIdoso(idUsuario);

        if (idososEncontrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }

       return ResponseEntity.status(200).body(idososEncontrados);
    }

}
