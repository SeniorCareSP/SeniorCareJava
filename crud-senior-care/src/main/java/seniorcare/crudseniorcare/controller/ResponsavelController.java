package seniorcare.crudseniorcare.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController{

    @Autowired
    private UsuarioController usuarioController;
    private int id_Idoso;

    @PostMapping("/{id_usuario}/idoso")
    public ResponseEntity<Idoso> adicionarIdoso(@PathVariable int id_usuario, @RequestBody Idoso novoIdoso){
        // Encontrar o usuário com o ID fornecido
        Usuario usuario = usuarioController.encontrarUsuarioPorId(id_usuario);

        // Verificar se o usuário existe e é um Cuidador
        if (usuario == null || !(usuario instanceof Responsavel responsavel)) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado ou não é um Cuidador
        }

        novoIdoso.setId_idoso(++id_Idoso);
        // Adicionar a característica ao Cuidador
        responsavel.getIdosos().add(novoIdoso);

        // Retornar uma resposta com o Cuidador atualizado
        return ResponseEntity.status(201).body(novoIdoso);
    }

    @GetMapping("/{id_usuario}/idoso/{id_idoso}")
    public ResponseEntity<Idoso> BuscarIdoso(@PathVariable int id_usuario, @PathVariable int id_Idoso){
        Usuario usuario = usuarioController.encontrarUsuarioPorId(id_usuario);
        if (usuario == null || !(usuario instanceof Responsavel responsavel)){
            return ResponseEntity.status(404).build();
        }else if(responsavel.getIdosos() == null){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(responsavel.getIdosos().get(id_Idoso));
    }

//    @PutMapping("/{id_usuario}/idoso/{id_idoso}")
//    public ResponseEntity<Idoso> atualizarIdoso(@PathVariable int id_usuario, @PathVariable int id_Idoso, @RequestBody Idoso attIdoso){
//        Usuario usuario = usuarioController.encontrarUsuarioPorId(id_usuario);
//        if (usuario == null || !(usuario instanceof Responsavel responsavel)){
//            return ResponseEntity.status(404).build();
//        }else if(responsavel.getIdosos() == null){
//            return ResponseEntity.status(204).build();
//        }
//
//
//    }

    @DeleteMapping("/{id_usuario}/idoso/{id_idoso}")
    public ResponseEntity<Idoso> deletarIdoso(@PathVariable int id_usuario, @PathVariable int id_Idoso){
        Usuario usuario = usuarioController.encontrarUsuarioPorId(id_usuario);
        if (usuario == null || !(usuario instanceof Responsavel responsavel)){
            return ResponseEntity.status(404).build();
        }else if(responsavel.getIdosos() == null){
            return ResponseEntity.status(204).build();
        }
        responsavel.getIdosos().remove(id_Idoso);
        return ResponseEntity.status(200).build();
    }
}
