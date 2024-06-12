package seniorcare.crudseniorcare.controller.usuario;

import lombok.Data;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.service.usuario.AdministradorService;
import seniorcare.crudseniorcare.service.usuario.dto.AdministradorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.CuidadorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.administrador.AdministradorCriacaoDto;
import seniorcare.crudseniorcare.service.usuario.dto.administrador.AdministradorListagemDto;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.UsuarioListagemCuidadorDto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
    @RequestMapping("/administradores")
@RequiredArgsConstructor
public class AdministradorController {
    private final AdministradorService service;
    private static final Logger logger = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping
    public ResponseEntity<List<AdministradorListagemDto>> listar(){
        List<Administrador> administradors = service.list();

        if (administradors.isEmpty()) return ResponseEntity.noContent().build();
        List<AdministradorListagemDto> dto = AdministradorMapper.toDtoList(administradors);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorListagemDto> porId(@PathVariable Integer id){
        Administrador administrador = service.byId(id);
        AdministradorListagemDto dto = AdministradorMapper.toAdministradorDto(administrador);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AdministradorListagemDto> criar(@RequestBody AdministradorCriacaoDto administradorCriacaoDto) {

        Administrador salvoEntity = AdministradorMapper.toAdministrador(administradorCriacaoDto);

        Administrador salvo = service.criar(salvoEntity);

        AdministradorListagemDto dto = AdministradorMapper.toAdministradorDto(salvo);

        URI uri = URI.create("/administradores/" + salvo.getIdUsuario());

        return ResponseEntity.created(uri).body(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorListagemDto> update(@PathVariable Integer id, @RequestBody Administrador administrador){
        Administrador uptAdministrador = service.update(id, administrador);
        AdministradorListagemDto dto = AdministradorMapper.toAdministradorDto(uptAdministrador);
        return ResponseEntity.ok(dto);
    }
}
