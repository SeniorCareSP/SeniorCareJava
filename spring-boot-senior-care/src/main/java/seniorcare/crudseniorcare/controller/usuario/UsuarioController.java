package seniorcare.crudseniorcare.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seniorcare.crudseniorcare.domain.usuario.Administrador;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);


    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){

        logger.info("Usuario pra login: {}", usuarioLoginDto);  // Log do token gerado

        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDto);

        logger.info("Usuario token login: {}", usuarioToken);  // Log do token gerado

        return ResponseEntity.status(200).body(usuarioToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        usuarioService.logout();
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListagemDto>> listarUsuario(){
        List<Usuario> listaUsuario = usuarioService.list();
        List<UsuarioListagemDto> listaUsuarioDto = listaUsuario.stream()
                .map(UsuarioMapper::toUsuarioListagemDto)
                .collect(Collectors.toList());

        if (listaUsuarioDto.isEmpty()){
            return ResponseEntity.status(204).body(listaUsuarioDto);
        }
        return ResponseEntity.status(200).body(listaUsuarioDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListagemDto> porId(@PathVariable Integer id){
        Usuario usuario = usuarioService.byId(id);
        UsuarioListagemDto dto = UsuarioMapper.toUsuarioListagemDto(usuario);
        return ResponseEntity.ok(dto);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/desfazer-exclusao/cuidador")
    public ResponseEntity<Cuidador> desfazerExclusaoCuidador() {
        Cuidador cuid = usuarioService.desfazerExclusaoCuidador();
        return ResponseEntity.ok().body(cuid);
    }

    @PostMapping("/desfazer-exclusao/responsavel")
    public ResponseEntity<Responsavel> desfazerExclusaoResponsavel() {
        Responsavel resp = usuarioService.desfazerExclusaoResponsavel();
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping("/desfazer-exclusao/administrador")
    public ResponseEntity<Administrador> desfazerExclusaoAdministrador() {
       Administrador adm = usuarioService.desfazerExclusaoAdministrador();
        return ResponseEntity.ok().body(adm);
    }
}

