package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();

    if (usuarios.isEmpty()){
        return ResponseEntity.status(204).build();
    }
        return ResponseEntity.status(200).body(usuarios);
    }

    }

