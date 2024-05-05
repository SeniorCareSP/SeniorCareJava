package seniorcare.crudseniorcare.api.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;

import java.util.List;
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



}
