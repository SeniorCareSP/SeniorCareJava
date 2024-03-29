package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorcare.crudseniorcare.model.Comentario;
import seniorcare.crudseniorcare.model.Usuario;
import seniorcare.crudseniorcare.repository.ComentariosRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    ComentariosRepository comentariosRepository;

    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable UUID idComentario) {
        List<Comentario> usuarios = comentariosRepository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build(); // Usuário não encontrado
        }
        return ResponseEntity.status(200).body(usuarios);
    }
}
