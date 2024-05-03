package seniorcare.crudseniorcare.api.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioLoginDto;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;

@RestController
@RequestMapping("/usuarios" +
        "")
public class    UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListagemDto>> listarUsuario(){
        List<UsuarioListagemDto> listaUsuario = usuarioService.listarTodos();

        return ResponseEntity.status(200).body(listaUsuario);
    }

    @PostMapping("/criar-cuidador")
    public ResponseEntity<UsuarioListagemCuidadorDto> criarCuidador(@RequestBody UsuarioCriacaoCuidadorDto usuarioCriacaoCuidadorDto){

       return ResponseEntity.status(201).body(usuarioService.criarCuidador(usuarioCriacaoCuidadorDto));

    }
    @PostMapping("/criar-responsavel")
    public ResponseEntity<UsuarioListagemResponsavelDto> criarResponsavel(@RequestBody UsuarioCriacaoResponsavelDto usuarioCriacaoResponsavelDtoDto){

        return ResponseEntity.status(201).body(usuarioService.criarResponsavel(usuarioCriacaoResponsavelDtoDto));
    }


}

