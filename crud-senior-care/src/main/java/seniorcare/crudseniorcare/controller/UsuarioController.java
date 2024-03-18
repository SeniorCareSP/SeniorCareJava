package seniorcare.crudseniorcare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();
    private int id_Usuario;
    private int id_Endereco;
    private int id_Lingua;
    private int id_Agenda;


    @PostMapping("/{id_usuario}/comentarios")
    public ResponseEntity<Comentario> adicionarComentario(@PathVariable int id_usuario, @RequestBody Comentario novoComentario) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }


        usuario.getComentarios().add(novoComentario);
        return ResponseEntity.status(201).body(novoComentario);
    }

    @GetMapping("/{id_usuario}/comentarios")
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable int id_usuario) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        // Obter a lista de comentários do usuário
        return ResponseEntity.status(201).body(usuario.getComentarios());
    }

    @DeleteMapping("/{id_usuario}/comentarios/{id_comentario}")
    public ResponseEntity<Usuario> removerComentario(@PathVariable int id_usuario, @PathVariable int id_comentario) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        // Remover a agenda da lista de agendas do usuário
        if (exstIndice(id_comentario, usuario.getAgendas())) {
            usuario.getAgendas().remove(id_comentario);
            return ResponseEntity.status(200).body(usuario);
        } else {
            return ResponseEntity.status(404).build(); // Agenda não encontrada
        }

    }

// Outros métodos também devem seguir o mesmo padrão


    //===============================Métodos POST do usuario================================//
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario novoUsuario) {

        if (!Validation.isValidEmail(novoUsuario.getEmail()) ||
                !Validation.isValidPassword(novoUsuario.getSenha()) ||
                !Validation.isValidUsername(novoUsuario.getNome())) {
            return ResponseEntity.status(400).build();
        }

        if (emailExist(novoUsuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }


        novoUsuario.setId_usuario(++id_Usuario);
        usuarios.add(novoUsuario);

        // Verifica se o usuário deseja se cadastrar como Cuidador
        if (novoUsuario instanceof Cuidador novoCuidador) {
            // Converte o usuário para Cuidador
            novoCuidador.setExperiencia(novoCuidador.getExperiencia());
            novoCuidador.setFaixaEtaria(novoCuidador.getFaixaEtaria());
            novoCuidador.setQtdIdoso(novoCuidador.getQtdIdoso());
            novoCuidador.setPrecoHora(novoCuidador.getPrecoHora());

            // Retorna a resposta com o cuidador cadastrado
            return ResponseEntity.status(201).body(novoCuidador);
        }

        // Verifica se o usuário deseja se cadastrar como Responsavel
        if (novoUsuario instanceof Responsavel novoResponsavel) {
            // Converte o usuário para Responsavel
            novoResponsavel.setPrecoHora(novoResponsavel.getPrecoHora());
            // Retorna a resposta com o responsável cadastrado
            return ResponseEntity.status(201).body(novoResponsavel);
        }

        // Retorna a resposta com o usuário genérico cadastrado
        return ResponseEntity.status(201).body(novoUsuario);
    }


    @PostMapping("/{id_usuario}/enderecos")
    public ResponseEntity<Endereco> adicionarEndereco(@PathVariable int id_usuario, @RequestBody Endereco novoEndereco) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        novoEndereco.setId_endereco(++id_Endereco);
        // Adicionando o novo endereço à lista de endereços do usuário
        usuario.getEnderecos().add(novoEndereco);
        return ResponseEntity.status(201).body(novoEndereco);
    }

    @PostMapping("/{id_usuario}/linguas")
    public ResponseEntity<Lingua> adicionarLingua(@PathVariable int id_usuario, @RequestBody Lingua novaLingua) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }
        novaLingua.setId_lingua(++id_Lingua);
        // Adicionando uma nova lingua para lista de linguas que está no usuário
        usuario.getLinguas().add(novaLingua);
        return ResponseEntity.status(201).body(novaLingua);
    }

    @PostMapping("/{id_usuario}/agendas")
    public ResponseEntity<Agenda> adicionarAgenda(@PathVariable int id_usuario, @RequestBody Agenda novaAgenda) {
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
        if (usuario == null) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado
        }

        novaAgenda.setId_Agenda(++id_Agenda);
        // Adicionando uma agenda à lista de agenda do usuário
        usuario.getAgendas().add(novaAgenda);
        return ResponseEntity.status(201).body(novaAgenda);
    }

    //===============================Métodos GET do usuario================================//


    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/cuidadores")
    public ResponseEntity<List<Cuidador>> getCuidadores() {
        List<Cuidador> cuidadores = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cuidador) {
                cuidadores.add((Cuidador) usuario);
            }
        }
        return ResponseEntity.ok(cuidadores);
    }

    // Função para retornar todos os responsáveis cadastrados
    @GetMapping("/responsaveis")
    public ResponseEntity<List<Responsavel>> getResponsaveis() {
        List<Responsavel> responsaveis = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Responsavel) {
                responsaveis.add((Responsavel) usuario);
            }
        }
        return ResponseEntity.ok(responsaveis);
    }

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
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
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
        Usuario usuario = encontrarUsuarioPorId(id_usuario);
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


    private boolean emailExist(String email) {
        return usuarios.stream().anyMatch(usuario -> usuario.getEmail().equals(email));
    }

    public Usuario encontrarUsuarioPorId(int id_usuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId_usuario() == id_usuario) {
                return usuario;
            }
        }
        return null; // Usuário não encontrado
    }

    private boolean exstIndice(int indice, List<?> lista) {
        return indice - 1 >= 0 && indice - 1 < lista.size();
    }

}
