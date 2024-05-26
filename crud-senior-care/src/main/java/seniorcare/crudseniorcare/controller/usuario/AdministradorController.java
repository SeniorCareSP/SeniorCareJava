package seniorcare.crudseniorcare.controller.usuario;

import lombok.Data;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import seniorcare.crudseniorcare.domain.usuario.Administrador;
import seniorcare.crudseniorcare.service.usuario.AdministradorService;
import seniorcare.crudseniorcare.service.usuario.dto.AdministradorMapper;
import seniorcare.crudseniorcare.service.usuario.dto.administrador.AdministradorCriacaoDto;
import seniorcare.crudseniorcare.service.usuario.dto.administrador.AdministradorListagemDto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/administradores")
@Data
public class AdministradorController {
    private final AdministradorService service;

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
    public ResponseEntity<AdministradorListagemDto> criar (
            @RequestBody AdministradorCriacaoDto administradorCriacaoDto){

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
