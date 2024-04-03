package seniorcare.crudseniorcare.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.dto.CaracteristicaRecordDTO;
import seniorcare.crudseniorcare.dto.ComentarioRecordDTO;
import seniorcare.crudseniorcare.model.Caracteristica;
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

    @PostMapping
    ResponseEntity<Comentario>cadastrar(@RequestBody @Valid ComentarioRecordDTO comentarioRecordDTO){
        Comentario comentario = new Comentario();
        BeanUtils.copyProperties(comentarioRecordDTO, comentario);
        return ResponseEntity.status(201).body(comentariosRepository.save(comentario));
    }

    @PutMapping("/{idComentario}")
    public ResponseEntity<Comentario> atualizarComentario(@RequestBody @Valid ComentarioRecordDTO novoComentario, @PathVariable UUID idComentario) {
        Comentario comentario = new Comentario();
        BeanUtils.copyProperties(novoComentario, comentario);
        Optional<Comentario> comentarioEncontrado = comentariosRepository.findById(idComentario);

        if (comentarioEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        comentario.setIdComentario(null);
        return ResponseEntity.status(200).body(comentariosRepository.save(comentario));
    }
}
