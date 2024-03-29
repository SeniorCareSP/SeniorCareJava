package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.model.Ajuda;
import seniorcare.crudseniorcare.model.Caracteristica;
import seniorcare.crudseniorcare.model.Cuidador;
import seniorcare.crudseniorcare.model.Usuario;
import seniorcare.crudseniorcare.repository.CuidadorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cuidadores")
public class CuidadorController {
    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private CuidadorRepository cuidadorRepository;


    @GetMapping
    public ResponseEntity<List<Cuidador>> getCuidadores() {

        List<Cuidador> cuidadores = cuidadorRepository.findAll();
        if (cuidadores.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cuidadores);
    }


    @PostMapping("/{idUsuario}/caracteristica")
    public ResponseEntity<Caracteristica> adicionarCaracteristica(@PathVariable UUID idUsuario, @RequestBody Caracteristica novaCaracteristica){
        // Encontrar o usuário com o ID fornecido
        Optional<Cuidador> cuidador = cuidadorRepository.findById(idUsuario);

        // Verificar se o usuário existe
        if (cuidador.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado ou não é um Cuidador
        }

        // Adicionar a característica ao Cuidador
        cuidador.get().getCaracteristicas().add(novaCaracteristica);

        // Retornar uma resposta com o Cuidador atualizado
        return ResponseEntity.status(201).body(novaCaracteristica);
    }



}
