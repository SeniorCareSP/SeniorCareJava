package seniorcare.crudseniorcare.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.service.usuario.CuidadorService;
import seniorcare.crudseniorcare.service.usuario.ResponsavelService;
import seniorcare.crudseniorcare.service.usuario.UsuarioService;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioLoginDto;
import seniorcare.crudseniorcare.service.usuario.autenticacao.dto.UsuarioTokenDto;
import seniorcare.crudseniorcare.service.usuario.dto.ResponsavelMapper;
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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListagemDto> porId(@PathVariable UUID id){
        Usuario usuario = usuarioService.byId(id);
        UsuarioListagemDto dto = UsuarioMapper.toUsuarioListagemDto(usuario);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/desfazer-exclusao/cuidador")
    public ResponseEntity<Void> desfazerExclusaoCuidador() {
        usuarioService.desfazerExclusaoCuidador();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/desfazer-exclusao/responsavel")
    public ResponseEntity<Void> desfazerExclusaoResponsavel() {
        usuarioService.desfazerExclusaoResponsavel();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/desfazer-exclusao/administrador")
    public ResponseEntity<Void> desfazerExclusaoAdministrador() {
        usuarioService.desfazerExclusaoAdministrador();
        return ResponseEntity.ok().build();
    }
}

