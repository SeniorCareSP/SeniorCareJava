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

    @GetMapping("/{idUsuario}/comentarios")
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

    @PostMapping("/{idUsuario}/linguas")
    public ResponseEntity<Lingua> adicionarLingua(@PathVariable UUID idUsuario, @RequestBody Lingua novaLingua) {
        Optional <Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        //Adicionando uma nova lingua para lista de linguas que está no usuario
        usuarioEncontrado.get().getLinguas().add(novaLingua);
        return ResponseEntity.status(201).body(novaLingua);
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

    @GetMapping("/{id_usuario}/enderecos")
    public ResponseEntity<List<Endereco>> listarEndereco(@PathVariable int id_usuario) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        if (usuario.getEnderecos().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuario.getEnderecos());
    }

    @GetMapping("/{id_usuario}/linguas")
    public ResponseEntity<List<Lingua>> listarlingua(@PathVariable int id_usuario) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        if (usuario.getLinguas().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuario.getLinguas());
    }

    @GetMapping("/{id_usuario}/agendas")
    public ResponseEntity<List<Agenda>> listarAgenda(@PathVariable int id_usuario) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        if (usuario.getAgendas().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuario.getAgendas());
    }

    //===============================Métodos UPDATE do usuario================================//

    @PutMapping("/{id_usuario}/enderecos/{id_endereco}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable int id_usuario, @RequestBody Endereco novoEndereco, @PathVariable int id_endereco) {

        Usuario usuario = encontrarUsuarioPorId(id_usuario);

        if (usuario == null || !exstIndice(id_endereco, usuarios.get(id_usuario - 1).getEnderecos())) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado ou endenreco não encontrado na lista de endereco
        }
        if (usuario.getEnderecos().isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        usuario.getEnderecos().set(id_endereco - 1, novoEndereco);

        return ResponseEntity.status(200).body(novoEndereco);
    }

    @PutMapping("/{id_usuario}/linguas/{id_lingua}")
    public ResponseEntity<Lingua> atualizarLingua(@PathVariable int id_usuario, @RequestBody Lingua novaLingua, @PathVariable int id_lingua) {

        Usuario usuario = encontrarUsuarioPorId(id_usuario);

        if (usuario == null || !exstIndice(id_lingua, usuarios.get(id_usuario - 1).getLinguas())) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado ou lingua não encontrado na lista de endereco
        }
        if (usuario.getLinguas().isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        usuario.getLinguas().set(id_lingua - 1, novaLingua);

        return ResponseEntity.status(200).body(novaLingua);
    }


    @PutMapping("/{id_usuario}/agendas/{id_agenda}")
    public ResponseEntity<Agenda> atualizarAgenda(@PathVariable int id_usuario, @RequestBody Agenda novaAgenda, @PathVariable int id_agenda) {

        Usuario usuario = encontrarUsuarioPorId(id_usuario);

        if (usuario == null || !exstIndice(id_agenda, usuarios.get(id_usuario - 1).getAgendas())) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado ou agenda não encontrado na lista de endereco
        }
        if (usuario.getAgendas().isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        usuario.getAgendas().set(id_agenda - 1, novaAgenda);

        return ResponseEntity.status(200).body(novaAgenda);
    }

    //===============================Métodos DELETE do usuario================================//


    @DeleteMapping("/{id_usuario}/enderecos/{id_endereco}")
    public ResponseEntity<Usuario> removerEndereco(@PathVariable int id_usuario, @PathVariable int id_endereco) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        // Remover o endereço da lista de endereços do usuário
        if (exstIndice(id_endereco, usuario.getEnderecos())) {
            usuario.getEnderecos().remove(id_endereco);
            return ResponseEntity.status(200).body(usuario);
        } else {
            return ResponseEntity.status(404).build(); // Endereço não encontrado
        }
    }

    @DeleteMapping("/{id_usuario}/linguas/{id_lingua}")
    public ResponseEntity<Usuario> removerLingua(@PathVariable int id_usuario, @PathVariable int id_lingua) {
        Usuario usuario = find(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        // Remover a língua da lista de línguas do usuário
        if (exstIndice(id_lingua, usuario.getLinguas())) {
            usuario.getLinguas().remove(id_lingua);
            return ResponseEntity.status(200).body(usuario);
        } else {
            return ResponseEntity.status(404).build(); // Língua não encontrada
        }
    }

    @DeleteMapping("/{id_usuario}/agendas/{id_agenda}")
    public ResponseEntity<Usuario> removerAgenda(@PathVariable int id_usuario, @PathVariable int id_agenda) {
        Usuario usuario = findBy(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        // Remover a agenda da lista de agendas do usuário
        if (exstIndice(id_agenda, usuario.getAgendas())) {
            usuario.getAgendas().remove(id_agenda);
            return ResponseEntity.status(200).body(usuario);
        } else {
            return ResponseEntity.status(404).build(); // Agenda não encontrada
        }
    }




}
