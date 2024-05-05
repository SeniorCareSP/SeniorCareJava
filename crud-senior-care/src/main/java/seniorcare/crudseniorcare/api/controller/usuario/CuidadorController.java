package seniorcare.crudseniorcare.api.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;

@RequestMapping("/cuidador")
@RestController
@RequiredArgsConstructor

public class CuidadorController {
    private final CuidadorService cuidadorService;

    @PostMapping
    public ResponseEntity<UsuarioListagemCuidadorDto> criar(){
        return null;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListagemCuidadorDto>> listarUsuario(){
        List<UsuarioListagemCuidadorDto> listaUsuario = cuidadorService.listarTodos();

        return ResponseEntity.status(200).body(listaUsuario);
    }


}
