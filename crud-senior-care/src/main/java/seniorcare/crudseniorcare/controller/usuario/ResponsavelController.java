package seniorcare.crudseniorcare.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.util.List;
import java.util.UUID;

@RequestMapping("/responsavel")
@RestController
@RequiredArgsConstructor
public class ResponsavelController {


        private final ResponsavelService responsavelService;

        @PostMapping
        public ResponseEntity<UsuarioListagemCuidadorDto> criar(){
            return null;
        }

        @GetMapping
        public ResponseEntity<List<UsuarioListagemResponsavelDto>> listarUsuario(){
            List<UsuarioListagemResponsavelDto> listaUsuario = responsavelService.listarTodos();

            return ResponseEntity.status(200).body(listaUsuario);
        }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        responsavelService.deleteResponsavelById(id);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioListagemResponsavelDto> atualizarCuidador(@PathVariable UUID id, @RequestBody UsuarioCriacaoResponsavelDto responsavel){

        UsuarioListagemResponsavelDto cuidadorSalvo = responsavelService.updateResponsavel(id, responsavel);
        if (cuidadorSalvo == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(200).body(cuidadorSalvo);
    }


}
