package seniorcare.crudseniorcare.api.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioLoginDto;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;
import seniorcare.crudseniorcare.service.usuario.dto.UsuarioMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioCriacaoCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioCriacaoResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.responsavel.UsuarioListagemResponsavelDto;
import seniorcare.crudseniorcare.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CuidadorService cuidadorService;
    private final ResponsavelService responsavelService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        usuarioService.logout();
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListagemDto>> listarUsuario(){
        List<UsuarioListagemDto> listaUsuario = usuarioService.listarTodos();

        return ResponseEntity.status(200).body(listaUsuario);
    }

    @GetMapping("/cuidadores")
    public ResponseEntity<List<UsuarioListagemCuidadorDto>> listarCuidadores(){
        List<UsuarioListagemCuidadorDto> listarCuidadores = cuidadorService.listarTodos();
        return ResponseEntity.status(200).body(listarCuidadores);
    }


    @PostMapping("/criar-cuidador")
    public ResponseEntity<UsuarioListagemCuidadorDto> criarCuidador(@RequestBody UsuarioCriacaoCuidadorDto usuarioCriacaoCuidadorDto){
        if (usuarioService.emailJaExiste(usuarioCriacaoCuidadorDto.getEmail())){
            return ResponseEntity.status(409).build();
        }
        if(usuarioCriacaoCuidadorDto == null){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(201).body(cuidadorService.criar(usuarioCriacaoCuidadorDto));

    }
    @PostMapping("/criar-responsavel")
    public ResponseEntity<UsuarioListagemResponsavelDto> criarResponsavel(@RequestBody UsuarioCriacaoResponsavelDto usuarioCriacaoResponsavelDtoDto){
        if (usuarioService.emailJaExiste(usuarioCriacaoResponsavelDtoDto.getEmail())){
            return ResponseEntity.status(409).build();
        }
        if(usuarioCriacaoResponsavelDtoDto == null){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(201).body(responsavelService.criar(usuarioCriacaoResponsavelDtoDto));
    }





}
