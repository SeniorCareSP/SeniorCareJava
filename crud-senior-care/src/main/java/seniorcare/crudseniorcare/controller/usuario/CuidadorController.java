package seniorcare.crudseniorcare.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;
import java.util.UUID;

@RequestMapping("/cuidador")
@RestController
@RequiredArgsConstructor

public class CuidadorController {
    private final CuidadorService cuidadorService;



    @GetMapping
    public ResponseEntity<List<UsuarioListagemCuidadorDto>> listarUsuario(){
        List<UsuarioListagemCuidadorDto> listaUsuario = cuidadorService.listarTodos();

        return ResponseEntity.status(200).body(listaUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        cuidadorService.deleteCuidadorById(id);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioListagemCuidadorDto> atualizarCuidador(@PathVariable UUID id, @RequestBody UsuarioCriacaoCuidadorDto cuidador){

        UsuarioListagemCuidadorDto cuidadorSalvo = cuidadorService.updateCuidador(id, cuidador);
        if (cuidadorSalvo == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(200).body(cuidadorSalvo);
    }


}
