package seniorcare.crudseniorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.*;

import java.util.List;

@RestController
@RequestMapping("/cuidadores")
public class CuidadorController {
    private int id_Caracteristica;
    private int id_Ajuda;


    @Autowired
    private UsuarioController usuarioController;



    @PostMapping("/{id_usuario}/caracteristica")
    public ResponseEntity<Caracteristica> adicionarCaracteristica(@PathVariable int id_usuario, @RequestBody Caracteristica novaCaracteristica){
        // Encontrar o usuário com o ID fornecido
        Usuario usuario = usuarioController.encontrarUsuarioPorId(id_usuario);

        // Verificar se o usuário existe e é um Cuidador
        if (usuario == null || !(usuario instanceof Cuidador cuidador)) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado ou não é um Cuidador
        }

        // Adicionando o id da caracteristica
        novaCaracteristica.setId_caracteristica(++id_Caracteristica);
        // Adicionar a característica ao Cuidador
        cuidador.getCaracteristicas().add(novaCaracteristica);

        // Retornar uma resposta com o Cuidador atualizado
        return ResponseEntity.status(201).body(novaCaracteristica);
    }

    @DeleteMapping("/{id_usuario}/Ajuda/{id_cara}")
    public ResponseEntity<Ajuda> removerCaracteristica(@PathVariable int id_usuario, @PathVariable int id_cara){
        Usuario usuario = usuarioController.encontrarUsuarioPorId(id_usuario);

        if (usuario == null || !(usuario instanceof Cuidador cuidador)) {
            return ResponseEntity.status(404).build();
        }else if(cuidador.getCaracteristicas().get(id_cara) == null){
            return ResponseEntity.status(204).build();
        }

        cuidador.getCaracteristicas().remove(id_cara);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/{id_usuario}/Ajuda")
    public ResponseEntity<Ajuda> adicionarAjuda(@PathVariable int id_usuario, @RequestBody Ajuda novaAjuda){
        // Encontrar o usuário com o ID fornecido
        Usuario usuario = usuarioController.encontrarUsuarioPorId(id_usuario);

        // Verificar se o usuário existe e é um Cuidador
        if (usuario == null || !(usuario instanceof Cuidador cuidador)) {
            return ResponseEntity.status(404).build(); // Usuário não encontrado ou não é um Cuidador
        }

        novaAjuda.setId_ajuda(++id_Ajuda);
        // Adicionar a ajuda ao Cuidador
        cuidador.getAjudas().add(novaAjuda);

        // Retornar uma resposta com o Cuidador atualizado
        return ResponseEntity.status(201).body(novaAjuda);
    }

    @DeleteMapping("/{id_usuario}/Ajuda/{id_ajuda}")
    public ResponseEntity<Ajuda> removerAjuda(@PathVariable int id_usuario, @PathVariable int id_ajdua){
        Usuario usuario = usuarioController.encontrarUsuarioPorId(id_usuario);

        if (usuario == null || !(usuario instanceof Cuidador cuidador)) {
            return ResponseEntity.status(404).build();
        }else if(cuidador.getAjudas().get(id_ajdua) == null){
            return ResponseEntity.status(204).build();
        }

        cuidador.getAjudas().remove(id_ajdua);
        return ResponseEntity.status(200).build();
    }
}
