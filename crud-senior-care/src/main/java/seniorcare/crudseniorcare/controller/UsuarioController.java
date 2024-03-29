package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.*;
import seniorcare.crudseniorcare.model.*;
import seniorcare.crudseniorcare.repository.UsuarioRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired private UsuarioRepository usuarioRepository;

    @PostMapping("/{idUsuario}/comentarios")
    public ResponseEntity<Comentario> adicionarComentario(@PathVariable UUID idUsuario, @RequestBody Comentario novoComentario) {
    Optional <Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
    if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        usuarioEncontrado.get().getComentarios().add(novoComentario);

        return ResponseEntity.status(201).body(novoComentario);
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable UUID idUsuario) {
        Optional <Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        // Obter a lista de comentários do usuário
        return ResponseEntity.status(201).body(usuarioEncontrado.get().getComentarios());
    }

    @DeleteMapping("/{idUsuario}/agenda/{idAgenda}")
    public ResponseEntity<Usuario> removerAgenda(@PathVariable UUID idUsuario, @PathVariable int idAgenda) {
        Optional <Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }








        // Remover a agenda da lista de agendas do usuário
        //aqui falta verificar se Agenda existe, se nao existir tem ue mapear erro tbm.

            return ResponseEntity.status(200).body(usuarioEncontrado.get());
    }

// Outros métodos também devem seguir o mesmo padrão


    //===============================Métodos POST do usuario================================//
    @PostMapping("/responsavel")
    public ResponseEntity<Responsavel> cadastrarResponsavel(@RequestBody Responsavel novoResponsavel)
    {
        novoResponsavel.setIdUsuario(null);

        if (usuarioRepository.existsByEmail(novoResponsavel.getEmail())) {
            return ResponseEntity.status(409).build();
        }
        Usuario novoUsuario = usuarioRepository.save(novoResponsavel);
        // Retorna a resposta com o usuário genérico cadastrado
        return ResponseEntity.status(201).body(novoResponsavel);
    }
    @PostMapping("/cuidador")
    public ResponseEntity<Cuidador> cadastrarCuidador(@RequestBody Cuidador novoCuidador)
    {
        novoCuidador.setIdUsuario(null);
        if (usuarioRepository.existsByEmail(novoCuidador.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        //mesma coisa do outro
      usuarioRepository.save(novoCuidador);
              // Retorna a resposta com o usuário genérico cadastrado
        return ResponseEntity.status(201).body(novoCuidador);
    }

    @PostMapping("/{idUsuario}/enderecos")
    public ResponseEntity<Endereco> adicionarEndereco(@PathVariable UUID idUsuario, @RequestBody Endereco novoEndereco) {
        Optional <Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        // Adicionando o novo endereço à lista de endereços do usuário
        usuarioEncontrado.get().getEnderecos().add(novoEndereco);
        return ResponseEntity.status(201).body(novoEndereco);
    }



    @PostMapping("/{idUsuario}/agendas")
    public ResponseEntity<Agenda> adicionarAgenda(@PathVariable UUID idUsuario, @RequestBody Agenda novaAgenda) {
        Optional <Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        // Adicionando uma agenda à lista de agenda do usuário
        usuarioEncontrado.get().getAgendas().add(novaAgenda);
        return ResponseEntity.status(201).body(novaAgenda);
    }

    //===============================Métodos GET do usuario================================//


    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();

    if (usuarios.isEmpty()){
        return ResponseEntity.status(204).build();
    }
        return ResponseEntity.status(200).body(usuarios);
    }

    // Função para retornar todos os responsáveis cadastrados





    //===============================Métodos UPDATE do usuario================================//




    //===============================Métodos DELETE do usuario================================//




    }

